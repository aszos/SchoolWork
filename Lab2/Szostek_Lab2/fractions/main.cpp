#include <cstdlib>
#include <iostream>
#include <cstring>
using namespace std;

class Fraction
{
	public:
	
	//---constructors and destructors---//	
	Fraction();
	Fraction(int, int);
	~Fraction();
	
	//---public functions---//
	friend Fraction operator+(Fraction &, Fraction &);	
	friend Fraction operator-(Fraction &, Fraction &);	
	friend Fraction operator*(Fraction &, Fraction &);	
	friend Fraction operator/(Fraction &, Fraction &);	
	friend void reduce(Fraction &);		
	friend ostream & operator <<(ostream &outputStream, const Fraction & a);	
	friend istream & operator >>(istream &in, Fraction & a);	

	void print()
	{
		cout << "(" << numerator  << " / " << denominator << ")" << endl;
	}

	private:
	//---variables---//
	int numerator;
	int denominator;
};


Fraction::Fraction()
{
	numerator = 1;
	denominator = 1 ;
}

Fraction::Fraction(int x, int y)
{
	numerator = x;
	denominator = y;
}

Fraction::~Fraction(){}

//---Member Functions---//

int getGCD(int a, int b)
{
	if(b==0)	
		return a;
	else
		return getGCD(b, a % b);	
}

//Reducing Fractions
void reduce(Fraction & a)
{
	int gcd = getGCD(a.numerator, a.denominator);
	a = Fraction(a.numerator / gcd, a.denominator / gcd);
}

//Adding
Fraction operator+(Fraction & a, Fraction & b)
{
	int newDenominator = a.denominator * b.denominator;
	int aNumerator = a.numerator * b.denominator;
	int bNumerator = b.numerator * a.denominator; 
	Fraction z(aNumerator + bNumerator, newDenominator);
	reduce(z);
	return z;	
}

//Subtracting
Fraction operator-(Fraction & a, Fraction & b)
{
	Fraction c(- b.numerator, b.denominator);
	Fraction d = a + c;
	reduce(d);
	return d;

}

//Multiplying
Fraction operator*(Fraction & a, Fraction & b)
{
	Fraction c(a.numerator * b.numerator, a.denominator * b.denominator);
	reduce(c);
	return c;
}

//Dividing
Fraction operator/(Fraction & a, Fraction & b)
{
	Fraction c(a.numerator *  b.denominator, a.denominator * b.numerator);
	reduce(c);
	return c;
}

//Printing out Fractions
ostream & operator <<(ostream &outputStream, const Fraction & a)	
{
	cout << "(" << a.numerator  << " / " << a.denominator << ")";
	return outputStream; 
}

istream & operator>> (istream &in, Fraction & a)
{
	cout << "Enter in a numerator." << endl;
	in >> a.numerator;
	cout << "Enter in a denominator." << endl;	
	in >> a.denominator;
	return in; 
}
	

void printSpacer()
{
	cout <<"--------------------------------------------------"<< endl; 
}

int main()
{
	cout << "------------Fraction Class Test Program-----------" << endl;
	Fraction a(1,2);
	Fraction b(1,5);
	Fraction c(1,9);
	
	cout << "A = " << a << endl; 
	cout << "B = " << b << endl; 
	cout << "C = " << c << endl; 
	printSpacer();
	
	cout << "A + B = " << a + b << endl;
	cout << "B + C = " << b + c << endl;
	cout << "C + A = " << c + a << endl;
	printSpacer();

	cout << "A - B = " << a - b << endl;
	cout << "B - C = " << b - c << endl;
	cout << "C - A = " << c - a << endl;
	printSpacer();

	cout << "A * B = " << a * b << endl;
	cout << "B * C = " << b * c << endl;
	cout << "C * A = " << c * a << endl;
	printSpacer();

	cout << "A / B = " << a / b << endl;
	cout << "B / C = " << b / c << endl;
	cout << "C / A = " << c / a << endl;
	printSpacer();

	cin >> a;
	cout << "A = ";
	a.print();
	printSpacer();

}
