# [2338. Count the Number of Ideal Arrays](https://leetcode.com/problems/count-the-number-of-ideal-arrays/)

> _Stars and bars_ is the most important approach in use here. [LINK](https://cp-algorithms.com/combinatorics/stars_and_bars.html)

At position `n`, the number of combinations are `C(N-1, n) + numberOfCombos_at_pos(n+1)`.

```
# curr = the current number at position `n`, N 
rec(curr, n) = C(N-1, n) + rec(curr*j, n+1), j = [2,3,4,5...) && curr*j<MAXVAL
             = 1, n == N-1 due to combination formula
             
For large enough numbers, n never exceeds log(MAXVAL, 2) << (N-1)
C(N-1, n) is basically the attempt to distribute infinite number of `n`s into N buckets

1 1 1 | 
```

Pre-computations of Pascal's triangle is done for C(10000, 15), due to problem and computation constraints. And also since `C(m, n) = C(m, m-n)`.
