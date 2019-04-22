#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include <SDL_image.h>

int main(int argc, char *argv[])
{
    SDL_Surface *ecran = NULL, *zozor = NULL;
    SDL_Rect positionZozor;
    SDL_Event event;
    int continuer = 1;
    int tempsPrecedent = 0, tempsActuel = 0, versDroite=0, versBas=1;
    SDL_TimerID timer;

    SDL_Init(SDL_INIT_VIDEO|SDL_INIT_TIMER);

    ecran = SDL_SetVideoMode(640, 480, 32, SDL_HWSURFACE | SDL_DOUBLEBUF);
    SDL_WM_SetCaption("Gestion du temps en SDL", NULL);

    zozor = SDL_LoadBMP("zozor.bmp");
    SDL_SetColorKey(zozor, SDL_SRCCOLORKEY, SDL_MapRGB(zozor->format, 0, 0, 255));

    positionZozor.x = ecran->w / 2 - zozor->w / 2;
    positionZozor.y = ecran->h / 2 - zozor->h / 2;

    SDL_EnableKeyRepeat(10, 10);

    while (continuer)
    {
        SDL_PollEvent(&event); /* On utilise PollEvent et non WaitEvent pour ne pas bloquer le programme */
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
                break;
        }

        tempsActuel = SDL_GetTicks();
        if (tempsActuel - tempsPrecedent > 1)
        {
            if(positionZozor.x>560)
                versDroite=0;
            else if(positionZozor.x<=0)
                versDroite=1;

            if(versDroite)
            {
                positionZozor.x++;
            }
            else
            {
                positionZozor.x--;
            }


            if(positionZozor.y>390)
                versBas=0;
            else if(positionZozor.y<=0)
                versBas=1;

            if(versBas)
            {
                positionZozor.y++;
            }
            else
            {
                positionZozor.y--;
            }

            tempsPrecedent = tempsActuel;
        }
        else /* Si ça fait moins de 1 ms depuis le dernier tour de boucle, on endort le programme le temps qu'il faut */
        {
            SDL_Delay(1 - (tempsActuel - tempsPrecedent));
        }

        SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 255, 255, 255));
        SDL_BlitSurface(zozor, NULL, ecran, &positionZozor);
        SDL_Flip(ecran);
    }

    SDL_FreeSurface(zozor);
    SDL_Quit();

    return EXIT_SUCCESS;
}
