#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>
#include <SDL_ttf.h>


int main(int argc, char *argv[])
{
    SDL_Surface *ecran = NULL, *texte = NULL;
    SDL_Rect position;
    SDL_Event event;
    TTF_Font *police = NULL;
    SDL_Color couleurNoire = {0, 0, 255}, couleurBlanche = {0, 0, 0};
    int continuer = 1;
    int tempsActuel = 0, tempsPrecedent = 0,tempsActuelb = 0, tempsPrecedentb = 0, compteur = 0, versDroite=1, versBas=1;;
    char temps[20] = ""; /* Tableau de char suffisamment grand */


    position.x = 180;
    position.y = 210;

    SDL_Init(SDL_INIT_VIDEO);
    TTF_Init();

    ecran = SDL_SetVideoMode(1920, 1080, 32, SDL_HWSURFACE | SDL_DOUBLEBUF| SDL_FULLSCREEN );
    SDL_WM_SetCaption("Gestion du texte avec SDL_ttf", NULL);

    /* Chargement de la police */
    police = TTF_OpenFont("arial.ttf", 65);

    /* Initialisation du temps et du texte */
    tempsActuel = SDL_GetTicks();
    sprintf(temps, "Temps : %d", compteur);
    texte = TTF_RenderText_Shaded(police, temps, couleurNoire, couleurBlanche);

    while (continuer)
    {
        SDL_PollEvent(&event);
        switch(event.type)
        {
            case SDL_QUIT:
                continuer = 0;
                break;
            case SDL_KEYDOWN:
                switch(event.key.keysym.sym)
                {
                    case SDLK_ESCAPE:
                        continuer=0;
                    break;
                };
            break;
        }

        SDL_FillRect(ecran, NULL, SDL_MapRGB(ecran->format, 0, 0, 0));

        tempsActuel = SDL_GetTicks();
        tempsActuelb = SDL_GetTicks();
        if (tempsActuel - tempsPrecedent >= 100) /* Si 100 ms au moins se sont écoulées */
        {
            compteur += 100; /* On rajoute 100 ms au compteur */
            sprintf(temps, "Temps : %d", compteur); /* On écrit dans la chaîne "temps" le nouveau temps */
            SDL_FreeSurface(texte); /* On supprime la surface précédente */
            texte = TTF_RenderText_Shaded(police, temps, couleurNoire, couleurBlanche); /* On écrit la chaîne temps dans la SDL_Surface */
            tempsPrecedent = tempsActuel; /* On met à jour le tempsPrecedent */
            position.x += 1;
            position.y += 1;
        }
        if (tempsActuelb - tempsPrecedentb > 1)
        {
            if(position.x>(ecran->w-texte->w))
                versDroite=0;
            else if(position.x<=0)
                versDroite=1;

            if(versDroite)
            {
                position.x++;
            }
            else
            {
                position.x--;
            }


            if(position.y>(ecran->h-texte->h))
                versBas=0;
            else if(position.y<=0)
                versBas=1;

            if(versBas)
            {
                position.y++;
            }
            else
            {
                position.y--;
            }
            tempsPrecedentb=tempsActuelb;
        }

        SDL_BlitSurface(texte, NULL, ecran, &position); /* Blit du texte */
        SDL_Flip(ecran);

    }

    TTF_CloseFont(police);
    TTF_Quit();

    SDL_FreeSurface(texte);
    SDL_Quit();

    return EXIT_SUCCESS;
}
