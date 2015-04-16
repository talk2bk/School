#define _CRT_SECURE_NO_WARNINGS
#define SDL_MAIN_HANDLED

#include <GL/glew.h>
#include <SDL.h>
#include <stdio.h>
#include "DrawUtils.h"
#include <stdlib.h>
#include <stdbool.h>

//array of textures for everything
GLuint textures[8];

enum Direction{
	up,
	down,
	left,
	right
};

enum Speed{
	slow,
	medium,
	fast,
	sanic
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
	int x, y, w, h;
}AABB;

typedef struct AI{
	enum Direction d;
	bool chasePlayer;
	enum Speed;

}AI;

typedef struct Camera{
	AABB bounds;
}Camera;

//player sprite, has a bounding box and animation data.
typedef struct Player{
	AABB bounds;
	AnimData data;
	enum Direction facing;
}Player;

//any of the three items, mush, skull, or evilmush.
typedef struct Item{
	AABB bounds;
	AnimData data;
	bool collided;
	AI movementPattern;
}Item;

typedef struct Tile{
	int image;
	AABB bounds;
	bool passable;
}Tile;

Tile background[40][40];
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

void resetPos(Item *item){
	item->bounds.x = randr(0,640);
	item->bounds.y = randr(0,640);
	item->collided = false;
}

bool checkLeft(int x, int y){
	if (background[(x - 16) / 16][y / 16].passable == false || background[(x - 16) / 16][(y - 16) / 16].passable == false){ return false; };//check one block to your left
}
bool checkRight(int x, int y){
	if (background[x / 16][y / 16].passable == false || background[(x + 16) / 16][(y + 16) / 16].passable == false){ return false; }//check one block to your right
}
bool checkUp(int x, int y){
	return background[x / 16][(y - 16) / 16].passable;//check one block to your up
}
bool checkDown(int x, int y){
	return background[x/ 16][(y + 48) / 16].passable;//check one block to your down
}


bool checkMovement(Player *player){
	//take player position, check the position in the direction they are moving, return false if that move is illegal
	switch (player->facing){
	case left: return checkLeft(player->bounds.x, player->bounds.y); break;
	case right: return checkRight(player->bounds.x, player->bounds.y); break;
	case up: return checkUp(player->bounds.x, player->bounds.y); break;
	case down: return checkDown(player->bounds.x, player->bounds.y); break;
	}
}

void moveDirection(Item* item){
	switch (item->movementPattern.d){
	case left: item->bounds.x--; break;
	case right: item->bounds.x++; break;
	case up: item->bounds.y--; break;
	case down: item->bounds.y++; break;
	}
}

void itemAnimUpdate(Item* item){
	//write this with the info at the bottom.
}

void itemUpdate(Item* item,Player* player, float dt){
	itemAnimUpdate(&item->data);

	float deltaX;
	float deltaY;
	if (item->movementPattern.chasePlayer){
		item->movementPattern.d = player->facing;
		moveDirection(item);
	}
	

}//maybe pass in different AIs for each item. give each item an AI???

int main(void)
{
	
	//*******************************************************************************************************
	char shouldExit = 0;

	/* Initialize SDL */
	if( SDL_Init( SDL_INIT_VIDEO ) < 0 ) {
	return 1;
	}

	/* Create the window, OpenGL context */
	SDL_GL_SetAttribute(SDL_GL_BUFFER_SIZE, 32);
	SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);
	SDL_Window* window = SDL_CreateWindow(
		"New and Improved and New",
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
	Item mush;
	Item skull;
	Item slow;
	//number of items is 3.
	Item items[6];

	int charHeight = 16;
	int charWidth = 32;
	int tileHeight = 16; 
	int tileWidth = 16;
	textures[0] = glTexImageTGAFile("enderStone.tga", &tileWidth,&tileHeight);
	textures[1] = glTexImageTGAFile("obsidian.tga", &tileWidth, &tileHeight);
	textures[8] = glTexImageTGAFile("sandStone.tga", &tileWidth, &tileHeight);

	textures[2] = glTexImageTGAFile("mush.tga", &tileWidth, &tileHeight);
	textures[3] = glTexImageTGAFile("skull.tga", &tileWidth, &tileHeight);
	textures[4] = glTexImageTGAFile("slow.tga", &tileWidth, &tileHeight);

	textures[5] = glTexImageTGAFile("idleAnim.tga", &charWidth, &charHeight);
	textures[6] = glTexImageTGAFile("walkOne.tga", &charWidth, &charHeight);
	textures[7] = glTexImageTGAFile("walkTwo.tga", &charWidth, &charHeight);

	

	//player
	player.bounds.x = 0;
	player.bounds.y = 0;
	player.bounds.h = charHeight;
	player.bounds.w = charWidth;
	int jumpDistanceX = 32;
	int jumpDistanceY = 48;
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

	//camera
	camera.bounds.x = 0; 
	camera.bounds.y = 0;
	camera.bounds.w = 640;
	camera.bounds.h = 480;

	
	//mush
	resetPos(&mush);
	mush.bounds.w = tileWidth;
	mush.bounds.h = tileHeight;
	int mushTimeToGrow = 30.0f;
	int mushGrowTime = 1.0f;
	//skull
	resetPos(&skull);
	skull.bounds.w = tileWidth;
	skull.bounds.h = tileHeight;
	int skullTimeToGrow = 30.0f;
	int skullGrowTime = 1.0f;
	//slow
	resetPos(&slow);
	slow.bounds.w = tileWidth;
	slow.bounds.h = tileHeight;
	int slowTimeToGrow = 30.0f;
	int slowGrowTime = 1.0f;
	
	

	/*to do: set up mush animation stuff*/
	AnimData mushAnimData;
	mushAnimData.curFrame = 0;
	mushAnimData.timeToNextFrame = 1.0f;
	mushAnimData.isPlaying = true;
	AnimDef mushIdle;
	mushIdle.name = "idle";
	mushIdle.numFrames = 1;
	mushIdle.frames[0].frameNum = 2;
	mushIdle.frames[0].frameTime = 1.0f;
	mushAnimData.def = &mushIdle;
	mush.data = mushAnimData;

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

	/*to do: set up slow animation stuff*/
	AnimData slowAnimData;
	slowAnimData.curFrame = 0;
	slowAnimData.timeToNextFrame = 1.0f;
	slowAnimData.isPlaying = true;
	AnimDef slowIdle;
	slowIdle.name = "idle";
	slowIdle.numFrames = 1;
	slowIdle.frames[0].frameNum = 4;
	slowIdle.frames[0].frameTime = 1.0f;
	slowAnimData.def = &slowIdle;
	slow.data = slowAnimData;

	for (int i = 0; i < 40; i++){
		for (int j = 0; j < 40; j++){
			background[i][j].bounds.w = tileWidth;
			background[i][j].bounds.h = tileHeight;
			background[i][j].bounds.x = i * tileWidth;
			background[i][j].bounds.y = j * tileHeight;

			switch (randr(0, 2)){
			case 0: background[i][j].image = textures[0]; background[i][j].passable = true; break;
			case 1: background[i][j].image = textures[8]; background[i][j].passable = true; break;
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
		glClearColor(1, 1, 1, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		currentFrameMs = SDL_GetTicks();
		//use for time since last frame
		float deltaTime = (currentFrameMs - lastFrameMs) / 1000.0f;

		//update and draw game
		for (int i = 0; i < 40; i++){
			for (int j = 0; j < 40; j++){
				if (AABBIntersect(&camera.bounds, &background[i][j].bounds)){
					glDrawSprite(background[i][j].image, background[i][j].bounds.x - camera.bounds.x, background[i][j].bounds.y - camera.bounds.y, background[i][j].bounds.h, background[i][j].bounds.w);
				}

			}

		}

		//if esc quit game
		if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }
		if (kbState[SDL_SCANCODE_SPACE] && !kbPrevState[SDL_SCANCODE_SPACE]){ //jump in a direction
			switch (player.facing){
			case right: player.bounds.x += jumpDistanceX; break;
			case left: player.bounds.x -= jumpDistanceX; break;
			case up: player.bounds.y -= jumpDistanceY; break;
			case down: player.bounds.y += jumpDistanceY; break;
			}
		}

		//update player based on input. playerUpdate(&player, deltaTime);
		if (kbState[SDL_SCANCODE_LEFT] && !kbPrevState[SDL_SCANCODE_LEFT]){
			player.data.isPlaying = true;
			if (player.facing != left){ player.facing = left; }
			if (player.bounds.w < 0) { player.bounds.w = -player.bounds.w;}
			if(player.facing == left && checkMovement(&player)) { player.bounds.x -= 16; }
			
			
		}
		else if (kbState[SDL_SCANCODE_RIGHT] && !kbPrevState[SDL_SCANCODE_RIGHT]){
			player.data.isPlaying = true;
			if (player.facing != right) { player.facing = right; }
			if (player.bounds.w > 0) { player.bounds.w = -player.bounds.w; }
			if(player.facing == right && checkMovement(&player)) { player.bounds.x += 16; }
			
			
		}
		if (kbState[SDL_SCANCODE_UP] && !kbPrevState[SDL_SCANCODE_UP]){
			player.data.isPlaying = true;
			if (player.facing == up && checkMovement(&player)) { player.bounds.y -= 16; }
			player.facing = up;
			
			
		}
		else if (kbState[SDL_SCANCODE_DOWN] && !kbPrevState[SDL_SCANCODE_DOWN]){
			player.data.isPlaying = true;
			if (player.facing == down && checkMovement(&player)) { player.bounds.y += 16; }
			player.facing = down;
			
		}

		if (player.data.curFrame == 4){ animReset(&player.data); }
		else{ animTick(&player.data, deltaTime); }

		if (mush.bounds.w >= 20 || mush.bounds.h >= 20){ mush.bounds.w = 16; mush.bounds.h = 16; mushTimeToGrow = 30.0f; }
		else if (mushTimeToGrow < 0){ mush.bounds.w++; mush.bounds.h++; mushTimeToGrow = 30.0f; }
		mushTimeToGrow -= mushGrowTime;

		if (skull.bounds.w >= 20 || skull.bounds.h >= 20){ skull.bounds.w = 16; skull.bounds.h = 16; skullTimeToGrow = 30.0f; }
		else if (skullTimeToGrow < 0){ skull.bounds.w++; skull.bounds.h++; skullTimeToGrow = 30.0f; }
		skullTimeToGrow -= skullGrowTime;

		if (slow.bounds.w >= 20 || slow.bounds.h >= 20){ slow.bounds.w = 16; slow.bounds.h = 16; slowTimeToGrow = 30.0f; }
		else if (slowTimeToGrow < 0){ slow.bounds.w++; slow.bounds.h++; slowTimeToGrow = 30.0f; }
		slowTimeToGrow -= slowGrowTime;

		//update camera, items based on input and player. cameraUpdate(&camera, deltaTime); for(int i = 0; i < numitems; ++i){itemUpdate(&items[i],deltaTime);}
		if (kbState[SDL_SCANCODE_A]){
			if (camera.bounds.x > -640) camera.bounds.x -= tileWidth/4;
			if (camera.bounds.x <= -640) camera.bounds.x = background[39][39].bounds.x + 16;//looop
		}
		else if (kbState[SDL_SCANCODE_D]){
			if (camera.bounds.x < background[39][39].bounds.x+16) camera.bounds.x += tileWidth/4;
			if (camera.bounds.x >= background[39][39].bounds.x + 16) camera.bounds.x = -640;//looooop
		}
		if (kbState[SDL_SCANCODE_W]){
			if (camera.bounds.y > -480) camera.bounds.y -= tileHeight/4;
			if (camera.bounds.y <= -480) camera.bounds.y = background[39][39].bounds.y + 16;//loop
		}
		else if (kbState[SDL_SCANCODE_S]){
			if (camera.bounds.y < background[39][39].bounds.y + 16) camera.bounds.y += tileHeight/4;
			if (camera.bounds.y >= background[39][39].bounds.y + 16) camera.bounds.y = -480;
		}

		if (AABBIntersect(&player.bounds, &mush.bounds)){ mush.collided = true; }
		if (AABBIntersect(&player.bounds, &skull.bounds)){ skull.collided = true; }
		if (AABBIntersect(&player.bounds, &slow.bounds)){ slow.collided = true; }
		if (mush.collided){ resetPos(&mush); }
		if (skull.collided){ resetPos(&skull); }
		if (slow.collided){ resetPos(&slow); }

		//physics stuff
		//do{
			//physics movement
			//physics collision detection
			//physics collision resolution
			//lastPhysicsFrameMs += physicsDeltaMs;
		//} while(lastPhysicsFrameMs + physicsDeltaMs < curFrameMs);

		//draw the current state of everything. playerDraw(&player);
		//draw items, then draw character.
		animDraw(&mush.data, mush.bounds.x - camera.bounds.x, mush.bounds.y - camera.bounds.y, mush.bounds.w, mush.bounds.h);
		animDraw(&skull.data, skull.bounds.x - camera.bounds.x, skull.bounds.y - camera.bounds.y, skull.bounds.w, skull.bounds.h);
		animDraw(&slow.data, slow.bounds.x - camera.bounds.x, slow.bounds.y - camera.bounds.y, slow.bounds.w, slow.bounds.h);
		
		animDraw(&player.data, player.bounds.x-camera.bounds.x, player.bounds.y-camera.bounds.y, player.bounds.w, player.bounds.h);
		
		SDL_GL_SwapWindow(window);
	}
	SDL_Quit();
	return 0;
}


