package SuperMario;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LastDayWhereYouCanStillCross {

  /**
   * https://leetcode.com/problems/last-day-where-you-can-still-cross/
   * Used BFS/DFS with binary search over solution space. I performed BFS without using a Disjoint set.
   * Optimal solution uses Disjoint set.
   * TC: O(m*n*log(m*n)) SC: O(m*n)
   * 
   * Afterwards I devised one linear way of doing it which runs in O(m*n) time and O(m*n) space. With disjoint sets.
   * If for any, `k` such that water cells[k...m*n] form the shortest possible route bw `top` and `bottom`. 
   * It would mean that for all water filled cells[0...k-1], there would always be a path that connects `top` and `bottom`
   * 
   * Note for ME!!! @3July2023: 
   * Have started using optimal algorithms now. Need to start using optimal implementations as well.
   */

  int [][]grid;
  boolean mark[][];
  int row, col;
  public int latestDayToCrossMine(int row, int col, int[][] cells) {
    this.mark = new boolean[row][col];
    this.row = row;
    this.col = col;
    this.grid = new int[row][col];
    for(int i=0; i<row; i++){
      Arrays.fill(this.grid[i], -1);
    }
    for(int i=0; i<cells.length; i++){
      this.grid[cells[i][0]-1][cells[i][1]-1] = i+1;
    }
    int res = 0, l=0, r=cells.length, mid;
    while(l<=r){
      mid = (l+r)/2;
      if(bfs(mid)){
        res = mid;
        l=mid+1;
      } else {
        r=mid-1;
      }
    }
    return res;
  }
  private boolean bfs(int possibleRes){
    Queue<int[]> q = new LinkedList<>();
    for(boolean []mk: mark) Arrays.fill(mk, false);
    for(int i=0; i<col; i++) if(grid[0][i] > possibleRes) q.offer(new int[]{0,i});
    while(!q.isEmpty()){
      int []co = q.poll();
      // System.out.print(":"+co[0]+":"+co[1]);
      if(mark[co[0]][co[1]] || grid[co[0]][co[1]] <= possibleRes) { continue; }
      // System.out.println();
      if(co[0] == row-1) { return true; }
      mark[co[0]][co[1]] = true;
      if(co[0]<row-1){
        q.offer(new int[]{co[0]+1, co[1]});
      }
      if(co[0]>0){
        q.offer(new int[]{co[0]-1, co[1]});
      }
      if(co[1]<col-1){
        q.offer(new int[]{co[0], co[1]+1});
      }
      if(co[1]>0){
        q.offer(new int[]{co[0], co[1]-1});
      }
    }
    return false;
  }
  
  
  // Disjoint set union - solution - used 1D memoization
  int parents[]; // Optimal representation of Disjoint sets
  // can use a 3D array to store parents of each cell in the grid

  
  public int latestDayToCrossSolFromFriend(int row, int col, int[][] cells) {
      // Flip the grid on the diagonal.
      int rW = row + 2;   // Width of a row in parents[] array.        
      parents = new int[(col + 2) * (row + 2)];
      int neighbours[] = {-rW-1, -rW, -rW+1, -1, 1, rW-1, rW, rW+1};
      int lastRow = col * rW;
      int firstRow = 2 * rW - 1;
      
      for (int cellsIdx = 0; ; cellsIdx++) {
          int cell = cells[cellsIdx][1] * rW + cells[cellsIdx][0], parent = cell;
          parents[cell] = cell;
          for(int n:neighbours) {
              int oldCell=cell+n;
              if(parents[oldCell]!=0) {
                  int oldPar = findParent(oldCell);
                  if (parent != oldPar)
                      if (parent <= firstRow) 
                          if (oldPar >= lastRow) return cellsIdx;
                          else parents[oldPar] = parent;
                      else if (parent >= lastRow) 
                          if (oldPar <= firstRow) return cellsIdx;
                          else parents[oldPar] = parent;
                      else parent = parents[parent] = oldPar;
              }                
          }
      }
  }
  
  private int findParent(int cel) {
      return parents[cel] == cel? cel: (parents[cel] = findParent(parents[cel]));
  }

  // In-genius way I used afterwards

  // private int[] parents;
  // private int row;
  // private int col;

  private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  public int latestDayToCross(int row, int col, int[][] cells) {
    int n = row * col;
    this.row = row;
    this.col = col;
    parents = new int[n + 2];
    for (int i = 0; i < parents.length; ++i) {
      parents[i] = i;
    }
    boolean[][] grid = new boolean[row][col];
    int top = n, bottom = n + 1; // to check connection between top and bottom layer
    for(int i=cells.length-1; i>=0; i--){
      int j=cells[i][0]-1, k=cells[i][1]-1;
      grid[j][k]=true;
      for(int []dir: dirs){
        if(validDir(j, k, dir) && grid[j+dir[0]][k+dir[1]]){ // connect to a already seen element
          parents[find(j*col+k)] = find((j+dir[0])*col+k+dir[1]);
        }
        if(j == 0) parents[find(j*col+k)] = find(top); // j connected to top
        if(j == row-1) parents[find(j*col+k)] = find(bottom); // j connected to bottom
        if(find(top) == find(bottom)) return i;
      }
    }
    return 0;
  }

  private int find(int x) {
    if (parents[x] != x) parents[x] = find(parents[x]);
    return parents[x];
  }

  private boolean validDir(int i, int j, int []dir) {
    return i+dir[0] >= 0 && i+dir[0] < row && j+dir[1] >= 0 && j+dir[1] < col;    
  }
}
