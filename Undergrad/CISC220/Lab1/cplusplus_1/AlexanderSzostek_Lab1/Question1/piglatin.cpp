#include <cstdlib>
#include <iostream>
#include <cmath>
#include <fstream>
#include <string.h>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
/*
Rules of PigLatin
1. Words that begin with consonant sounds, move the first letter to the end then add "ay"
2. Words that begin with vowels or silent letters, just  add "way"
*/

//determines if the character given is a vowel
//char --> boolean
bool isVowel(char c)
{
	return 
	(c == 'a' || c  == 'e' || c == 'i' || c == 'o'|| c == 'u');
}

//this will mutate the word character array that's passed in
//char --> char
char *piglatin(char* s)
{
	//determine the first character and make space for a return value 
	char *result = new char[strlen(s) + 3];	
	char *firstChar = new char; 
	strncpy(firstChar, &s[0], 1);

	if(isVowel(*firstChar))	
	{
		//create a new word with the old word append with "way" 
		result = strcat(s, "way");	
	}		
	else
	{
		//create a new word with the old word but append first letter and add "ay"
		strncpy(result, &s[1], strlen(s) - 1);
		strcat(strcat(result, firstChar), "ay");		
	}	

	//return
	return result;
}

int main()
{
	char a[] = { 'a', 'p', 'p', 'l', 'e', '\0'}; 
	char b[] = { 'b', 'a', 'n', 'a', 'n', 'a', '\0'}; 
	char e[] = { 'e', 'g', 'g', '\0'}; 
	char i[] = { 'i', 'g', 'l', 'o' ,'o' ,'\0'}; 
	char o[] = { 'o', 'o', 'p', 's', '\0'}; 
	char u[] = { 'u', 'h', 'h', '\0'}; 
	char h[] = {};

	cout << "---- English Test Words ----" << endl;
	cout << a << endl;
	cout << b << endl;
	cout << e << endl;
	cout << i << endl;
	cout << o << endl;
	cout << u << endl;
	cout << h << endl;
	
	cout << "---- Pig Latin Words ----" << endl; 	
	cout << piglatin(a) << endl;
	cout << piglatin(b) << endl;
	cout << piglatin(e) << endl;
	cout << piglatin(i) << endl;
	cout << piglatin(o) << endl;
	cout << piglatin(u) << endl;
	cout << piglatin(h) << endl;
}
