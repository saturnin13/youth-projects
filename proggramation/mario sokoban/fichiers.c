#include <stdio.h>
#include <stdlib.h>
#include <SDL.h>
#include <SDL_image.h>

#include "constantes.h"
#include "jeu.h"
#include "fichiers.h"

void charger(int tableau[][NB_BLOC_HAUTEUR],int niveaux)
{
    FILE *fichier=NULL;
    char resultat[NB_BLOC_LARGEUR*NB_BLOC_HAUTEUR+1]={0};
    int numeroLigne=0, caractereActuel=0;
    int i=0, j=0;

    fichier=fopen("niveaux.lvl","r");
    while(numeroLigne!=niveaux)
    {
        caractereActuel=fgetc(fichier);
        if(caractereActuel=='\n')
            numeroLigne++;
    }

    fgets(resultat,NB_BLOC_LARGEUR*NB_BLOC_HAUTEUR+1,fichier);

    for(i=0;i<NB_BLOC_HAUTEUR;i++)
    {
        for(j=0;j<NB_BLOC_LARGEUR;j++)
        {
            switch(resultat[(i*NB_BLOC_HAUTEUR)+j])
            {
                case '0':
                    tableau[i][j]=VIDE;
                    break;
                case '1':
                    tableau[i][j]=MUR;
                    break;
                case '2':
                    tableau[i][j]=CAISSE;
                    break;
                case '3':
                    tableau[i][j]=OBJECTIF;
                    break;
                case '4':
                    tableau[i][j]=MARIO;
                    break;
                case '5':
                    tableau[i][j]=CAISSE_OK;
                    break;
            }
        }
    }
    fclose(fichier);
}
void sauvegarder(int tableau[][NB_BLOC_HAUTEUR])
{
    FILE *fichier=NULL;
    char stockage=0;
    int i=0, j=0;

    fichier=fopen("niveaux.lvl","a");
    fputc('\n',fichier);

    for(i=0;i<NB_BLOC_HAUTEUR;i++)
    {
        for(j=0;j<NB_BLOC_LARGEUR;j++)
        {
            fprintf(fichier,"%d",tableau[i][j]);
        }
    }
}

int nombreDeLigne()
{
    FILE *fichier=NULL;
    int numeroLigne=0, caractereActuel=0;

    fichier=fopen("niveaux.lvl","r");
    while(caractereActuel!=EOF)
    {
        caractereActuel=fgetc(fichier);
        if(caractereActuel=='\n')
            numeroLigne++;
    }
    return numeroLigne;
}
