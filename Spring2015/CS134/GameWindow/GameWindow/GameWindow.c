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

unsigned int
randr(unsigned int min, unsigned int max)
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

int currentFrameAdvance(int currentFrame){
	currentFrame++;
	if (currentFrame > 1){ return 0; }
	return currentFrame;
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
	char shouldExit = 0;
	//tiles for world
	GLuint enderStoneTex;
	GLuint obsidianTex;
	GLuint sandStoneTex;

	//character animations
	GLuint characterIdleTex;

	//background animation
	GLuint spaceOneTex;
	GLuint spaceTwoTex;
	GLuint spaceThreeTex;

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

	//tiles for world 16x16
	enderStoneTex = glTexImageTGAFile("enderStone.tga", NULL, NULL);
	obsidianTex = glTexImageTGAFile("obsidian.tga", NULL, NULL);
	sandStoneTex = glTexImageTGAFile("sandStone.tga", NULL, NULL);
	int tileHeight = 16;
	int tileWidth = 16;
	GLuint tiles[2];
	tiles[0] = enderStoneTex;
	tiles[1] = obsidianTex;
	tiles[2] = sandStoneTex;
	AABB worldArray[40][40];
	AABB tile; tile.w = tileWidth, tile.h = tileHeight;
	for (int i = 0; i <= 40; i++){
		for (int j = 0; j <= 40; j++){
			AABB tileCopy = tile; tileCopy.x = i * 16; tileCopy.y = j * 16;
			if (i % 2 == 0 || j % 2 == 0){ 
				tileCopy.image = tiles[0];
				worldArray[i][j] = tileCopy; 
			}
			else { 
				tileCopy.image = tiles[1];
				worldArray[i][j] = tileCopy; 
			}
		}
	}

 	//max world size is 640x640

	//character animations
	GLuint walkAnim[1];
	walkAnim[0] = glTexImageTGAFile("walkOne.tga", NULL, NULL);
	walkAnim[1] = glTexImageTGAFile("walkTwo.tga", NULL, NULL);
	characterIdleTex = glTexImageTGAFile("idleAnim.tga", NULL, NULL);
	GLuint currentCharTex = characterIdleTex;
	int currentCharFrame = 0;
	int charHeight = 16;
	int charWidth = 31;
	int charX = 0;
	int charY = 0;

	//background animation
	GLuint backgroundAnim[2];
	backgroundAnim[0] = glTexImageTGAFile("space1.tga", NULL, NULL);
	backgroundAnim[1] = glTexImageTGAFile("space2.tga", NULL, NULL);
	backgroundAnim[2] = glTexImageTGAFile("space3.tga", NULL, NULL);
	spaceOneTex = backgroundAnim[0];
	spaceTwoTex = backgroundAnim[1];
	spaceThreeTex = backgroundAnim[2];
	int spaceHeight = 320;
	int spaceWidth = 256;
	int currentBackgroundFrame = 0;

	//screen information
	int screenWidth = 640;
	int screenCenterWidth = screenWidth / 2;
	int screenHeight = 480;
	int screenCenterHeight = screenHeight / 2;
	AABB camera;
	camera.x = charX; camera.y = charY, camera.w = screenWidth, camera.h = screenHeight;

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
			//update positions, animations of sprites
			if (kbState[SDL_SCANCODE_LEFT]){
				//if (deltaTime > 5000.0f){ currentCharFrame = currentFrameAdvance(currentCharFrame); }
				//currentCharTex = walkAnim[currentCharFrame];
				if (charX > 0) charX -= 8;
			}
			else if (kbState[SDL_SCANCODE_RIGHT]){
				//if (deltaTime > 5000.0f){ currentCharFrame = currentFrameAdvance(currentCharFrame); }
				//currentCharTex = walkAnim[currentCharFrame];
				if (charX < 640-tileWidth) charX+=8;
			}
			if (kbState[SDL_SCANCODE_UP]){
				//if (deltaTime > 5000.0f){ currentCharFrame = currentFrameAdvance(currentCharFrame); }
				//currentCharTex = walkAnim[currentCharFrame];
				if( charY > 0) charY-=8;
			}
			else if (kbState[SDL_SCANCODE_DOWN]){
				//if (deltaTime > 5000.0f){ currentCharFrame = currentFrameAdvance(currentCharFrame); }
				//currentCharTex = walkAnim[currentCharFrame];
				if (charY < 480 - tileHeight*2)charY += 8;
			}
			//update position of camera

			//draw backgrounds
			//if (deltaTime > 100000.0f){ currentBackgroundFrame = currentFrameAdvance(currentBackgroundFrame); }
			//glDrawSprite(backgroundAnim[currentBackgroundFrame],charX,charY,NULL,NULL);
			
			//draw sprites
			
			for (int i = 0; i <= 40; i++){
				for (int j = 0; j <= 40; j++){
					//randomly choose tiles for each space.
					if (AABBIntersect(&camera, &worldArray[i][j])){ glDrawSprite(worldArray[i][j].image, worldArray[i][j].x - charX, worldArray[i][j].y - charY, tileHeight, tileWidth); }
					
				}
				
			}
			glDrawSprite(currentCharTex, charX, charY, charHeight, charWidth); //draw the character
			

			//draw foregrounds

			
			
			//present to player
			SDL_GL_SwapWindow(window);
		}
	SDL_Quit();
	return 0;
}

