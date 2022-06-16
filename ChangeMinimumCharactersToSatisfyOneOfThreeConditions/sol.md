# [1737. Change Minimum Characters to Satisfy One of Three Conditions](https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/)

Well, I messed it up bad. Had to look at solution.

The ideas simple. For conditions 1&2 since all characters in either string should be strictly 
greater than the other one, we can create a split in the sequence of characters. 

```
s1 = "a b b c d d e f f"
s2 = "a b b d e g"
   a b c d e f g h i j k l m n o p q r s t u v w x y z
s1 1 2 1 2 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
s2 1 2 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
          | - split here
```

Consider above example. If we assume s1 to be greater than s2, and say that s1 will only use 
characters greater than equal to `e`, then s2 will only include characters lesser than `e`. So, 
for this case the number of swaps would be `count of all characters greater than 'e' in s2 + 
count of all characters smaller than 'e' in s1`. We do this for all characters, once assuming s1 
to be greater and otherwise assuming s2 to be larger. Since all counts will be `>0` we'll use 
**_prefix sum arrays_** to speed-up sum calculations.

For condition three, we find characters with highest number of occurances, and use it to find 
minimum number of swaps needed for converting entire string with one character.