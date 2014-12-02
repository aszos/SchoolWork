/*
----------
Quicksort
----------
Also a divide and conquer algorithm, like mergesort, to sort array A
Key difference:
	- Partition stage divides input based on comparisons to "pivot" value (aka bound)
		=> If A[i] <= pivot, go to left
		=> If A[i] > pivot, go to right

--------------------
Array A
_________________
|_|_|_|_|_|_|_|_|

--------------------

--------------------------
	Array A'

A left           A right
_________       _________
|_|_|_|_| pivot |_|_|_|_|

{A left} < pivot
{A right} > pivot
--------------------------

quicksort(data[])
{
	if data has >= 2 elements
	{
		partition data into 2 subarrays using pivot values

	quicksort(subarray 1)
	quicksort(subarray 2)
	concatenate subarrays
	}

}

In mergesort, all of the work is done in merging.
In quicksort, all of the work is done in partitioning.
Notice how these are practically inverse sorts!

------------
Pivot Choice
------------
The pivot choice is important to obtain balanced divisions.
- The median would divide input equally => This is costly! (O(n))
- Pick random value from input => Good but somewhat costly! 
- Pick first value from input => Super cheap, but bad if input is already mostly sorted
- Pick the middle index of the array => Might be better if partially sorted, still cheap if random order
Solution:
- Pick the median of three random values, sampling based strategy => Good compromise for robustness to partial ordering 
	=> median of A[first], A[last], A[middle]

------------------------------------------------------------
quicksort(array)
{
	if length(array) > 1
		pivot := select any element of array
		left := first index of array
		right := last index of arary
		while left <= right
			while array[left] < pivot
				left++
			while array[right] > pivot
				right++
			if left <= right
				swap array[left] with array[right]
					left++
					right--

		quicksort(array from first index to right)
		quicksort(array from left index to last index)
}

------------------------------------------------------------
Array A
_________________________________
| 7 | 2 | 9 | 4 | 3 | 8 | 6 | 1 | 
---------------------------------
 0   1   2   3   4   5   6   7		

first = 0
last = 7
middle = 3
pivot value = A[3] = 4

_________________________________
| 7 | 2 | 9 | 4 | 3 | 8 | 6 | 1 | 
---------------------------------
 0   1   2   3   4   5   6   7		
 ^           ^               ^
 L           M               R

A[L] > A[M] && A[R] < A[M]
Swap!

_________________________________
| 1 | 2 | 9 | 4 | 3 | 8 | 6 | 7 | 
---------------------------------
 0   1   2   3   4   5   6   7		
     ^       ^           ^    
   ->L       M           R<-

...continues until...

_________________________________
| 1 | 2 | 9 | 4 | 3 | 8 | 6 | 7 | 
---------------------------------
  0   1   2   3   4   5   6   7		
          ^   ^   ^    
          L   M   R

A[L] > A[M] && A[R] < A[M]
Swap!
_________________________________
| 1 | 2 | 3 | 4 | 9 | 8 | 6 | 7 | 
---------------------------------
  0   1   2   3   4   5   6   7		
              ^       
             LMR

Done partitioning!

Now we:
quicksort(array from first index to right)
quicksort(array from left index to last index)

Recurse until we're sorted.


Quicksort is expected(average) O(n log n) and worst case is O(n^2).
Mergesort is worse case O(n log n), but takes memory.
*/
