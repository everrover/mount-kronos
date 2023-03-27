package NinetySix;


import java.util.HashSet;
import java.util.Set;

/**
https://leetcode.com/problems/pyramid-transition-matrix/
Simply used DFS(could've used BFS) and backtracking across states till reached the final sol.
*/

class Solution {
  public boolean pyramidTransition(String bottom, List<String> allowed) {
    Set<Character> [][]edges = new Set[7][7];
    for(String allow: allowed){
      int i = allow.charAt(0)-'A', j = allow.charAt(1)-'A';
      if(edges[i][j] == null) edges[i][j] = new HashSet<>();
      edges[i][j].add(allow.charAt(2));
    }
    return dfs(edges, new StringBuilder(bottom), new StringBuilder(), 0);
  }
  private boolean dfs(Set<Character> [][]edges, StringBuilder bottom, StringBuilder top, int k){
    if(bottom.length() == 1) return true;
    if(bottom.length() == k+1) return dfs(edges, top, new StringBuilder(), 0);
    int i=bottom.charAt(k)-'A', j=bottom.charAt(k+1)-'A';
    if(edges[i][j] == null) return false;
    for(char ch: edges[i][j]){
      top.append(ch);
      if(dfs(edges, bottom, top, k+1)) return true;
      top.deleteCharAt(k);
    }
    return false;
  }
}
