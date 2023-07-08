package SuperMario;

public class StableBrix {
  /**
   * https://leetcode.com/problems/bricks-falling-when-hit/
   * 
   * Marked all acdessible nodes from top as 2.
   * Then for each hit, check if it is connected to top. 
   * DFS only for the nodes connected to top.
   * 
   * TC: O(m*n) SC: O(1)
   */

  private int m, n;
  private int [][]grid;
  public int[] hitBricks(int[][] grid, int[][] hits) {
    int []res = new int[hits.length];
    m = grid.length; n = grid[0].length;
    this.grid = grid;    
    for(int i=0; i<hits.length; i++) grid[hits[i][0]][hits[i][1]] *= -1; // mark hits as -1
    for(int i=0; i<n; i++) dfs(0, i);
    for(int i=hits.length-1; i>=0; i--){
      int x = hits[i][0], y = hits[i][1];
      if(grid[x][y] == 0) continue;
      grid[hits[i][0]][hits[i][1]] = 1;
      boolean isConnected = false;
       for(int []move: moves){
        if(isValid(x+move[0], y+move[1]) && (grid[x+move[0]][y+move[1]] == 2 || x == 0)){
          isConnected = true;
          break;
        }
      }
      if(isConnected) res[i] = Math.max(0, dfs(x, y)-1);
    }
    return res;
  }
  private int [][]moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
  private int dfs(int i, int j){
    if(!isValid(i, j) || grid[i][j] != 1) return 0;
    grid[i][j] = 2;
    int r = 1;
    for(int []move: moves){
      r += dfs(i+move[0],j+move[1]);
    }
    return r;
  }
  private boolean isValid(int i, int j){
    return i>=0 && i<m && j>=0 && j<n;
  }
}
