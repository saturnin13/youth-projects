#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    FILE* fichier = NULL;

    fichier = fopen("text.txt", "a+");

    if (fichier != NULL)
    {
        fputc('s', fichier);
        fputs("alut les bouffons\nComment allez-vous ?\n", fichier);
        fclose(fichier);// On peut lire et écrire dans le fichier
    }
    else
    {
        printf("Impossible d'ouvrir le fichier text.txt");// On affiche un message d'erreur si on veut
    }
    rename("text.txt", "text salut.txt");
    remove("text salut.txt");

    return 0;
}

