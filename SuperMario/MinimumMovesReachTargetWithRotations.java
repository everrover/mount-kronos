package SuperMario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class MinMovesToMoveBoxToTargetLocation {
  /**
   * https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations
   * Tried using raw BFS, but it was too slow
   * - I used a custom class to represent the state and used it as a key in the visited set
   * 
   * TC
   * 
   * Then used DP to optimize the solution.
   * - dp[i][j][k] represents the minimum number of moves to reach the box at (i,j) starting from (0,0)
   * - 6 moves are possible: 
   *    - 4 moves to move the snake
   *      - 2 moves to move the snake in horizontal orientation
   *      - 2 moves to move the snake in vertical orientation   
   *      - all moves are in respect to tail of the snake
   *    - 2 moves to rotate : counter-clockwise and clockwise
   * TC: O(m*n), SC: O(m*n*2)
   * 
   * #bfs #dp #tricky #optimization #memoization-optimization
   */

  private static class Pos{
    int x1, y1, x2, y2;
    public Pos(int x1, int y1, int x2, int y2){
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
    public boolean equals(Pos pos) {
      return this.x1 == pos.x1 && this.x2 == pos.x2 && this.y1 == pos.y1 && this.y2 == pos.y2;
    }
    public String toString(){
      return x1+":"+y1+"::"+x2+":"+y2;
    }
  }
  public int minimumMovesBFS(int[][] grid) {
    return bfs(grid);
  }

  private int bfs(int [][]grid){
    Queue<Pos> q = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    int res = 0;
    q.offer(new Pos(0,0,0,1));
    Pos dest = new Pos(grid.length-1, grid.length-2, grid.length-1, grid.length-1);
    while(!q.isEmpty()){
      int size=q.size();
      while(size-->0){
        Pos curr = q.poll();
        if(visited.contains(curr.toString())) {
          continue;
        }
        visited.add(curr.toString());
        System.out.println(curr);
        if(dest.equals(curr)){
          return res;
        }
        // move down
        if(curr.x1!=grid.length-1 && curr.x2!=grid.length-1 && grid[curr.x1+1][curr.y1] == 0 && grid[curr.x2+1][curr.y2] == 0){
          q.offer(new Pos(curr.x1+1, curr.y1, curr.x2+1, curr.y2));
        }
        // move right
        if(curr.y1!=grid.length-1 && curr.y2!=grid.length-1 && grid[curr.x1][curr.y1+1] == 0 && grid[curr.x2][curr.y2+1] == 0){
          q.offer(new Pos(curr.x1, curr.y1+1, curr.x2, curr.y2+1));
        }
        // rotate counter-clockwise
        if(curr.y1!=grid.length-1 && curr.y1 == curr.y2 && grid[curr.x1+1][curr.y1+1] == 0 && grid[curr.x1][curr.y1+1] == 0){
          Pos t = new Pos(curr.x1, curr.y1, curr.x1, curr.y1+1);
          q.offer(t);
        }
        // rotate clockwise
        if(curr.x1!=grid.length-1 && curr.x1 == curr.x2 && grid[curr.x1+1][curr.y1] == 0 && grid[curr.x1+1][curr.y1+1] == 0){
          Pos t = new Pos(curr.x1, curr.y1, curr.x1+1, curr.y1);
          q.offer(t);
        }
      }
      res++;
    }

    return -1;
  }

  public int minimumMoves(int[][] grid) { // DP
    int n = grid.length;
    int[][][] dp = new int[n][n][2];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        Arrays.fill(dp[i][j], Integer.MAX_VALUE / 2);
      }
    }

    dp[0][0][0] = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        boolean canHorizontal = (j + 1 < n && grid[i][j] == 0 && grid[i][j + 1] == 0);
        boolean canVertical = (i + 1 < n && grid[i][j] == 0 && grid[i + 1][j] == 0);

        if (i - 1 >= 0 && canHorizontal) {
          dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][0] + 1);
        }
        if (j - 1 >= 0 && canHorizontal) {
          dp[i][j][0] = Math.min(dp[i][j][0], dp[i][j - 1][0] + 1);
        }
        if (i - 1 >= 0 && canVertical) {
          dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][j][1] + 1);
        }
        if (j - 1 >= 0 && canVertical) {
          dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][1] + 1);
        }
        if (canHorizontal && canVertical && grid[i + 1][j + 1] == 0) {
          dp[i][j][0] = Math.min(dp[i][j][0], dp[i][j][1] + 1);
          dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j][0] + 1);
        }
      }
    }

    return dp[n - 1][n -2][0] == Integer.MAX_VALUE / 2 ? -1 : dp[n - 1][n -2][0];
  }
}