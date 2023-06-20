package SuperMario;

import java.util.Arrays;

public class PathsInMatrixWhoseSumIsDivisibleByK {
  /**
   * https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k
   * Used DFS(essentially memoization based DP only to mark nodes for which computation is already done) to find the number of paths in a matrix whose sum is divisible by k.
   * TC: O(n*m*k) SC: O(n*m*k)
   * #dfs #dynamic-programming #matrix
   */

  public int m, n, k;
  public int [][]grid;
  public int [][][]dp;
  public final int MOD = (int)1e9+7;
  public int numberOfPaths(int[][] grid, int k) {
    m = grid.length; n = grid[0].length; this.k = k;
    this.grid = grid;
    this.dp = new int[m][n][k];
    for(int [][]dd: dp){
      for(int []d: dd){
        Arrays.fill(d, -1);
      }
    }
    dp[m-1][n-1][0] = 1;
    return dfs(0, 0, grid[0][0]%k);
  }
  private int dfs(int i, int j, int sum){
    if(i>=grid.length || j>=grid[0].length) return 0;
    if(dp[i][j][sum] != -1) return dp[i][j][sum];
    if(i==m-1 && j==n-1 && sum == 0){ return dp[i][j][sum] = 1; }
    long res = 0;
    if(i<m-1) res += dfs(i+1, j, (sum + grid[i+1][j])%k);
    if(j<n-1) res += dfs(i, j+1, (sum + grid[i][j+1])%k);
    return dp[i][j][sum] = (int)(res%MOD);
  }
}