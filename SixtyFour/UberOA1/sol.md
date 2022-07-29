## Uber OA #1

> Please note that these questions are what I pulled from Codeforces, Codechef and HackerRank.

### Question #1

You are given two strings a and b consisting of lowercase English letters
Let a' be the concatenation of 10^250 a copies.

Find the minimum integer `i` such that `b` is a subsequence of the string `a_1'a_2'a_3'....a_i` (the first i characters in a')

Constraints: 
- 1 <= abs(a) <= 10^5
- 1 <= b <= 10^5
- `a` and `b` consists of lowercase English letters.

Example:

Input:
```
contest
son
```

Output:
```
10
```

b=son is a subsequence of the string `contestcon` (the first 10 characters in a' = contestcontestcontestcontest..), so `i=10` 
satisfies the condition.

On the other hand, b is not a subsequence of the string `contestco` (the first 9 characters in a'), so `i=9` does not satisfy 
the condition.

Similarly, any integer less than 9 does not satisfy the condition, either. Thus, the minimum integer `i` satisfying the 
condition is 10.

### Question #2

### Question #3

## Uber OA #2

### Question #1: 

You are given an integer `n`. Find out the Order Sum where Order Sum for integer `n` is the maximum sum of the difference of the
adjacent elements in all arrangement permutations of numbers from `1` to `n`.

NOTE: Difference between two elements A and B will be absolute difference `|A-B|`

Input: Contains a single integer `n`
Output: Print the maximum value of Order Sum for given `n`.
Constraints: `1 <= n <= 10^5`

Sample Testcase => n: 3

Value of Order Sum for arrangement {1,2,3} is 2 i.e `|1-2|+|2-3|=2`

Value of Order Sum for arrangement {1,3,2} is 3.

Value of Order Sum for arrangement {2,1,3) is 3.

Value of Order Sum for arrangement {2,3,1} is 3.

Value of Order Sum for arrangement {3,1,2} is 3.

Value of Order Sum for arrangement {3,2,1} is 2.

Hence, the maximum value of Order Sum for all arrangements is 3.

### Question #2: Coins and diamonds


