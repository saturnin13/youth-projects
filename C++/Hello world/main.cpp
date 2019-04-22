#include <iostream>
using namespace std;

int main()
{
   double a(0), b(0); //Déclaration des variables utiles

   cout << "Bienvenue dans le programme d'addition a+b !" << endl;

   cout << "Donnez une valeur pour a : "; //On demande le premier nombre
   cin >> a;

   cout << "Donnez une valeur pour b : "; //On demande le deuxième nombre
   cin >> b;

   double const resultat(a + b); //On effectue l'opération

   cout << a << " + " << b << " = " << resultat << endl;
   //On affiche le résultat

   return 0;

}
