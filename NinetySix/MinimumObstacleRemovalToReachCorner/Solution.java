package NinetySix.MinimumObstacleRemovalToReachCorner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
 *
 * Minesweep title was enough to give the hint. But the trick was awesome. Thanks @Votrubac: https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/discuss/2111406/Minesweeper
 ```cpp
 pair<int, int> dirs[4] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
 int minimumObstacles(vector<vector<int>>& g) {
 int m = g.size(), n = g[0].size(), res = 0;
 deque<array<int, 2>> emp{{0, 0}}, obs;
 while (!emp.empty() || !obs.empty()) {
 if (emp.empty()) {
 ++res;
 swap(emp, obs);
 }
 const auto [i, j] = emp.front(); emp.pop_front();
 if (i == m - 1 && j == n - 1)
 break;
 for (const auto [dx, dy] : dirs) {
 int x = i + dx, y = j + dy;
 if (min(x, y) >= 0 && x < m && y < n && g[x][y] >= 0) {
 if (g[x][y])
 obs.push_back({x, y});
 else
 emp.push_back({x, y});
 g[x][y] = -1;
 }
 }
 }
 return res;
 }
 ```
 * Prioritizing 0s over 1s(using DQ, push `0` nodes in front and `1` nodes in back) in Deque essentially traverses in the same order I've listed.
 * https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/discuss/2085640/JavaPython-3-2-codes%3A-Shortest-Path-and-BFS-w-brief-explanation-analysis-and-similar-problems.
 * ```java
 private static final int[] d = {0, 1, 0, -1, 0};
   private static final int M = Integer.MAX_VALUE;
   public int minimumObstacles(int[][] grid) {
     int m = grid.length, n = grid[0].length;
     int[][] dist = new int[m][n];
     for (int[] di : dist) {
        Arrays.fill(di, M);
     }
     dist[0][0] = 0;
     Deque<int[]> dq = new ArrayDeque<>();
     dq.offer(new int[3]);
     while (!dq.isEmpty()) {
       int[] cur = dq.poll();
       int o = cur[0], r = cur[1], c = cur[2];
       for (int k = 0; k < 4; ++k) {
         int i = r + d[k], j = c + d[k + 1];
         if (0 <= i && i < m && 0 <= j && j < n && dist[i][j] == M) {
           if (grid[i][j] == 1) {
               dist[i][j] = o + 1;
               dq.offer(new int[]{o + 1, i, j});
           }else {
             dist[i][j] = o;
             dq.offerFirst(new int[]{o, i, j});
           }
        }
      }
    }
   return dist[m - 1][n - 1];
 }
 ```
 */
class Solution {
  private static int[][] moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
  private int m, n;
  int[][] spt;
  boolean [][]isV;

  public int minimumObstacles(int[][] grid) {
    m=grid.length; n=grid[0].length;
    isV = new boolean[m][n];
    int res = minesweepingBFS(grid);
    return res;
  }
  private int minesweepingBFS(int [][]grid){
    Queue<int[]> ones = new LinkedList<>();
    Queue<int[]> zeros = new LinkedList<>();

    if(grid[0][0] == 0) zeros.offer(new int[]{0, 0});
    else ones.offer(new int[]{0, 0});
    int res = 0;
    int r,c, cnt;
    int []p;
    while(!ones.isEmpty() || !zeros.isEmpty()){
      if(!zeros.isEmpty()){
        // could've used DFS(Stack) for flood-fill
        while(!zeros.isEmpty()){ // flood fill all accessible locations
          p = zeros.poll();
          r = p[0]; c = p[1];
          if(r == m-1 && c == n-1) return res;
          if(isV[r][c]) continue;
          // System.out.println("0s"+r+":"+c);
          isV[r][c] = true;
          for(int []move: moves){
            int i=r+move[0], j = c+move[1];
            if(isValidMove(i,j)){
              if(grid[i][j] == 0) zeros.offer(new int[]{i, j});
              else ones.offer(new int[]{i, j});
            }
          }
        }
      }
      if(!ones.isEmpty()){
        cnt = ones.size();
        res++;
        while(cnt-->0){ // need to remove only layer 1 and not subsequent layers
          p = ones.poll();
          r = p[0]; c = p[1];
          if(r == m-1 && c == n-1) return res;
          if(isV[r][c]) continue;
          // System.out.println("1s"+r+":"+c);
          isV[r][c] = true;
          for(int []move: moves){
            int i=r+move[0], j = c+move[1];
            if(isValidMove(i,j) && !isV[i][j]){
              if(grid[i][j] == 0) zeros.offer(new int[]{i, j});
              else ones.offer(new int[]{i, j});
            }
          }
        }
      }
    }
    return res;
  }
  private boolean isValidMove(int r, int c){
    return r>=0 && r<m && c>=0 && c<n;
  }
}