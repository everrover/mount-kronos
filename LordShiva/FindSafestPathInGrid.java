package LordShiva;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindSafestPathInGrid{

  /**
   * Gave during contests. Pointers::
   * - Always use traversal array and global checks for 
   * int []trav = new int[]{1,0,-1,0,1}; // or {-1,0,1,0,-1}
   * for(int i=1; i<5; i++) 
   *  int j = idx+trav[i-1], k = jdx+trav[i];
   *  if(j<0 || j>m-1 || k<0 || k>n-1) continue;
   *  // do x,y,z
   * - Keep names short for your own sake
   * 
   * https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/
   * 
   * First found the smallest manhatten distance from thieves for each cell via simple BFS. 
   * Now find the max-sum path via Dijkstra algorithm. On the max-sum path find the smallest manhatten distance from thieves. That's the result.
   * 
   * I also saw solutions centered around applying BFS/DFS with binary search over the distance(solution space). Offers same time complexity.
   * Basically, for a given constrained distance, find if there's a path from start(0,0) to end(m-1,n-1). 
   * If yes, then try to find a smaller distance.
   * Else, try to find a larger distance.
   * 
   * TC: O(m*n*log(m*n)) SC: O(m*n)
   *  
   * #contest #dfs #bfs #dijkstra-algorithm #priority-queue #binary-search
   */

  public int maximumSafenessFactor(List<List<Integer>> grid) {
    int m = grid.size(), n = grid.get(0).size();
    int [][]mark = new int[m][n];
    boolean [][]v = new boolean[m][n];
    for(int []ma: mark) Arrays.fill(ma, Integer.MAX_VALUE);
    Queue<int[]> q = new LinkedList<>();
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(grid.get(i).get(j) == 1) q.add(new int[]{i,j,0});
      }
    }
    while(!q.isEmpty()){
      int []e = q.poll();
      int i = e[0], j = e[1], val = e[2];
      if(v[i][j]) continue;
      mark[e[0]][e[1]] = Math.min(e[2], mark[e[0]][e[1]]);
      v[e[0]][e[1]] = true;
      
      if(i>0) {mark[i-1][j] = Math.min(mark[i-1][j], val+1); q.offer(new int[]{i-1,j,val+1});} 
      if(i<m-1) {mark[i+1][j] = Math.min(mark[i+1][j], val+1); q.offer(new int[]{i+1,j,val+1});} 
      if(j>0) {mark[i][j-1] = Math.min(mark[i][j-1], val+1); q.offer(new int[]{i,j-1,val+1});} 
      if(j<n-1) {mark[i][j+1] = Math.min(mark[i][j+1], val+1); q.offer(new int[]{i,j+1,val+1});}
    }
    for(boolean []arr: v) Arrays.fill(arr, false);
    int res = Integer.MAX_VALUE;
    q = new PriorityQueue<>((a,b)->b[2]-a[2]);
    q.offer(new int[]{0,0,mark[0][0]});
    while(!q.isEmpty()){
      int []e = q.poll();
      int i = e[0], j = e[1];
      if(v[i][j] || v[m-1][n-1]) continue;
      res = Math.min(res, mark[i][j]);
      v[i][j] = true;
      if(i>0 && !v[i-1][j]) {q.offer(new int[]{i-1,j,mark[i-1][j]});} 
      if(i<m-1 && !v[i+1][j]) {q.offer(new int[]{i+1,j,mark[i+1][j]});} 
      if(j>0 && !v[i][j-1]) {q.offer(new int[]{i,j-1,mark[i][j-1]});} 
      if(j<n-1 && !v[i][j+1]) {q.offer(new int[]{i,j+1,mark[i][j+1]});}
    }
    return res;
  }

}