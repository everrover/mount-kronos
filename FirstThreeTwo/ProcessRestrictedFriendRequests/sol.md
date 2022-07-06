# [2076. Process Restricted Friend Requests](https://leetcode.com/problems/process-restricted-friend-requests/)

We need _**Disjoint Sets** with Path compression_ for resolving this. 

The algorithm is straight-forward. 
- First we build disjoint-set for each of the friends.
- For each request,
  - If reps of both the friends in request is the different, we check if any violations are occurring because of the restrictions. This we do by iterating over each restriction and checking if restriction _**reps**_ match with request _**reps**_.
  - If _**reps**_ are different, we unionize request **_reps_**. And... set result to `true`. Otherwise, result is set to `true`

This approach runs in `O(m*n*(alpha))` TC and `O(k)` MC. m = number of friend requests, n = number of restrictions and k = number of friends in network.
I tried and tried and tried to solve it in something less than above TC but couldn't find anything else.
