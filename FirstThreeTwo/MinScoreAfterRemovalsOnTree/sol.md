# (2322. Minimum Score After Removals on a Tree)[https://leetcode.com/problems/minimum-score-after-removals-on-a-tree]

The biggest problem to solve, I thought initially, was to build out a tree. And that's where I got stuck initially. We can 
find the most balanced tree using the `indegree` of graph nodes.

How? The nodes of `indegree` 1 will always be the leaf nodes. Using those we can always get the DFS tree with a most 
balanced tree. Most balanced tree helped me improve TC, but isn't necessary.

However, later I realised... that is isn't essential. **_DFS from any node in a tree's graph will always result in a tree._** 😅. A 
realisation that came very late. The DFS tree node was augmented to carry with it, a reference to all child elements(used 
hash-set) and the `xor` of all elements with it being the root of subtree. 

Anyways, once the tree was ready, I brute-forced into finding the edge-pair that results in smallest operation result. If we 
had both `a` and `c` in same branch of the tree.
Let's say for edge 1 we have two nodes `a` and `b` with `b` being the parent in DFS tree, and for edge 2 similarly we have `c` 
and `d`. If `a` is an ancestor of `c`, the op result would be based on max and min of `totalXor^a.xor, a.xor^c.xor, c.xor)`. 
If `c` is an ancestor of `a`, the op result would be based on max and min of `totalXor^c.xor, a.xor^c.xor, a.xor)`. 

Else, the two nodes are in completely different branches and result will be based on `totalXor^c.xor^a.xor, a.xor, b.xor`.

We pick a minimum of all results obtained for these edge pairs.

Note: We are able to remove xor effects of a certain branch since, `X^X = 0`.