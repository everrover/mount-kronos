# Microsoft OA on @20thJuly

---

## #1 Contiguous Subarray Count with Arithmetic Mean

You are given an array A of N integers and an integer S. Your task is to compute how many ways one can choose
a contiguous fragment of A that has an arithmetic mean equal to S. The arithmetic mean (average) of a fragment is
the sum of the elements of the fragment divided by its length. For example, the arithmetic mean of [1, 4, 4, 5] is
14/4 = 35

Write a function:
```java
class Solution {
    public int solution(int[] A, int S){
        // write your code in Java 8
        return 0;
    }
}
```

which returns the number of contiguous fragments of A whose arithmetic means are equal to S.
If the result is greater than 1,000,000,000, your function should return 1,000,000,000.

### Examples:
1. Given A = [2,1,3] and S = 2, your function should return
   3, since the arithmetic means of fragments (2), (1, 3] and
   [2, 1, 3] are equal to 2.
2. Given A = [0, 4, 3, -1] and S = 2, your function should
   return 2, since fragments [0, 4] and [4, 3, -1] have an
   arithmetic mean equal to 2.
3. Given A = [2, 1, 4) and S = 3, your function should return
   0, since there exist no contiguous fragments whose
   arithmetic mean is equal to 3.


Write an efficient algorithm for the following assumptions:
- N is an integer within the range [1..100,000;
- S is an integer within the range 1-1,000,000,000.... 1,000,000,000
- Each element of array A is an integer within
the range [-1000000000.. 1000000000].

### Solution:

We calculate a prefix sum array P of length N.

```
(P[i] - P[j-1])/(i-j+1) = S
=> P[i] = P[j-1] + (i-j+1)*S
=> P[i]-i*S = P[j-1] - (j-1)*S
=> for j<=i, all pairs which satisfy the above equation can define a range that can be used to calculate the required mean.
```
To do so, we maintain a map, `H` of {key: (count of ranges which have this sum)}. Here, `key=P[i]-i*S`. 
For `i` indexed `prefixSum`, whenever we find a new key we add it to the map. If the key already exists, it would mean that 
current `i` will pair up with `H[key]` many other prefix sums. So, we increment the existing count of this `H[key]` in the 
result and then increment `H[key]` by 1.

Hence, our result is obtained via, `dp[i]=dp[i-1]+H[P[i]-i*S]`. Here we store the result in `dp`.

Since we don't need any prefix sum of previous elements (our memoization of `key`ed hashmap is enough), we keep a `runningSum` 
only and not a prefix sum array. Same for `dp`.
---

## #2 Contiguous Subarray Count with Arithmetic Mean

We consider alphabet with only three letters: "a", "b" and "c". A string is called diverse if no three consecutive
letters are the same. In other words, a diverse string may not contain any of the strings "aaa", "bbb" or "ccc".
Write a function:
```java
class Solution {
    public String solution(int A, int B, int C){
        // write your code in Java 8
        return "";
    } 
}
```
that, given three integers A, B and C, returns any longest possible diverse string containing at most A letters 'a', at
most B letters 'b' and at most C letters 'c'. If there is no possibility of building any string, return empty string.

Examples:
- Given A = 6, B = 1 and C = 1, your function may return
   "aabaacaa". Note that "aacaabaa" would also be a correct
   answer. Your function may return any correct answer. 
- Given A = 1, B = 3 and C = 1 your function may return
   "abbcb", "bcbab", "bacbb" or any of several other strings. 
- Given A = 0, B = 1 and C = 8 your function should return
   "bcc", which is the only correct answer in this case.
  
Assume that: 0<=A,B,C<=100, A+B+C>0

### Solution:

I simply generated the combination with a greedy choice of `A>B>C` which we maintain at each recursion step. After selecting 
the first two or less characters from `A` set, and we add one from `B` set. And recurse towards next iteration with updated 
count of `A` and `B`.

Since only had three sets, I simply used equality operators to maintain `A>B>C`. If more sets were present, I could have 
used **min-heap** to maintain the relationship.  

---

## 