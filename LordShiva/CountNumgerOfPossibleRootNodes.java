package LordShiva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountNumgerOfPossibleRootNodes {
  /**
   * https://leetcode.com/problems/count-number-of-possible-trees/
   * 
   * I made a mistake during complecity calculation. Since it's a tree the parent-child mappings used for memoization will be unique. So it's O(n) and not O(n^2). And hence got stuck.
   * Otherwise I just put memoization on top of brute force method. Iterating over all nodes and checking if it's satisfies the mentioned constraints or not. 
   * 
   * I saw another clever solution as well. Putting it down below. Unable to understand it though.
   * 
   * #dfs #bfs #dynamic-programming #contest
   * 
   * TC: O(n) SC: O(n)
   */

  public int rootCount(int[][] edges, int[][] guesses, int k) {
    int n = edges.length + 1;
    
    List<Integer>[] graph = new ArrayList[n];
    Set<Integer>[] guessesGraph = new HashSet[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
      guessesGraph[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      graph[edge[1]].add(edge[0]);
    }
    for (int[] guess : guesses) {
      guessesGraph[guess[0]].add(guess[1]);
    }

    int res = 0;
    Map<Long, Integer> memo = new HashMap<>();
    for (int i = 0; i<n; i++) {
      if (dfs(graph, guessesGraph, memo, i, -1) >= k) {
        res++;
      }
    }
    return res;
  }
  
  
  int dfs(List<Integer>[] graph, Set<Integer>[] guessesGraph, Map<Long, Integer> memo, int current, int prev) {
    long key = (long)current * 1000000 + prev;
    if (memo.containsKey(key)) return memo.get(key);
    int count = prev != -1 && guessesGraph[prev].contains(current) ? 1 : 0;
    for (int next : graph[current]) {
      if (next != prev) {
        count += dfs(graph, guessesGraph, memo, next, current);
      }
    }
    memo.put(key, count);
    return count;
  }

  // --------------- unique
  private static class Solution {
    int[] parents;
    List<Integer>[] graph;
    HashSet<Integer>[] guess; // Use set to avoid duplicate enteries
    int ans=0;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length+1;
        graph = new ArrayList[n];
        guess = new HashSet[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<Integer>();
            guess[i] = new HashSet<Integer>();
        }
        // Create tree
        for(int i=0; i<edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        // Create guess array
        for(int i=0; i<guesses.length; i++){
            guess[guesses[i][0]].add(guesses[i][1]);
        }
        parents = new int[n];
        fill(0,-1);
        int c=0;
        for(int i=1; i<n; i++) {
            if(guess[parents[i]].contains(i)) c++;
        }
        if(c>=k) ans++;
        for(int i: graph[0]) dfs(i, 0, c, k);
        return ans;
    }

    // Fill the parent array
    void fill(int v,int p){
        parents[v] = p;
        for(int child: graph[v]){
            if(child==p) continue;
            fill(child,v);
        }
    }

    // Use DFS to make all nodes as root one by one
    void dfs(int v, int p, int c, int k){
        if(guess[p].contains(v)) c--;
        if(guess[v].contains(p)) c++;
        if(c>=k) ans++;
        for(int child: graph[v]) if(child!=p) dfs(child, v, c, k);
    }
}
}
