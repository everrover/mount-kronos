package LordShiva;

public class DisconnectPathInBinMatrixByAtmostOneFlip {

  /**
   * https://leetcode.com/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip/
   * 
   * Simply performed two DFS's which marking traversed nodes as not traversible. Could've used Tarjan's algorithm or Kosaraju's to find SCC and have checked is SCC count is one or not.
   * 
   * #dfs #rashly-coded #contest
   * TC: O(m*n) SC: O(m*n)
   */
  public boolean isPossibleToCutPath(int[][] grid) {
    final int m=grid.length, n = grid[0].length;
    // if((m == 1 && n<=2) || (n == 1 && m<=2)) return false;
    return !dfs(0, 0, grid, new boolean[m][n], m, n) ||
        !dfs(0, 0, grid, new boolean[m][n], m, n);
  }
  private boolean dfs(int x, int y, int [][]grid, boolean [][]isV, final int m, final int n){
    isV[x][y] = true;
    if(x == m-1 && y == n-1) return true;
    boolean reachable = false;
    if(!reachable && x<m-1 && !isV[x+1][y] && grid[x+1][y] == 1) reachable = dfs(x+1, y, grid, isV, m, n);
    if(!reachable && y<n-1 && !isV[x][y+1] && grid[x][y+1] == 1) reachable = dfs(x, y+1, grid, isV, m, n);
    if(reachable && !((x==0 && y==0) || (x==m-1 && y==n-1))) grid[x][y] = 0;
    return reachable;
  }
}
