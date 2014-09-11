/* 
Providing that the declarations, 
	int intArray[] = {1, 2, 3}, *p = intArray;
have been made, what will be the content of intArray and p after executing individually?

1. *p++;
   Answer:
	int intArray = {1, 2, 3};
	p = 2;

2. (*p)++;
   Answer:
	int intArray = {2, 2, 3};
	p = 2;

3. *p++; (*p)++;
   Answer:
	int intArray = {1, 3, 3};
	p = 3;
*/
