/*
----------
Sorting
----------

Priority Queue (PQ)
- sort variants
	-> Unordered list L (with n elements) -> put into PQ -> repeatedly remove smallest element to "build" L', which is ordered

- PQ as heap 
	=> Heapsort 
		=> O(n log(n))

- PQ is unordered list 
		=> "Selection Sort"
			=> insert() is O(1), removeMin() is O(n)
				=> O(n^2)

- PQ is ordered list 
		=> "Insertion Sort"
			=> insert() is O(n), removeMin is O(1)
				=> O(n^2)

------------------------------
Can a sort be done "in place"?
------------------------------
*/

template <typename T>
void insertionSort(vector <T> & data)
{
	int i, j;

	//outer loop moves boundary between sorted and unsorted portion
	// of the array over one slot at a time
	
	for(i = 1; i < data.size(); i++)
	{
		T tmp = data[i];

		//search to the left for the correct slot and shift while doing so 
		for(j = i; j > 0 && tmp < data[j - 1]; j--)
		{
			data[j] = data[j - 1];
		}
	
		data[j] = tmp;
	}
}
/*
----------
Merge Sort
----------
Relies on merge(array1[], array2[], array3[]) => (destination, sorted source, other sorted source)
	=> Takes two sorted lists and combines them into one

Idea: divide original array into smaller and smaller pieces until merging is trivial, then recombine
      into larger and larger pieces (through merging) until we have L', the sorted version of L
	=> Divide and Conquer


mergeSort(data[])
{
	if data has at least 2 elements
	{
		mergeSort(left_half_data[])
		mergeSort(right_half_data[])
		merge(both_halves)
	}
}

merge(array1[], array2[], array3[])
{
	i1, i2, i3;
	//current indices into arrays

	while(i2 < array2.size() && i3 < array3.size())
	{	
		if(array[i2] < array[i3])
			array[i1++] = array2[i2++];	
		else
			array1[i1++] = array3[i3++];
	}
	
	while array2 contains data
	{
		array1[i1++] = array2[i2++];
	}	
	
	while array3 contains data
	{
		array1[i1++] = array3[i3++];
	}

}
*/
