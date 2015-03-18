#define _CRT_SECURE_NO_WARNINGS
#define SDL_MAIN_HANDLED

#include <GL/glew.h>
#include <SDL.h>
#include <stdio.h>
#include "DrawUtils.h"
#include <stdlib.h>

typedef enum {false,true} bool;

typedef struct {
	int x, y, w, h;
	GLuint image;
}AABB;

//change all tiles to this format
typedef struct{
	AABB bounds;
	GLuint image;
}Tile;

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

	//tiles for world 16x16
	int tileHeight = 16;
	int tileWidth = 16;
	GLuint tiles[1];
	tiles[0] = glTexImageTGAFile("enderStone.tga", NULL, NULL);
	tiles[1] = glTexImageTGAFile("obsidian.tga", NULL, NULL);
	GLuint items[2];
	items[0] = glTexImageTGAFile("mush.tga", NULL, NULL);
	items[1] = glTexImageTGAFile("skull.tga", NULL, NULL);
	items[2] = glTexImageTGAFile("slow.tga", NULL, NULL);

<<<<<<< HEAD
	textures[0] = tiles[0];
	textures[1] = tiles[1];
	textures[2] = items[0];
	textures[3] = items[1];
	textures[4] = items[2];


	AABB mush; int itemHeight = 16, itemWidth = 16;
=======
	AABB mush; int itemHeight = 16, itemWidth = 16; 
>>>>>>> parent of e38aa6c... updates to enable animation
	int itemX = randr(0, 100), itemY = randr(0, 100);
	mush.x = randr(100, 400); mush.y = randr(100, 400);
	mush.h = itemHeight; mush.w = itemWidth;
	mush.image = glTexImageTGAFile("mush.tga", NULL, NULL);
	bool collided = false;

	//character animations
	AABB character;
	int charHeight = 16;
	int charWidth = 32;
	int charX = 0;
	int charY = 0;
	character.x = charX; 	character.y = charY;
	character.h = charHeight; 	character.w = charWidth;
	character.image = glTexImageTGAFile("idleAnim.tga", &charWidth, &charHeight);

	//screen information
	int screenWidth = 640;
	int screenCenterWidth = screenWidth / 2;
	int screenHeight = 480;
	int screenCenterHeight = screenHeight / 2;
	AABB camera;
	int camX = 0; int camY = 0;
	camera.x = camX; camera.y = camY, camera.w = screenWidth, camera.h = screenHeight;


	//change world array to work off of Tile structs, makes it easier for overlaying items
	AABB worldArray[40][40];
	for (int i = 0; i <= 40; i++){
		for (int j = 0; j <= 40; j++){
			worldArray[i][j].w = tileWidth; worldArray[i][j].h = tileHeight;
			worldArray[i][j].x = i * 16; worldArray[i][j].y = j * 16;

			if (i % 2 == 0 || j % 2 == 0){
				worldArray[i][j].image = tiles[0];
			}
			else {
				worldArray[i][j].image = tiles[1];
			}

		}
	}
	//max world size is 640x640

	Uint32 lastFrameMs;
	Uint32 currentFrameMs = SDL_GetTicks();

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

		//if esc quit game
		if (kbState[SDL_SCANCODE_ESCAPE]){ shouldExit = true; }

		//character controls
		if (!kbState[SDL_SCANCODE_LEFT] && kbPrevState[SDL_SCANCODE_LEFT]){
			if (character.x > 0) character.x -= 16;
		}
		else if (!kbState[SDL_SCANCODE_RIGHT] && kbPrevState[SDL_SCANCODE_RIGHT]){
			if (character.x < 640 - tileWidth) character.x += 16;
		}
		if (!kbState[SDL_SCANCODE_UP] && kbPrevState[SDL_SCANCODE_UP]){
			if (character.y > 0) character.y -= 16;
		}
		else if (!kbState[SDL_SCANCODE_DOWN] && kbPrevState[SDL_SCANCODE_DOWN]){
			if (character.y < 480 - tileHeight * 2)character.y += 16;
		}
		//character controls end

		//camera controls
		if (kbState[SDL_SCANCODE_A]){
			if (mush.x > 0) mush.x -= 8;
		}
		else if (kbState[SDL_SCANCODE_D]){
			if (mush.x < 640 - tileWidth) mush.x += 8;
		}
		if (kbState[SDL_SCANCODE_W]){
			if (mush.y > 0) mush.y -= 8;
		}
		else if (kbState[SDL_SCANCODE_S]){
			if (mush.y < 480 - tileHeight * 2)mush.y += 8;
		}
		//camera controls end

		//mush controls
		if (kbState[SDL_SCANCODE_J]){
			if (camera.x > 0) camera.x -= 8;
		}
		else if (kbState[SDL_SCANCODE_L]){
			if (camera.x < 640 - tileWidth) camera.x += 8;
		}
		if (kbState[SDL_SCANCODE_I]){
			if (camera.y > 0) camera.y -= 8;

		}
		else if (kbState[SDL_SCANCODE_K]){
			if (camera.y < 480 - tileHeight * 2)camera.y += 8;
		}
		//mush controls end


		for (int i = 0; i <= 40; i++){
			for (int j = 0; j <= 40; j++){
				if (AABBIntersect(&camera, &worldArray[i][j])){
					glDrawSprite(worldArray[i][j].image, worldArray[i][j].x - camera.x, worldArray[i][j].y - camera.y, tileHeight, tileWidth);
				}

			}

		}
		if (AABBIntersect(&character, &mush)){ collided = true; }
		if (collided){
			mush.x = randr(0, 600); mush.y = randr(0, 400); collided = false;
			switch (randr(0, 1)){
			case 0: mush.image = items[1]; break;
			case 1: mush.image = items[2]; break;
			default: mush.image = items[0]; break;
			}
		}
		glDrawSprite(mush.image, mush.x, mush.y, mush.w, mush.h);
		glDrawSprite(character.image, character.x, character.y, character.h, character.w);

		SDL_GL_SwapWindow(window);
	}
	SDL_Quit();
	return 0;
}