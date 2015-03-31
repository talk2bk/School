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
int numItems = 3;
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

typedef struct Camera{
	AABB bounds;
}Camera;

//player sprite, has a bounding box and animation data.
typedef struct Player{
	AABB bounds;
	AnimData data;
}Player;

//any of the three items, mush, skull, or evilmush.
typedef struct Item{
	AABB bounds;
	AnimData data;
	bool collided;
}Item;

typedef struct Tile{
	int image;
	AABB bounds;
}Tile;

Tile background[40][40];
Player player;
Camera camera;
Item mush;
Item skull;
Item slow;
//number of items is 3.
Item items[2];

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
	item->bounds.x = randr(0, 600);
	item->bounds.y = randr(0, 400);
	item->collided = false;
}

int main(void)
{
	//*******************************************************************************************************
	char shouldExit = 0;

	/* Initialize SDL *l
	if( SDL_Init( SDL_INIT_VIDEO ) < 0 ) {
	return 1;
	}
	/* Create the window, OpenGL context */
	SDL_GL_SetAttribute(SDL_GL_BUFFER_SIZE, 32);
	SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);
	SDL_Window* window = SDL_CreateWindow(
		"Space. SPACE. SPAAAAAAAAAACE.",
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
	int charHeight = 16;
	int charWidth = 32;
	int tileHeight = 16; 
	int tileWidth = 16;
	textures[0] = glTexImageTGAFile("enderStone.tga", &tileWidth,&tileHeight);
	textures[1] = glTexImageTGAFile("obsidian.tga", &tileWidth, &tileHeight);
	textures[2] = glTexImageTGAFile("mush.tga", &tileWidth, &tileHeight);
	textures[3] = glTexImageTGAFile("skull.tga", &tileWidth, &tileHeight);
	textures[4] = glTexImageTGAFile("slow.tga", &tileWidth, &tileHeight);

	textures[5] = glTexImageTGAFile("idleAnim.tga", &charWidth, &charHeight);
	textures[6] = glTexImageTGAFile("walkOne.tga", &charWidth, &charHeight);
	textures[7] = glTexImageTGAFile("walkTwo.tga", &charWidth, &charHeight);

	items[0] = mush;
	items[1] = skull;
	items[2] = slow;

	//player
	player.bounds.x = 0;
	player.bounds.y = 0;
	player.bounds.h = charHeight;
	player.bounds.w = charWidth;
	/*to do: set up animation stuff*/

	//camera
	camera.bounds.x = 0; 
	camera.bounds.y = 0;
	camera.bounds.w = 640;
	camera.bounds.h = 480;

	for (int i = 0; i < 3; i++){
		resetPos(&items[i]);
	}
	/*to do: set up mush animation stuff*/
	
	/*to do: set up skull animation stuff*/
	
	/*to do: set up slow animation stuff*/

	for (int i = 0; i <= 40; i++){
		for (int j = 0; j <= 40; j++){
			background[i][j].bounds.w = tileWidth;
			background[i][j].bounds.h = tileHeight;
			background[i][j].bounds.x = i * tileWidth;
			background[i][j].bounds.y = j * tileHeight;

			if ((i * 16) % 2 == 0 || (j * 16) % 2 == 0){
				background[i][j].image = textures[0];
			}
			else{
				background[i][j].image = textures[1];
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

		currentFrameMs = SDL_GetTicks();
		//use for time since last frame
		float deltaTime = (currentFrameMs - lastFrameMs) / 1000.0f;

		//if esc quit game
		if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }

		//update and draw game
		for (int i = 0; i <= 40; i++){
			for (int j = 0; j <= 40; j++){
				if (AABBIntersect(&camera, &background[i][j].bounds)){
					glDrawSprite(background[i][j].image, background[i][j].bounds.x - camera.bounds.x, background[i][j].bounds.y - camera.bounds.y, background[i][j].bounds.h, background[i][j].bounds.w);
				}

			}

		}

		//update player based on input. playerUpdate(&player, deltaTime);
		if (kbState[SDL_SCANCODE_LEFT] && !kbPrevState[SDL_SCANCODE_LEFT]){
			if (player.bounds.x > 0) player.bounds.x -= 16;
		}
		else if (kbState[SDL_SCANCODE_RIGHT] && !kbPrevState[SDL_SCANCODE_RIGHT]){
			if (player.bounds.x < 640 - tileWidth) player.bounds.x += 16;
		}
		if (kbState[SDL_SCANCODE_UP] && !kbPrevState[SDL_SCANCODE_UP]){
			if (player.bounds.y > 0) player.bounds.y -= 16;
		}
		else if (kbState[SDL_SCANCODE_DOWN] && !kbPrevState[SDL_SCANCODE_DOWN]){
			if (player.bounds.y < 480 - tileHeight * 2) player.bounds.y += 16;
		}
		//update camera, items based on input and player. cameraUpdate(&camera, deltaTime); for(int i = 0; i < numitems; ++i){itemUpdate(&items[i],deltaTime);}
		if (kbState[SDL_SCANCODE_A]){
			if (camera.bounds.x > 0) camera.bounds.x -= 8;
		}
		else if (kbState[SDL_SCANCODE_D]){
			if (camera.bounds.x < 640 - tileWidth) camera.bounds.x += 8;
		}
		if (kbState[SDL_SCANCODE_W]){
			if (camera.bounds.y > 0) camera.bounds.y -= 8;
		}
		else if (kbState[SDL_SCANCODE_S]){
			if (camera.bounds.y < 480 - tileHeight * 2) camera.bounds.y += 8;
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
		/*to do: draw items, then draw character.*/
		/*
		for(int i = 0; i < 3; i++){
		animDraw(&items[i], items[i].bounds.x, items[i].bounds.y, items[i].bounds.w, items[i].bounds.h);
		*/
		//animDraw(&player, player.bounds.x, player.bounds.y, player.bounds.w, player,bounds.h);


		glClearColor(0, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		SDL_GL_SwapWindow(window);
	}
	SDL_Quit();
	return 0;
}


