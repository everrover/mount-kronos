package NinetySix.MinimumObstacleRemovalToReachCorner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class SPTSolution {
  private static int[][] moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
  private int m, n;
  int[][] spt;

  public int minimumObstacles(int[][] grid) {
    m=grid.length; n=grid[0].length;
    spt = new int[m][n];
    for(int []s: spt) Arrays.fill(s, Integer.MAX_VALUE);
    minCostTree(grid);
    return spt[m-1][n-1];
  }

  //TC: O(E*ln(V)) = O(4*R*C*log(R*C)) = O(RC * log(RC)), MC: O(2*RC)
  private int minCostTree(int [][]grid){ // Using Dijkstra's algo(with PQ), could've used Prim's and Bellman Ford's algo as well
    Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
    pq.offer(new int[]{grid[0][0], 0, 0});
    spt[0][0] = grid[0][0];
    while(!pq.isEmpty()){
      int []p = pq.poll();
      int r = p[1], c = p[2], cost = p[0];
      if(c == n-1 && r == m-1) return cost;
      for(int []move: moves){
        int i=r+move[0], j = c+move[1];
        if(isValidMove(i,j) && grid[i][j] + cost < spt[i][j]){
          spt[i][j] = grid[i][j]+cost;
          pq.offer(new int[]{spt[i][j], i, j});
        }
      }
    }
    return spt[m-1][n-1];
  }
  private boolean isValidMove(int r, int c){
    return r>=0 && r<m && c>=0 && c<n;
  }
}
