# [2316. Count Unreachable Pairs of Nodes in an Undirected Graph](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

Have found the SCC's first using Disjoint algo. Have augmented it to keep count of elements in the set. The SCC's are built 
with edge list traversal only. The all parents of the SCC's are extracted. 

The SCC roots are then used for finding result using `remainingNodes*sccRoot.count`. `remainingNodes` is the number of nodes 
initially and `sccRoot.count` subtracted from it before aforementioned calculation. TC(avg, not including amortized analysis)
is `O(E)` and MC is `O(V)`.

Have not chosen DFS or BFS, since would have to traverse the edges twice. Once for building `adjList` or `adjMatrix` and once 
for BFS and DFS itself. Our TC would then be `O(V+E) = O(V+E)+O(E)` and MC is `O(V+E)` Other than that, the logic is pretty 
much the same.