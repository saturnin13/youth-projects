#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>

void pause();

int main ( int argc, char** argv )
{
    SDL_Surface *ecran=NULL, *lignes[256]={0};
    int i=0;
    SDL_Rect position;

    SDL_Init(SDL_INIT_VIDEO);

    ecran=SDL_SetVideoMode(500, 255, 32, SDL_HWSURFACE);

    for(i=0;i<256;i++)
    {
        lignes[i]=SDL_CreateRGBSurface(SDL_HWSURFACE,500, 1, 32,0,0,0,0);
    }
    for(i=0;i<256;i++)
    {
        position.x=0;
        position.y=i;
        SDL_FillRect(lignes[i],NULL,SDL_MapRGB(ecran->format,i,0,i));
        SDL_BlitSurface(lignes[i],NULL,ecran,&position);
    }

    SDL_Flip(ecran);

    pause();

    for(i=0;i<256;i++)
    {
        SDL_FreeSurface(lignes[i]);
    }

    SDL_Quit();

    return EXIT_SUCCESS;
}
void pause()
{
    int continuer = 1;
    SDL_Event event;

    while (continuer)
    {
        SDL_WaitEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
        }
    }
}
