# [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/)



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

### Remember: critical edge has `>` and critical node has `>=`

![](./1-min.jpg)
![](./2-min.jpg)