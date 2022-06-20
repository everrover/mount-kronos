# [740. Delete and earn](https://leetcode.com/problems/delete-and-earn)

It;'s a simple variant of [House Robber]().

All we have to do is build an array(hash) of total count(I have used sums of nums[i] at position `i`)
of nums. Then we could apply the House Robber's algorithm.

The recursive relationship is,
```
sol(hash, pos) = max(sol(hash, pos-1), hash[pos]+sol(pos-2)), pos>=2
               = hash[pos], pos < 2
```

TWO THINGS VALID HERE,

- Overlapping sub-problems
- Optimal sub-str

Hence, we can memoize solutions to sub-problems. This'll improve TC and MC both to `O(n)`.

As an memory optimization in bottom-up approach, 
since we only need the last two previously computed solutions, we don't need the complete DP 
array and only last two computed solutions. This'll improve MC to `O(1)`.