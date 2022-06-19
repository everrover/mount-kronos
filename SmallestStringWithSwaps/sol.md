# [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/)

Essentially all strongly connected components will have to be sorted amongst themselves. These 
SCC are made up of characters at indices(nodes in graphs) and the edges are the relationships 
defined within pairs. 

Since we are allowed infinite number of swaps, the characters at indexes within SCC will create 
smallest(lexicographically) possible string.

Two possible ways are there to fetch SCC's,

Solution 1: Perform BFS(my choice since it's less memory intensive) or DFS to find characters within each SCC. 
Solution 2: Use disjoint sets.

Create a graph of relationships(with string indices). USE one of the two sols above to get SCC. Use 
SCC index array and sort both the char array(obtained via array indices in SCC) and index 
array. Replace characters in string in increasing order at required string indices within SCC.

```
s = "dceab", pairs = [[0,4],[1,3],[0,3]]
SCC: {0,1,3,4}, {2}
Char strings: "dcab"->"abcd", "e"->"e"
"ab-e-cd"
```