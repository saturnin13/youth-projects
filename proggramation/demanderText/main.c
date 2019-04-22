#include <stdio.h>
#include <stdlib.h>
double lireDouble();
long lireLong();
int lire(char *chaine, int longueur);

int main()
{
    double nombre=0;

    nombre=lireDouble();
    printf("%f\n",nombre);

    return 0;
}s

void viderBuffer()
{
    int c = 0;
    while (c != '\n' && c != EOF)
    {
        c = getchar();
    }
}

int lire(char *chaine, int longueur)
{
    char *positionEntree = NULL;

    if (fgets(chaine, longueur, stdin) != NULL)
    {
        positionEntree = strchr(chaine, '\n');
        if (positionEntree != NULL)
        {
            *positionEntree = '\0';
        }
        else
        {
            viderBuffer();
        }
        return 1;
    }
    else
    {
        viderBuffer();
        return 0;
    }
}
long lireLong()
{
    char nombreTexte[100] = {0}; // 100 cases devraient suffire

    if (lire(nombreTexte, 100))
    {
        // Si lecture du texte ok, convertir le nombre en long et le retourner
        return strtol(nombreTexte, NULL, 10);
    }
    else
    {
        // Si problème de lecture, renvoyer 0
        return 0;
    }
}
double lireDouble()
{
    char nombreTexte[100] = {0}; // 100 cases devraient suffire
    char *position=0;

    if (lire(nombreTexte, 100))
    {
        position=strchr(nombreTexte, ',');
        if (position != NULL)
        {
            *position = '.';
        }
        return strtod(nombreTexte, NULL);// Si lecture du texte ok, convertir le nombre en long et le retourner
    }
    else
    {
        // Si problème de lecture, renvoyer 0
        return 0;
    }
}
