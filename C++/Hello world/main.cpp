#include <iostream>
using namespace std;

int main()
{
   double a(0), b(0); //D�claration des variables utiles

   cout << "Bienvenue dans le programme d'addition a+b !" << endl;

   cout << "Donnez une valeur pour a : "; //On demande le premier nombre
   cin >> a;

   cout << "Donnez une valeur pour b : "; //On demande le deuxi�me nombre
   cin >> b;

   double const resultat(a + b); //On effectue l'op�ration

   cout << a << " + " << b << " = " << resultat << endl;
   //On affiche le r�sultat

   return 0;

}
