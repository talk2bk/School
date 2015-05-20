#define _CRT_SECURE_NO_WARNINGS
#define SDL_MAIN_HANDLED

#include <GL/glew.h>
#include <SDL.h>
#include <stdio.h>
#include "DrawUtils.h"
#include <stdlib.h>
#include <stdbool.h>

//array of textures for everything
GLuint textures[30];

enum GameState{
	GS_TitleScreen,
	GS_Game,
	GS_GameOverGood,
	GS_GameOverBad
};

enum Direction{//change this to just negative and positive values
	up,
	down,
	left,
	right
};

enum Speed{
	low = 32,
	medium = 64,
	high = 128,
	sanic = 256,
	tooFastKool = 512
};
//structs

//holds frame number and how long that frame exists
typedef struct AnimFrameDef{
	int frameNum;
	float frameTime;
}AnimFrameDef;

//animation name, copy of frames, number of frames in teh animation
typedef struct AnimDef{
	const char* name;
	AnimFrameDef frames[20];
	int numFrames;
}AnimDef;

//takes the def, takes current frame, uses time to next frame and checks if the animation is playing.
typedef struct AnimData{
	AnimDef* def;
	int curFrame;
	float timeToNextFrame;
	bool isPlaying;
}AnimData;

//bounding box
typedef struct AABB{
	float x, y, w, h;
}AABB;

typedef struct AI{
	bool chasePlayer;
	enum Speed speed;
	float timeToChangeAI;
	float defaultTimeToChangeAI;
}AI;

typedef struct Camera{
	AABB bounds;
}Camera;

typedef struct Attack{
	AABB bounds;
	AnimData data;
	bool collided;
	bool collidedPlayer;
	enum Speed attackSpeed;
}Attack;

typedef struct Stand{
	AABB bounds;
	Attack left;
	Attack right;
	AnimData data;
	bool play;
	int standHealth;
	bool attacking;
}Stand;

//player sprite, has a bounding box and animation data.
typedef struct Player{
	AABB bounds;
	AnimData data;
	enum Direction facing;
	enum Speed speed;
	bool shot;
	Stand playerStand;
	bool isRolling;
	int playerHealth;
	bool hit;
}Player;

typedef struct Boss{
	AABB bounds;
	AnimData data;
	bool attacked;
	Stand bosssStand;
	int bossHealth;
	bool alive;
	//probably need to implement an AI
}Boss;



typedef struct GrowTime{
	float timeToGrow;
	float growTime;
}GrowTime;

//any of the three items, mush, skull, or evilmush.
typedef struct Item{
	AABB bounds;
	AnimData data;
	bool collided;
	AI movementPattern;
	GrowTime grow;
	enum Speed speed;
	int damage;
}Item;

typedef struct Tile{
	int image;
	AABB bounds;
	bool passable;
}Tile;

Tile background[40][30];
//methods

//set which animation is running
void animSet(AnimData* anim, AnimDef* toPlay){
	anim->def = toPlay;
	anim->curFrame = 0;
	anim->timeToNextFrame = toPlay->frames[0].frameTime;
	anim->isPlaying = true;
}

//draw the animation
void animDraw(AnimData* anim, int x, int y, int w, int h){
	AnimDef* def = anim->def;
	int curFrameNum = def->frames[anim->curFrame].frameNum;
	GLuint tex = textures[curFrameNum];
	glDrawSprite(tex, x, y, w, h);
}

//reset the animation
void animReset(AnimData*anim){
	animSet(anim, anim->def);
}

//advance to next frame
void animTick(AnimData* data, float dt){
	if (!data->isPlaying){
		return;
	}
	int numFrames = data->def->numFrames;
	data->timeToNextFrame -= dt;
	if (data->timeToNextFrame < 0){
		++data->curFrame;
		if (data->curFrame >= numFrames){
			//end of animation, stop it
			data->curFrame = numFrames - 1;
			data->timeToNextFrame = 0;
			data->isPlaying = false;
		}
		else{
			AnimFrameDef* curFrame = &data->def->frames[data->curFrame];
			data->timeToNextFrame += curFrame->frameTime;
		}
	}
}

//check intersection of AABB
bool AABBIntersect(const AABB* box1, const AABB* box2)
{
	// box1 to the right
	if (box1->x > box2->x + box2->w) {
		return false;
	}
	// box1 to the left
	if (box1->x + box1->w < box2->x) {
		return false;
	}
	// box1 below
	if (box1->y > box2->y + box2->h) {
		return false;
	}
	// box1 above
	if (box1->y + box1->h < box2->y) {
		return false;
	}
	return true;
}

unsigned int randr(unsigned int min, unsigned int max)
{
	bool notInRange = true;
	int result;
	while (notInRange){
		double scaled = (double)rand() / RAND_MAX;

		result = ((max - min + 1)*scaled + min);

		if (result <= max && result >= min)
		{
			notInRange = false;
		}
	}

	return result;
}

void resetPos(Item* item){
	item->bounds.x = randr(0, 640);
	item->bounds.y = randr(0, 640);
	item->collided = false;
	item->grow.growTime = 30.0f;
	item->grow.timeToGrow = 1.0f;
}

void resetPosShot(Item* item, Player* player){
	item->bounds.x = -30;
	item->bounds.y = -30;
	item->collided = false;
	//item->grow.growTime = 30.0f;
	//item->grow.timeToGrow = 1.0f;
	player->shot = false;
}

void resetAttack(Boss* boss, Player* player){
	boss->bosssStand.left.bounds.x = player->bounds.x - 158*2 - 150;
	boss->bosssStand.right.bounds.x = player->bounds.x + 158*2;
	boss->bosssStand.left.bounds.y = player->bounds.y - 129;
	boss->bosssStand.right.bounds.y = player->bounds.y - 129;
	boss->bosssStand.left.collided = false;
	boss->bosssStand.right.collided = false;
}

void shoot(Player *player, Item *item, float dt){
	player->shot = true;
	item->bounds.x = player->bounds.x;
	item->bounds.y = player->bounds.y;
	item->speed = sanic;
	
}



void itemAnimUpdate(Item* item, float dt){
	if (item->bounds.w >= 20 || item->bounds.h >= 20){ item->bounds.w = 16; item->bounds.h = 16; item->grow.timeToGrow = 30.0f; }
	else if (item->grow.timeToGrow < 0){ item->bounds.w++; item->bounds.h++; item->grow.timeToGrow = 30.0f; }
	item->grow.timeToGrow -= item->grow.growTime*dt;
}

void itemUpdate(Item* item, Player* player, float dt){
	itemAnimUpdate(item, dt);
	if (item->speed > 0 && !item->collided){
		item->bounds.y -= item->speed*dt;
	}

	//updateAI(item, dt);

	//if (item->movementPattern.chasePlayer){moveTowards(item, player, dt);}
	//else{moveAway(item, player, dt);}

}//maybe pass in different AIs for each item. give each item an AI???

void printLocation(Player* player){
	printf("Player x = %d\n", player->bounds.x);
	printf("Player y = %d\n", player->bounds.y);
}

void checkLocation(Player* player){//moves player to edge of screen whe ngoing right or left
	if (player->bounds.x < 0){ player->bounds.x = 640 - player->speed; } //if off screen to the left, make him come out to the right
	else if (player->bounds.x >= 640){ player->bounds.x = 0; } //if offscreen to the right, make him come out to the left

	if (player->bounds.y < 0){ player->bounds.y = 480 - player->speed; }
	else if (player->bounds.y >= 480){ player->bounds.y = 0 + player->speed; }
}

void roll(Player* player){

	switch (player->facing){
	case(left) : player->bounds.x -= 40; break;
	case(right) : player->bounds.x += 40; break;
	case(up) : player->bounds.y -= 40; break;
	case(down) : player->bounds.y += 40; break;
	}
	player->isRolling = true;

}

int main(void)
{

	//*******************************************************************************************************
	char shouldExit = 0;

	/* Initialize SDL */
	if (SDL_Init(SDL_INIT_VIDEO) < 0) {
		return 1;
	}

	/* Create the window, OpenGL context */
	SDL_GL_SetAttribute(SDL_GL_BUFFER_SIZE, 32);
	SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);
	SDL_Window* window = SDL_CreateWindow(
		"Press space to jump spaces, arrow keys to move, wasd to move camera. items will run at or away from you alternating.",
		SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED,
		640, 480,
		SDL_WINDOW_OPENGL);
	if (!window) {
		fprintf(stderr, "Could not create window.ErrorCode = %s\n", SDL_GetError());
		SDL_Quit();
		return 1;
	}
	SDL_GL_CreateContext(window);

	/* Make sure we have a recent version of OpenGL */
	GLenum glewError = glewInit();
	if (glewError != GLEW_OK) {
		fprintf(stderr, "Could not initialize glew.ErrorCode = %s\n", glewGetErrorString(glewError));
		SDL_Quit();
		return 1;
	}
	if (!GLEW_VERSION_3_0) {
		fprintf(stderr, "OpenGL max supported version is too low.\n");
		SDL_Quit();
		return 1;
	}
	/* Setup OpenGL state */
	glViewport(0, 0, 640, 480);
	glMatrixMode(GL_PROJECTION);
	glOrtho(0, 640, 480, 0, 0, 100);
	glEnable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	/* The previous frame's keyboard state */
	unsigned char kbPrevState[SDL_NUM_SCANCODES] = { 0 };
	/* The current frame's keyboard state */
	const unsigned char* kbState = NULL;
	/* Keep a pointer to SDL's internal keyboard state */
	kbState = SDL_GetKeyboardState(NULL);
	//*********************************************************************************************************

	Player player;
	Camera camera;
	Boss boss;
	Stand bossStand;
	Item skull;
	bool prevFrameHit;
	enum GameState currentState;
	currentState = GS_TitleScreen;

	int charHeight = 28;
	int charWidth = 34;
	int tileHeight = 16;
	int tileWidth = 16;
	//background
	textures[0] = glTexImageTGAFile("enderStone.tga", &tileWidth, &tileHeight);
	textures[1] = glTexImageTGAFile("obsidian.tga", &tileWidth, &tileHeight);
	textures[8] = glTexImageTGAFile("sandStone.tga", &tileWidth, &tileHeight);
	//misc
	textures[2] = glTexImageTGAFile("mush.tga", &tileWidth, &tileHeight);
	textures[3] = glTexImageTGAFile("skull.tga", &tileWidth, &tileHeight);
	textures[4] = glTexImageTGAFile("slow.tga", &tileWidth, &tileHeight);
	//player
	textures[5] = glTexImageTGAFile("idleAnim.tga", &charWidth, &charHeight);
	textures[6] = glTexImageTGAFile("walkOne.tga", &charWidth, &charHeight);
	textures[7] = glTexImageTGAFile("walkTwo.tga", &charWidth, &charHeight);
	textures[20] = glTexImageTGAFile("roll.tga", &charWidth, &charHeight);
	//boss

	textures[8] = glTexImageTGAFile("menuidle.tga", NULL, NULL);

	textures[9] = glTexImageTGAFile("ready.tga", NULL, NULL);
	textures[10] = glTexImageTGAFile("attack.tga", NULL, NULL);
	textures[11] = glTexImageTGAFile("death1.tga", NULL, NULL);
	textures[12] = glTexImageTGAFile("death2.tga", NULL, NULL);
	textures[13] = glTexImageTGAFile("death3.tga", NULL, NULL);
	textures[14] = glTexImageTGAFile("death4.tga", NULL, NULL);

	textures[15] = glTexImageTGAFile("stand.tga", NULL, NULL);
	textures[16] = glTexImageTGAFile("attack1.tga", NULL, NULL);
	textures[17] = glTexImageTGAFile("attack1Mirror.tga", NULL, NULL);
	textures[18] = glTexImageTGAFile("attack2.tga", NULL, NULL);
	textures[19] = glTexImageTGAFile("attack2Mirror.tga", NULL, NULL);
	

	//camera
	camera.bounds.x = 0;
	camera.bounds.y = 0;
	camera.bounds.w = 640;
	camera.bounds.h = 480;

	//player
	player.bounds.x = (640/2) - 17;
	player.bounds.y = 448;
	player.bounds.h = charHeight;
	player.bounds.w = charWidth;
	player.facing = left;
	player.shot = false;
	player.isRolling = false;
	player.playerHealth = 3;
	player.hit = false;

	int jumpDistanceX = 32;
	int jumpDistanceY = 48;
	player.speed = medium;
	/*to do: set up animation stuff*/
	AnimData playerAnimData;
	playerAnimData.curFrame = 0;
	playerAnimData.timeToNextFrame = 0.5f;
	playerAnimData.isPlaying = false;
	AnimDef walk;
	walk.name = "walk";
	walk.numFrames = 5;
	walk.frames[0].frameNum = 5;
	walk.frames[0].frameTime = 0.5f;
	walk.frames[1].frameNum = 7;
	walk.frames[1].frameTime = 0.5f;
	walk.frames[2].frameNum = 6;
	walk.frames[2].frameTime = 0.5f;
	walk.frames[3].frameNum = 7;
	walk.frames[3].frameTime = 0.5f;
	walk.frames[4].frameNum = 6;
	walk.frames[4].frameTime = 0.5f;
	playerAnimData.def = &walk;
	player.data = playerAnimData;

	AnimDef rollDef;
	rollDef.name = "roll";
	rollDef.numFrames = 1;
	rollDef.frames[0].frameNum = 20;
	rollDef.frames[0].frameTime = 1.0f;

	//boss
	boss.bounds.x = (640/2) - (75/2);
	boss.bounds.y = (480/2) - 120;
	boss.bounds.h = 75;
	boss.bounds.w = 120;
	boss.bossHealth = 1;
	boss.attacked = false;
	
	AnimData bossAnimData;
	bossAnimData.curFrame = 0;
	bossAnimData.timeToNextFrame = 0.0f;
	bossAnimData.isPlaying = false;

	AnimDef menuIdle;
	menuIdle.name = "menuIdle";
	menuIdle.numFrames = 1;
	menuIdle.frames[0].frameNum = 8;
	menuIdle.frames[0].frameTime = 1.0f;

	AnimDef ready;
	ready.name = "ready";
	ready.numFrames = 1;
	ready.frames[0].frameNum = 9;
	ready.frames[0].frameTime = 1.0f;

	AnimDef attack;
	attack.name = "attack";
	attack.numFrames = 1;
	attack.frames[0].frameNum = 10;
	attack.frames[0].frameTime = 1.0f;

	AnimDef death;
	death.name = "death";
	death.numFrames = 4;
	death.frames[0].frameNum = 11;
	death.frames[0].frameTime = 1.0f;
	death.frames[1].frameNum = 12;
	death.frames[1].frameTime = 1.0f;
	death.frames[2].frameNum = 13;
	death.frames[2].frameTime = 1.0f;
	death.frames[3].frameNum = 14;
	death.frames[3].frameTime = 1.0f;

	bossAnimData.def = &menuIdle;
	boss.data = bossAnimData;

	//boss's Stand
	bossStand.bounds.x = 640/2 - 233/2;
	bossStand.bounds.y = 480/2 - 120;
	bossStand.bounds.h = 217;
	bossStand.bounds.w = 220;
	bossStand.standHealth = 420;
	bossStand.play = true;
	bossStand.attacking = false;

	AnimData bossStandAnimData;
	bossStandAnimData.curFrame = 0;
	bossStandAnimData.timeToNextFrame = 0.0f;
	bossStandAnimData.isPlaying = false;

	AnimDef standDef;
	standDef.name = "standDef";
	standDef.numFrames = 1;
	standDef.frames[0].frameNum = 15;
	standDef.frames[0].frameTime = 1.0f;

	bossStandAnimData.def = &standDef;
	bossStand.data = bossStandAnimData;
	
	boss.bosssStand = bossStand;

	Attack leftAttack;
	leftAttack.bounds.x = 0;
	leftAttack.bounds.y = 0;
	leftAttack.bounds.h = 207;
	leftAttack.bounds.w = 158;
	leftAttack.attackSpeed = high;
	leftAttack.collided = false;
	leftAttack.collidedPlayer = false;
	AnimData leftAttackAnimData;
	leftAttackAnimData.curFrame = 0;
	leftAttackAnimData.timeToNextFrame = 0.0f;
	leftAttackAnimData.isPlaying = false;

	AnimDef leftAttackSwing;
	leftAttackSwing.name = "leftArm";
	leftAttackSwing.numFrames = 1;
	leftAttackSwing.frames[0].frameNum = 16;
	leftAttackSwing.frames[0].frameTime = 1.0f;

	AnimDef leftAttackSwingHit;
	leftAttackSwingHit.name = "leftArmHit";
	leftAttackSwingHit.numFrames = 1;
	leftAttackSwingHit.frames[0].frameNum = 18;
	leftAttackSwingHit.frames[0].frameTime = 1.0f;

	leftAttackAnimData.def = &leftAttackSwing;
	leftAttack.data = leftAttackAnimData;
	boss.bosssStand.left = leftAttack;

	Attack rightAttack;
	rightAttack.bounds.x = 640- 158;
	rightAttack.bounds.y = 0;
	rightAttack.bounds.h = 207;
	rightAttack.bounds.w = 158;
	rightAttack.attackSpeed = high;
	rightAttack.collided = false;
	rightAttack.collidedPlayer = false;
	AnimData rightAttackAnimData;
	rightAttackAnimData.curFrame = 0;
	rightAttackAnimData.timeToNextFrame = 0.0f;
	rightAttackAnimData.isPlaying = false;

	AnimDef rightAttackSwing;
	rightAttackSwing.name = "rightArm";
	rightAttackSwing.numFrames = 1;
	rightAttackSwing.frames[0].frameNum = 17;
	rightAttackSwing.frames[0].frameTime = 1.0f;

	AnimDef rightAttackSwingHit;
	rightAttackSwingHit.name = "rightArmHit";
	rightAttackSwingHit.numFrames = 1;
	rightAttackSwingHit.frames[0].frameNum = 19;
	rightAttackSwingHit.frames[0].frameTime = 1.0f;

	rightAttackAnimData.def = &rightAttackSwing;
	rightAttack.data = rightAttackAnimData;
	boss.bosssStand.right = rightAttack;


	//skull
	//resetPos(&skull);
	skull.bounds.w = 5;
	skull.bounds.h = 14;
	skull.damage = 10;
	//skull.movementPattern.chasePlayer = true;
	//skull.movementPattern.speed = low;
	//skull.movementPattern.timeToChangeAI = 15.0f;
	//skull.movementPattern.defaultTimeToChangeAI = 15.0f;


	/*to do: set up skull animation stuff*/
	AnimData skullAnimData;
	skullAnimData.curFrame = 0;
	skullAnimData.timeToNextFrame = 1.0f;
	skullAnimData.isPlaying = true;
	AnimDef skullIdle;
	skullIdle.name = "idle";
	skullIdle.numFrames = 1;
	skullIdle.frames[0].frameNum = 3;
	skullIdle.frames[0].frameTime = 1.0f;
	skullAnimData.def = &skullIdle;
	skull.data = skullAnimData;



	for (int i = 0; i < 40; i++){
		for (int j = 0; j < 30; j++){
			background[i][j].bounds.w = tileWidth;
			background[i][j].bounds.h = tileHeight;
			background[i][j].bounds.x = i * tileWidth;
			background[i][j].bounds.y = j * tileHeight;

			switch (randr(0, 2)){
			//case 0: background[i][j].image = textures[0]; background[i][j].passable = true; break;
			//case 1: background[i][j].image = textures[8]; background[i][j].passable = true; break;
			default: background[i][j].image = textures[1]; background[i][j].passable = false; break;//use this block as unpassable
			}
		}
	}

	Uint32 lastFrameMs;
	Uint32 currentFrameMs = SDL_GetTicks();
	float physicsDeltaTime = 1 / 100.0f;
	int physicsDeltaMs = 10;
	Uint32 lastPhysicsFrameMs;

	/* The game loop */
	while (!shouldExit) {
		//save last frame's values
		lastFrameMs = currentFrameMs;
		/* kbState is updated by the message pump. Copy over the old state before the pump! */
		memcpy(kbPrevState, kbState, sizeof(kbPrevState));

		// Handle OS message pump
		SDL_Event event;
		while (SDL_PollEvent(&event)) {
			switch (event.type) {
			case SDL_QUIT:
				shouldExit = 1;
			}
		}

		//get keyboard state
		kbState = SDL_GetKeyboardState(NULL);
		glClearColor(0, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		currentFrameMs = SDL_GetTicks();
		//use for time since last frame
		float deltaTime = (currentFrameMs - lastFrameMs) / 1000.0f;

		//*********************************************************************************************************

		switch (currentState){
		case GS_TitleScreen:
			if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }
			if (kbState[SDL_SCANCODE_X] && !kbPrevState[SDL_SCANCODE_X]){ animSet(&boss.data, &ready); currentState = GS_Game; }
			

			animDraw(&boss.data, boss.bounds.x - camera.bounds.x, boss.bounds.y - camera.bounds.y, boss.bounds.w, boss.bounds.h);
			animDraw(&player.data, player.bounds.x - camera.bounds.x, player.bounds.y - camera.bounds.y, player.bounds.w, player.bounds.h);

			break;
//*********************************************************************************************************
		case GS_Game:
			//update and draw game
			for (int i = 0; i < 40; i++){
				for (int j = 0; j < 30; j++){
					if (AABBIntersect(&camera.bounds, &background[i][j].bounds)){
						glDrawSprite(background[i][j].image, background[i][j].bounds.x - camera.bounds.x, background[i][j].bounds.y - camera.bounds.y, background[i][j].bounds.h, background[i][j].bounds.w);
					}

				}

			}

			//if esc quit game
			if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }

			//if (kbState[SDL_SCANCODE_X] && !kbPrevState[SDL_SCANCODE_X]){ printLocation(&player); }
			if ((kbState[SDL_SCANCODE_LSHIFT] || kbState[SDL_SCANCODE_RSHIFT]) && !player.shot){ //jump in a direction
				shoot(&player, &skull, deltaTime);
				//changeSpeed(&player);
			}

			if (kbState[SDL_SCANCODE_SPACE] && !kbPrevState[SDL_SCANCODE_SPACE] && !player.isRolling){
				roll(&player);
				animSet(&player.data, &rollDef);
			}

			//update player based on input. playerUpdate(&player, deltaTime);
			if (kbState[SDL_SCANCODE_LEFT]){

				player.data.isPlaying = true;
				player.facing = left;
				//if (player.bounds.w < 0) { player.bounds.w = -player.bounds.w; }
				player.bounds.x -= player.speed*deltaTime;
				checkLocation(&player);
			}
			else if (kbState[SDL_SCANCODE_RIGHT]){

				player.data.isPlaying = true;
				player.facing = right;
				//if (player.bounds.w > 0) { player.bounds.w = -player.bounds.w; }
				player.bounds.x += player.speed*deltaTime;
				checkLocation(&player);
			}
			if (kbState[SDL_SCANCODE_UP]){
				//max 320
				player.data.isPlaying = true;
				if (player.bounds.y > 0)player.bounds.y -= player.speed*deltaTime;
				player.facing = up;
				checkLocation(&player);

			}
			else if (kbState[SDL_SCANCODE_DOWN]){
				//lowest is 448
				player.data.isPlaying = true;
				if (player.bounds.y < 448)player.bounds.y += player.speed*deltaTime;
				player.facing = down;
				checkLocation(&player);
			}
			if (!player.isRolling){
				if (player.data.curFrame == 4){ animReset(&player.data); }
				else{ animTick(&player.data, deltaTime); }
			}

			if (boss.bosssStand.attacking && (!boss.bosssStand.left.collided && !boss.bosssStand.right.collided)){
				if (boss.bosssStand.left.bounds.x - player.bounds.x > 240) { boss.bosssStand.left.attackSpeed = medium; }
				if (boss.bosssStand.right.bounds.x - player.bounds.x > 240) { boss.bosssStand.right.attackSpeed = medium; }
				else{
					boss.bosssStand.left.attackSpeed = high; boss.bosssStand.right.attackSpeed = high;
				}
				boss.bosssStand.left.bounds.x += boss.bosssStand.left.attackSpeed*deltaTime;
				boss.bosssStand.right.bounds.x -= boss.bosssStand.right.attackSpeed*deltaTime;

			}

			itemUpdate(&skull, &player, deltaTime);

			//update camera, items based on input and player. cameraUpdate(&camera, deltaTime); for(int i = 0; i < numitems; ++i){itemUpdate(&items[i],deltaTime);}
			prevFrameHit = player.hit;

			//when hands hit, change animation to the hit animation
			if (AABBIntersect(&boss.bosssStand.left.bounds, &boss.bosssStand.right.bounds)){
				animSet(&boss.bosssStand.left.data, &leftAttackSwingHit);
				animSet(&boss.bosssStand.right.data, &rightAttackSwingHit);
				boss.bosssStand.left.collided = true;
				boss.bosssStand.right.collided = true;
				boss.bosssStand.attacking = false;
				//resetAttack(&boss, &player);
			}

			//if boss isnt attacking
			if (!boss.bosssStand.attacking){
				switch (randr(0, 2)){
				case 0: boss.bosssStand.attacking = true;  resetAttack(&boss, &player); break;
				default: break;
				}
			}


			//if hand hits a player, deal damage to player
			if ((AABBIntersect(&boss.bosssStand.left.bounds, &player.bounds)
				|| AABBIntersect(&boss.bosssStand.right.bounds, &player.bounds)
				|| AABBIntersect(&boss.bosssStand.bounds, &player.bounds)
				|| AABBIntersect(&boss.bounds, &player.bounds)) && (!player.hit && !prevFrameHit)){
				player.playerHealth -= 1;
				player.hit = true;
				printf("playerHealth: %d\n", player.playerHealth);
			}
			//if not hitting, and last frame wasnt hitting.
			if ((!AABBIntersect(&boss.bosssStand.left.bounds, &player.bounds)
				&& !AABBIntersect(&boss.bosssStand.right.bounds, &player.bounds)
				&& !AABBIntersect(&boss.bosssStand.bounds, &player.bounds)
				&& !AABBIntersect(&boss.bounds, &player.bounds)
				) && (player.hit && prevFrameHit)){
				player.hit = false;
			}

			//if shot hits stand
			if (AABBIntersect(&skull.bounds, &boss.bosssStand.bounds)){
				skull.collided = true;
				boss.bosssStand.standHealth -= skull.damage; printf("standhealth is: %d\n", boss.bosssStand.standHealth);
				boss.bosssStand.attacking = true;
				if (boss.bosssStand.standHealth <= 0){
					boss.bosssStand.play = false;
					boss.bosssStand.bounds.h = 0;
					boss.bosssStand.bounds.w = 0;
					boss.bosssStand.left.bounds.h = 0;
					boss.bosssStand.left.bounds.w = 0;
					boss.bosssStand.right.bounds.h = 0;
					boss.bosssStand.right.bounds.w = 0;
				}
			}

			//if shot hits arms
			if (AABBIntersect(&skull.bounds, &boss.bosssStand.left.bounds) || AABBIntersect(&skull.bounds, &boss.bosssStand.right.bounds)){
				skull.collided = true;
				//do nothing other than reset bullet
			}
			//if shot hits boss
			if (AABBIntersect(&skull.bounds, &boss.bounds)){
				skull.collided = true;
				boss.bossHealth -= skull.damage;
				if (boss.bossHealth <= 0) {boss.alive = false; animSet(&boss.data, &death); boss.data.isPlaying = true;}//when boss dies play animation of death.
			}
			//if shot hits edge of screen
			for (int k = 0; k < 40; k++){
				if (AABBIntersect(&skull.bounds, &background[k][0].bounds)){
					skull.collided = true;
				}
			}

			if (player.playerHealth <= 0){
				currentState = GS_GameOverBad;
			}

			if (!boss.alive){
				if (boss.data.curFrame == 3){ currentState = GS_GameOverGood; }
				else{ animTick(&boss.data, deltaTime); }
			}
			//

			//if shot hit something , reset it
			if (skull.collided){ skull.speed = 0; resetPosShot(&skull, &player); }
				
			//if (boss.data.curFrame < 4){ animTick(&boss.data, deltaTime); }

			//physics stuff
			//do{
			//physics movement
			//physics collision detection
			//physics collision resolution
			//lastPhysicsFrameMs += physicsDeltaMs;
			//} while(lastPhysicsFrameMs + physicsDeltaMs < curFrameMs);

			//draw the current state of everything. playerDraw(&player);
			//draw items, then draw character.
			//animDraw(&slow.data, slow.bounds.x - camera.bounds.x, slow.bounds.y - camera.bounds.y, slow.bounds.w, slow.bounds.h);
			//animDraw(&mush.data, mush.bounds.x - camera.bounds.x, mush.bounds.y - camera.bounds.y, mush.bounds.w, mush.bounds.h);

			//draw boss
			
			if (boss.bosssStand.play){
				animDraw(&boss.bosssStand.data, boss.bosssStand.bounds.x - camera.bounds.x, boss.bosssStand.bounds.y - camera.bounds.y, boss.bosssStand.bounds.w, boss.bosssStand.bounds.h);
				
			}
			animDraw(&boss.data, boss.bounds.x - camera.bounds.x, boss.bounds.y - camera.bounds.y, boss.bounds.w, boss.bounds.h);
			if (boss.bosssStand.attacking){
				animDraw(&boss.bosssStand.left.data, boss.bosssStand.left.bounds.x - camera.bounds.x, boss.bosssStand.left.bounds.y - camera.bounds.y, boss.bosssStand.left.bounds.w, boss.bosssStand.left.bounds.h);
				animDraw(&boss.bosssStand.right.data, boss.bosssStand.right.bounds.x - camera.bounds.x, boss.bosssStand.right.bounds.y - camera.bounds.y, boss.bosssStand.right.bounds.w, boss.bosssStand.right.bounds.h);
			}
			animDraw(&skull.data, skull.bounds.x - camera.bounds.x, skull.bounds.y - camera.bounds.y, skull.bounds.w, skull.bounds.h);

			animDraw(&player.data, player.bounds.x - camera.bounds.x, player.bounds.y - camera.bounds.y, player.bounds.w, player.bounds.h);
			if (player.isRolling){
				player.isRolling = false;
				animSet(&player.data, &walk);
			}
			break;
//*********************************************************************************************************
		case GS_GameOverBad:
			if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }
			animSet(&boss.data, &attack);
			animDraw(&boss.data, boss.bounds.x - camera.bounds.x, boss.bounds.y - camera.bounds.y, boss.bounds.w, boss.bounds.h);
			animSet(&player.data, &rollDef);
			//print you and all your friends are dead
			animDraw(&player.data, player.bounds.x - camera.bounds.x, player.bounds.y - camera.bounds.y, player.bounds.w, player.bounds.h);

			break;
		case GS_GameOverGood:
			if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }
			//print good job!
			animDraw(&player.data, player.bounds.x - camera.bounds.x, player.bounds.y - camera.bounds.y, player.bounds.w, player.bounds.h);
			break;
		}

		
		
		SDL_GL_SwapWindow(window);
	}
	SDL_Quit();
	return 0;
}


