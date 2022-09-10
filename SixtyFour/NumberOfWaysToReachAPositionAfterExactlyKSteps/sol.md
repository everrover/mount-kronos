# [2400. Number of Ways to Reach a Position After Exactly k Steps](https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/)

Have used distance for memoization. The number of ways to achieve distance of `-1` is same as that for `1`.

Hence, recursive relation is.
```
util(k,d) = util(k-1, abs(d-1)) + util(k-1, d+1), 
          = 1, k==d
          = 0, k<d
```