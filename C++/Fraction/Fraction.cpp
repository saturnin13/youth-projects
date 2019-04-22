#include <iostream>
#include <string>
#include "Fraction.h"

using namespace std;

Fraction::Fraction():num(0),den(1){}
Fraction::Fraction(int numerateur):num(numerateur),den(1){}
Fraction::Fraction(int numerateur,int denominateur):num(numerateur),den(denominateur)
{
    simplifier();
}

void Fraction::afficher(ostream &flux) const
{
    if(den!=1)
    {
        flux<<num<<"/"<<den;
    }
    else
    {
        flux<<num;
    }
}

ostream &operator<<(ostream &flux, Fraction const& fraction)
{
    fraction.afficher(flux);
    return flux;
}

Fraction& Fraction::operator+=(Fraction const& fraction2)
{
    num=fraction2.num*den+num*fraction2.den;
    den=fraction2.den*den;
    simplifier();
    return *this;
}

Fraction& Fraction::operator+=(int const& number)
{
    num=number*den+num;
    simplifier();
    return *this;
}

Fraction operator+(Fraction const& a, Fraction const& b)
{
    Fraction copie(a);
    copie+=b;
    return copie;
}

Fraction& Fraction::operator*=(Fraction const& fraction)
{
    num=num*fraction.num;
    den=den*fraction.den;
    simplifier();
    return *this;
}

Fraction operator*(Fraction const& a,Fraction const& b)
{
    Fraction copie(a);
    copie*=b;
    return copie;
}

void Fraction::simplifier()
{
    int nombre=pgcd(num,den);
    num/=nombre;
    den/=nombre;
}

int pgcd(int a, int b)
{
    while (b != 0)
    {
        const int t = b;
        b = a%b;
        a=t;
    }
    return a;
}
