package SuperMario;

import java.util.LinkedList;
import java.util.Queue;

public class StableBric {
  /**
   * https://leetcode.com/problems/bricks-falling-when-hit/
   * Coukd be solved with BFS/DFS & Union-Find
   * Trick is to reverse the process. Add the bricks back in reverse order of hits.
   * For any i`th hit, we know all bricks broken will hv the entire graph disconnected.
   * 
   * TC: O(m*n) SC: O(m*n)
   */

  private static class DisjointSetElement {
    private DisjointSetElement parent;    
    private int rank;
    private int val;

    public DisjointSetElement() {
      this.parent = this;
      this.rank = 0;
      this.val = 0;
    }

    public DisjointSetElement(int val) {
      this.parent = this;
      this.rank = 0;
      this.val = val;
    }

    public String toString(){
      return ((this == this.parent)? this.val: this.find().val)+"";
    }
    
    public DisjointSetElement find() {
      if (this.parent != this) {
        this.parent = this.parent.find();
      }
      return this.parent;
    }

    public void union(DisjointSetElement other) {
      DisjointSetElement root1 = this.find();
      DisjointSetElement root2 = other.find();
      if (root1 == root2) return;
      if (root1.rank < root2.rank) {
        root1.parent = root2;
      } else if (root1.rank > root2.rank) {
        root2.parent = root1;
      } else {
        root2.parent = root1;
        root1.rank++;
      }
    }
  }
  private int m, n, oneConnected;
  private boolean [][]vi;
  private DisjointSetElement ds[];
  private int [][]grid;
  public int[] hitBricks(int[][] grid, int[][] hits) {
    int []res = new int[hits.length];
    m = grid.length; n = grid[0].length; oneConnected = m*n;
    this.grid = grid;
    vi = new boolean[m][n];
    ds = new DisjointSetElement[m*n+1];
    for(int i=0; i<=oneConnected; i++) ds[i] = new DisjointSetElement(i);
    
    for(int i=0; i<hits.length; i++) if(grid[hits[i][0]][hits[i][1]] == 1) grid[hits[i][0]][hits[i][1]] = 0; else hits[i] = null; 

    for(int i=0; i<n; i++) {
      ds[oneConnected].union(ds[i]);
      if(grid[0][i] == 1) bfs(0,i);
    }
    for(int i=hits.length-1; i>=0; i--){
      if(hits[i] == null) continue;
      grid[hits[i][0]][hits[i][1]] = 1;
      res[i] = bfs(hits[i][0], hits[i][1]);
    }
    return res;
  }
  private int [][]moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
  private int bfs(int i, int j){
    Queue<int[]> q = new LinkedList<>();
    for(int []move: moves){
      int k=move[0]+i, l=move[1]+j;
      if(isValid(k,l) && grid[k][l] != 0) {
        q.offer(new int[]{k, l});
      }
    }
    int res=0;
    while(!q.isEmpty()){
      int []pt = q.poll();
      int ii = pt[0], jj = pt[1];
      if(grid[ii][jj] == 0 || ds[ii*n+jj].find()==ds[i*n+j].find()) continue;
      // System.out.println(i+":"+j+":::"+ii+":"+jj);
      ds[i*n+j].union(ds[ii*n+jj]);
      for(int []move: moves){
        int k=move[0]+ii, l=move[1]+jj;
        if(isValid(k,l) && grid[k][l] != 0) {
          q.offer(new int[]{k, l});
        }
      }
    }
    if(ds[i*n+j].find() == ds[oneConnected].find()){
      res=dfs(i, j)-1;
    }
    return Math.max(res, 0);
  }
  private int dfs(int i, int j){
    if(vi[i][j]) return 0;
    int res = 1;
    vi[i][j] = true;
    for(int []move: moves){
      int k = i+move[0], l = j+move[1];
      if(isValid(k,l) && grid[k][l]!=0){
        res += dfs(k,l); 
      }
    }
    return res;
  }

  private boolean isValid(int i, int j){
    return i>=0 && i<m && j>=0 && j<n;
  }
}
