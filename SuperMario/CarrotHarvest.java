package SuperMario;

import java.util.Arrays;

public class CarrotHarvest {

  /*
   * https://leetcode.com/problems/cherry-pickup-ii/
   * 
   * Simply used DFS with memoization to find the maximum number of cherries.
   * Intuition: Since the robot can move in 3 directions and on each layer a given robot can reach only once, we don't need robot's `row` index.
   * Hence, we can use 3D dp to store the maximum number of cherries.
   * 
   * At `i` layer rboot A can only reach till `i`th column and robot B can only reach till `n-1-i`th column. HENCE, needed extra conditions in bottom-up dp.
   * 
   * SC: O(m*n*n) TC: O(m*n*n) `*9` has been excluded since it's a constant.
   * 
   * #dynamic-programming #dfs
   */

  int [][][]dp;
  int [][]grid;
  int m, n;
  final int []moves = new int[]{-1,0,1};
  public int cherryPickup(int[][] grid) {
    m = grid.length; n = grid[0].length;
    dp = new int[m][n][n];
    for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, -1);
    this.grid = grid;
    m = grid.length; n = grid[0].length;
    int res = dfs(0, 0, n-1);
    return res;
  }

  private int dfs(int i, int j1, int j2){
    if(i>=m || j1>=n || j2>=n || j1<0 || j2<0) return Integer.MIN_VALUE;
    int res = 0, base = (j1 == j2? 0:grid[i][j1])+grid[i][j2];
    if(i == m-1) return base;
    if(dp[i][j1][j2] > -1) return dp[i][j1][j2];
    for(int movex: moves) for(int movey: moves)
      res = Math.max(res, dfs(i+1, j1+movex, j2+movey));
    return dp[i][j1][j2] = res+base;
  }

  public int bfs(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    dp = new int[m][n][n];
    dp[0][0][n-1] = grid[0][0]+grid[0][n-1];
    for(int i=1; i<m; i++){
      for(int j=0; j<n && j<=i; j++){
        for(int k=n-1; k>=0 && k>=(n-i-1); k--){
          int res = 0;
          for(int movex: moves) for(int movey: moves)
            if(j+movex>=0 && j+movex<n && k+movey>=0 && k+movey<n) res = Math.max(res, dp[i-1][j+movex][k+movey]);
          dp[i][j][k] = (j==k?0:grid[i][j]) + grid[i][k] + res;
        }
      }
    }
    int res = 0;
    for(int j=0; j<n; j++){
      for(int k=0; k<n; k++){
        res = Math.max(res, dp[m-1][j][k]);
      }
    }
    return res;
  }
}
