[2320. Count Number of Ways to Place Houses](https://leetcode.com/problems/count-number-of-ways-to-place-houses/)

The two different rows of houses are unaffected by each other. Hence, we can find the possible ways to place houses in each 
row and return the square of it(modulo'ed). Basically, using a modded version of [House Robber](../HouseRobber/sol.md)

Works in `O(2*n)` TC and `O(2*n)` MC.

Earlier I implemented it using state transitions as in code. TC and MC is `2x` in comparison to the above solution.