# [877. Stone Game](https://leetcode.com/problems/stone-game/)

#dynamic-programming #greedy #math #simulation

The solution I've built is capable for working in all cases with the given constraints... IGNORED.

## Solution

For every player at every turn, we can choose to either take the stone on the left or the right. We simulate this 
behaviour at each recursion step. At every step, we find the max score achieved by current player(for both left and right 
cases) and for the best path, calculate score achieved by both _alice_ and _bob_, and return the pair. For rest of 
explanation have a look at the [code](./StoneGame.java).

BASE CASE: For two stones left, the `currentPlayer` will definitely pick the largest pile.

## Ironic and most crappy solution

If we apply Math and simulate for every possible situation, we find that player who plays first will always win. And the 
solution on leetcode suggested the same. But I'll never use it, and recommend you to do the same.

Hence, code below works, in `O(1)` TC and `O(1)` MC.
```java
public boolean solve(int []piles){
    return true;
}
```