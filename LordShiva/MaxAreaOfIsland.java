package LordShiva;

public class MaxAreaOfIsland {

  /**
   * https://leetcode.com/problems/max-area-of-island/
   * 
   * We can use DFS to find the area of the island. We can use a boolean matrix to mark the visited cells.
   * Same can be done with BFS as well.
   * 
   * TC: O(m*n) SC: O(m*n)
   * 
   * #dfs #bfs #graph #matrix
   */

  private int m,n;
  public int maxAreaOfIsland(int[][] grid) {
    m = grid.length; n = grid[0].length;
    boolean [][]mark = new boolean[m][n];
    int area = 0;
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(!mark[i][j]){
          area = Math.max(area, dfs(i, j, grid, mark));
        }
      }
    }
    return area;
  }

  private int dfs(int i, int j, int [][]grid, boolean [][]mark){
    if(i>=m || j>=n || i<0 || j<0) return 0;
    else if(mark[i][j] || grid[i][j] == 0) return 0;
    mark[i][j] = true;
    int area = grid[i][j];
    area += dfs(i+1,j,grid,mark);
    area += dfs(i-1,j,grid,mark);
    area += dfs(i,j+1,grid,mark);
    area += dfs(i,j-1,grid,mark);
    return area;
  }
}
