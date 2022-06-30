# [Bellman Ford Algorithm](#)

As per Mr.I(the internet I mean), it has several advantages and applications that aren't provided by the other SPT algorithm
- Ability to find negative weight cycles
- Ability to use in distributed systems and distributed computational models. This I'm really wondering about... how?
- Ability to use with negative edge weights

The solution(SPT) is built incrementally, using DP in a bottom up manner. It does so

The TC is `O(VE)` and MC is `O(V+E)`(edges and SPT array)