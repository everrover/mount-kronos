package SuperMario;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
  /**
   * https://leetcode.com/problems/shortest-path-in-binary-matrix/
   * Used BFS to find the shortest path within the un-directed graph.
   * The below sol is un-optimal, but I'm too tired of repetitions
   * TC: O(n*m) SC: O(n*m)
   * #bfs #shortest-path #matrix #no-effort
   */
  public int shortestPathBinaryMatrix(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    boolean v[][] = new boolean[m][n];
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{0,0,0});
    while(!q.isEmpty()){
      int []curr = q.poll();
      int i = curr[0], j = curr[1], c = curr[2];
      if(i<0 || j<0 || i>=m || j>=n || v[i][j] || grid[i][j]==0) continue;
      if(i==m-1 && j==n-1) return c+1;
      v[i][j] = true;
      q.offer(new int[]{i-1, j-1, c+1, c+1});
      q.offer(new int[]{i-1, j, c+1});
      q.offer(new int[]{i-1, j+1, c+1});
      q.offer(new int[]{i, j-1, c+1});
      q.offer(new int[]{i, j+1, c+1});
      q.offer(new int[]{i+1, j-1, c+1});
      q.offer(new int[]{i+1, j, c+1});
      q.offer(new int[]{i+1, j+1, c+1});
    }
    return -1;
  }
}
