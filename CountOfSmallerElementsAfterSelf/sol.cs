// C# implementation to find
// number of Even Parity numbers
// in a subarray and performing updates
using System;

class GFG{

public static int MAX = 1000;

// Function that returns true if count
// of set bits in x is even
static bool isEvenParity(int x)
{

	// parity will store the
	// count of set bits
	int parity = 0;

	while (x != 0)
	{
		if ((x & 1) != 0)
			parity++;

		x = x >> 1;
	}

	if (parity % 2 == 0)
		return true;
	else
		return false;
}

// A utility function to get
// the middle index
static int getMid(int s, int e)
{
	return s + (e - s) / 2;
}

// Recursive function to get the number
// of Even Parity numbers in a given range
static int queryEvenParityUtil(int[] segmentTree,
							int segmentStart,
							int segmentEnd,
							int queryStart,
							int queryEnd, int index)
{

	// If segment of this node is a part
	// of given range, then return
	// the number of Even Parity numbers
	// in the segment
	if (queryStart <= segmentStart &&
		queryEnd >= segmentEnd)
		return segmentTree[index];

	// If segment of this node
	// is outside the given range
	if (segmentEnd < queryStart ||
		segmentStart > queryEnd)
		return 0;

	// If a part of this segment
	// overlaps with the given range
	int mid = getMid(segmentStart, segmentEnd);
	return queryEvenParityUtil(segmentTree, segmentStart,
							mid, queryStart, queryEnd,
							2 * index + 1) +
		queryEvenParityUtil(segmentTree, mid + 1,
							segmentEnd, queryStart,
							queryEnd, 2 * index + 2);
}

// Recursive function to update
// the nodes which have the given
// index in their range
static void updateValueUtil(int[] segmentTree,
							int segmentStart,
							int segmentEnd, int i,
							int diff, int si)
{

	// Base Case:
	if (i < segmentStart || i > segmentEnd)
		return;

	// If the input index is in range
	// of this node, then update the value
	// of the node and its children
	segmentTree[si] = segmentTree[si] + diff;

	if (segmentEnd != segmentStart)
	{
		int mid = getMid(segmentStart, segmentEnd);
		updateValueUtil(segmentTree, segmentStart, mid,
						i, diff, 2 * si + 1);
		updateValueUtil(segmentTree, mid + 1,
						segmentEnd, i, diff,
						2 * si + 2);
	}
}

// Function to update a value in the
// input array and segment tree
static void updateValue(int[] arr, int[] segmentTree,
						int n, int i, int new_val)
{

	// Check for erroneous input index
	if (i < 0 || i > n - 1)
	{
		Console.WriteLine("Invalid Input");
		return;
	}

	int diff = 0, oldValue = 0;

	oldValue = arr[i];

	// Update the value in array
	arr[i] = new_val;

	// Case 1: Old and new values
	// both are Even Parity numbers
	if (isEvenParity(oldValue) &&
		isEvenParity(new_val))
		return;

	// Case 2: Old and new values
	// both not Even Parity numbers
	if (!isEvenParity(oldValue) &&
		!isEvenParity(new_val))
		return;

	// Case 3: Old value was Even Parity,
	// new value is non Even Parity
	if (isEvenParity(oldValue) &&
	!isEvenParity(new_val))
	{
		diff = -1;
	}

	// Case 4: Old value was non Even Parity,
	// new_val is Even Parity
	if (!isEvenParity(oldValue) &&
		!isEvenParity(new_val))
	{
		diff = 1;
	}

	// Update the values of
	// nodes in segment tree
	updateValueUtil(segmentTree, 0, n - 1, i, diff, 0);
}

// Return number of Even Parity numbers
static void queryEvenParity(int[] segmentTree, int n,
							int queryStart,
							int queryEnd)
{
	int EvenParityInRange = queryEvenParityUtil(
		segmentTree, 0, n - 1, queryStart, queryEnd, 0);

	Console.WriteLine(EvenParityInRange);
}

// Recursive function that constructs
// Segment Tree for the given array
static int constructSTUtil(int[] arr, int segmentStart,
						int segmentEnd,
						int[] segmentTree, int si)
{

	// If there is one element in array,
	// check if it is Even Parity number
	// then store 1 in the segment tree
	// else store 0 and return
	if (segmentStart == segmentEnd)
	{

		// if arr[segmentStart] is
		// Even Parity number
		if (isEvenParity(arr[segmentStart]))
			segmentTree[si] = 1;
		else
			segmentTree[si] = 0;

		return segmentTree[si];
	}

	// If there are more than one elements,
	// then recur for left and right subtrees
	// and store the sum of the
	// two values in this node
	int mid = getMid(segmentStart, segmentEnd);
	segmentTree[si] = constructSTUtil(arr, segmentStart, mid,
									segmentTree, si * 2 + 1) +
					constructSTUtil(arr, mid + 1, segmentEnd,
									segmentTree, si * 2 + 2);
	return segmentTree[si];
}

// Function to construct a segment
// tree from given array
static int[] constructST(int[] arr, int n)
{

	// Height of segment tree
	int x = (int)(Math.Ceiling(Math.Log(n, 2)));

	// Maximum size of segment tree
	int max_size = 2 * (int)Math.Pow(2, x) - 1;

	int[] segmentTree = new int[max_size];

	// Fill the allocated memory st
	constructSTUtil(arr, 0, n - 1, segmentTree, 0);

	// Return the constructed segment tree
	return segmentTree;
}

// Driver Code
public static void Main()
{
	int[] arr = { 18, 15, 8, 9, 14, 5 };
	int n = arr.Length;

	// Build segment tree from given array
	int[] segmentTree = constructST(arr, n);

	// Query 1: Query(start = 0, end = 4)
	int start = 0;
	int end = 4;
	queryEvenParity(segmentTree, n, start, end);

	// Query 2: Update(i = 3, x = 11),
	// i.e Update a[i] to x
	int i = 3;
	int x = 11;
	updateValue(arr, segmentTree, n, i, x);

	// Query 3: Query(start = 0, end = 4)
	start = 0;
	end = 4;
	queryEvenParity(segmentTree, n, start, end);
}
}

// This code is contributed by ukasp
