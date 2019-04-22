#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

char lireCaractere();
char comparerCaractere(char caractere,char mot[],int caractereTrouver[]);
int gagne(int valeurTrouve[],int taille);
void changerMot(int ValeurTrouve[],char mot[],char montrer[],int taille);
void choisirMot(char *motSecret);
int nombreAleatoire(int nombreMax);
void initialiserMotTrouve(char mot[],int taille);

int main()
{
    char recommencer[3]={0};

    printf("bienvenue dans le pendu !!!!!!\n\n");

do
{
    int coupRestant=10;int difficulte=0;
    char motSecret[100]={0};
    int gagner=0;
    char motTrouver[100]={0};
    int caractereTrouver[100]={0};
    char caractereJoueur="A";
    int longueurMot=0;
    int i=0;


    choisirMot(motSecret);
    longueurMot=strlen(motSecret);
    initialiserMotTrouve(motTrouver,longueurMot);
    printf("qu'elle niveaux de difficulté choisissez-vous ?\n(entre 0 et 9)");
    scanf("%d",&difficulte);
    if(difficulte>9){difficulte=9;}
    coupRestant=coupRestant-difficulte;

    while(gagner==0 && coupRestant!=0)
    {
        printf("il vous reste %d coup a jouer\n",coupRestant);
        printf("quel est le mot secret? %s\n",motTrouver);
        printf("proposer une lettre:");
        if(coupRestant==10-difficulte){scanf("%s",&caractereJoueur);}//lireCaractere utilise le caractere entre par le joueur du niveau de difficulté.
        else{caractereJoueur=lireCaractere();}

        if(!comparerCaractere(caractereJoueur,motSecret,caractereTrouver))
        {
            coupRestant--;
        }
        printf("\n\n");
        gagner=gagne(caractereTrouver,longueurMot);
        changerMot(caractereTrouver,motSecret,motTrouver,longueurMot);
    };
    if(gagner==1)
    {
        printf("bravo tu a gagne!!!!!!!!!!!!!!!!!!!!!!!!!!!\nLe mot etait bien \"%s\"\n",motSecret);
    }
    else
    {
        printf("Dommage le mot etait \"%s\" tu reussiras peut-etre la prochaine fois :)\n\n",motSecret);
    }
    printf("voulait-vous recommencer???\n");
    scanf("%s",recommencer);
    printf("\n");

}while(recommencer[0]=='o' && recommencer[1]=='u' && recommencer[2]=='i');
    return 0;
}
char lireCaractere()
{
    char caractere = 0;
    caractere = getchar(); // On lit le premier caractère
    caractere = toupper(caractere); // On met la lettre en majuscule si elle ne l'est pas déjà
    while (getchar() != '\n') ;// On lit les autres caractères mémorisés un à un jusqu'au \n (pour les effacer)
    return caractere; // On retourne le premier caractère qu'on a lu
}
char comparerCaractere(char caractere,char mot[],int caractereTrouver[])
{
    int juste=0;
    int i=0;
    int longueurChaine=strlen(mot);

    caractere=toupper(caractere);

    for(i=0;i<longueurChaine;i++)
    {
        if(mot[i]==caractere)
        {
            caractereTrouver[i]=1;
            juste=1;
        }
    };


    return juste;
}
int gagne(int valeurTrouve[],int taille)
{
    int gagne=1;
    int i=0;

    for(i=0;i<taille;i++)
    {
        if(!valeurTrouve[i])
        {
            gagne=0;
        }
    }
    return gagne;
}
void changerMot(int ValeurTrouve[],char mot[],char montrer[],int taille)
{
    int i=0;

    for(i=0;i<taille;i++)
    {
        if(ValeurTrouve[i])
        {
            montrer[i]=mot[i];
        }
    }
}
void choisirMot(char *motSecret)
{
    FILE* dico=NULL;
    char caractereLu='a';
    int nombreMot=0;
    int nombreAlea=0;
    int motActuelle=0;

    dico=fopen("mot.txt","r");

    do
    {
        caractereLu=fgetc(dico);
        if(caractereLu=='\n')
        {
            nombreMot++;
        }
    }while(caractereLu!=EOF);
    rewind(dico);

    nombreAlea=nombreAleatoire(nombreMot);
    while(nombreAlea!=motActuelle)
    {
        caractereLu=fgetc(dico);
        if(caractereLu=='\n')
        {
            motActuelle++;
        }
    }
    fgets(motSecret,100,dico);
    motSecret[strlen(motSecret)-1]='\0';
    fclose(dico);
}
int nombreAleatoire(int nombreMax)
{
    srand(time(NULL));
    return (rand() % nombreMax);
}
void initialiserMotTrouve(char mot[],int taille)
{
    int i=0;
    for(i=0;i<taille;i++)
    {
        mot[i]='*';
    }
}
