# [Floyd-Warshall algorithm](#)

Basically we incrementally build the SPT using the relationship

```
dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
where `dist[i][j]` is the distance from `i` to `j` and `dist[i][k]` is the distance from `i` to `k`.
```

Translating this to adjacency list is easy or some other form is easy. 

The TC is `O(V^3)` due to three loops and MC is `O(V^2)` for `dist` matrix.

TBH better SPT algorithms are present for the same problem, and so this isn't the best for most cases.