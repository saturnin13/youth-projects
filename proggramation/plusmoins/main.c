#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    const int MAX=100, MIN=1;
    int nombreMystere=0, joueur=0;
    srand(time(NULL));
    nombreMystere = (rand() % (MAX - MIN + 1)) + MIN;

    printf("qu'elle nombre voulez-vous choisir?");
        scanf("%d",&joueur);
    while(joueur!=nombreMystere)
    {
        if(nombreMystere<joueur)
        {
            printf("trop grand\n");
        }
        else
        {
            printf("trop petit\n");
        }
        printf("qu'elle nombre voulez-vous choisit?");
        scanf("%d",&joueur);
    };
    printf("\nbravo vous avez trouve le bon nombre\n");
}
