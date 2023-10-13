# [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/)

# Tarjan's algorithm

## _[Important note]To make work for directed graph, we don't need to track parent, so passing -1 as intermediate node's parent and -2 as root's parent would make it work._

In the DFS tree, a node is critical if the following conditions are met:
- If the root has more than one child
- `discovery[v] >= low[u]` for all nodes where `v` is a descendent of `u`. `low` values are the earliest times of all 
  discovery times of it's descendants

Whenever there's a back edge we set `low[u]=min(low[u], discovery[v])`. This back-edge makes a DFS subtree, a critical 
subtree. If any node(except root of this subtree) is removed, our graph would still stay connected.

In the DFS tree, an edge is critical if the following condition is met:
- `discovery[v] > low[u]` for all nodes where `v` is a descendent of `u`. `low` values are the earliest times of all
  discovery times of it's descendants

Similarly, for a node to be critical:
- `discovery[u] >= low[v]` for all nodes where `u` is a descendent of `v`. `low` values are the earliest times of all
  discovery times of it's descendants

### Remember: critical edge has `>` and critical node has `>=`

![](./1-min.jpg)
![](./2-min.jpg)

The algorithm works because an articulation point is a node whose removal would disconnect the graph, and a bridge is an edge whose removal would disconnect the graph. The low time of a node is the earliest discovery time of any node that can be reached from the subtree rooted at the node, including the node itself. If the low time of a neighbor is less than the discovery time of the node, then there is no other path from the subtree rooted at the neighbor to the subtree rooted at the node, and hence the edge connecting the node and the neighbor is a bridge. If the node is the root of the DFS tree and has more than one child, then removing the node would disconnect the graph, and hence the node is an articulation point. If the node is not the root of the DFS tree and there exists a child of the node such that the low time of the child is greater than or equal to the discovery time of the node, then removing the node would disconnect the graph, and hence the node is an articulation point. ▌

Explain Tarjan's algorithm in detail here. And also why it works.
