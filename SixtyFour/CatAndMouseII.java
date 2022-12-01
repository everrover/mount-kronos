package SixtyFour;

public class CatAndMouseII {

  /**
   * https://leetcode.com/problems/cat-and-mouse-ii/
   * For faster execution I could have used a 3D array instead of 5. Example: https://leetcode.com/problems/cat-and-mouse-ii/discuss/1023634/C%2B%2B-80-ms-(weak-test-cases)
   * Can't use `isVisited` array since ... it's possible that cat-or-the mice would like to stay in the same zone. Example listed in class `Solution`
   */
  private char [][]g;
  private int cJ, mJ, fr, fc, m, n;
  private int dp[][][][][];
  // private boolean [][][]v;
  public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
    this.m = grid.length; this.n = grid[0].length();
    this.cJ = catJump; this.mJ = mouseJump;
    this.g = new char[m][n]; this.dp = new int[m][n][m][n][75];
    // this.v = new int[2][m][n];
    int catr=0, catc=0, mr=0, mc=0;
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        char curr = grid[i].charAt(j);
        if(curr == 'C'){
          catr = i; catc = j;
        }else if(curr == 'M'){
          mr = i; mc = j;
        }else if(curr == 'F'){
          fr = i; fc = j;
        }
        this.g[i][j] = curr;
      }
    }
    boolean res = doesWin(0, catr, catc, mr, mc);
    return res;
  }

  private boolean isPathPossible(int r, int c){
    return r>=0 && c>=0 && r<m && c<n && g[r][c] != '#';
  }

  private static int[] xs = new int[]{0,0,-1,1};
  private static int[] ys = new int[]{-1,1,0,0};

  private boolean doesWin(int turns, int cr, int cc, int mr, int mc) {
    // System.out.println(turns+"::"+mr+":"+mc+"::"+cr+":"+cc);
    if(turns>=75) return false;
    if(dp[mr][mc][cr][cc][turns] != 0){ return dp[mr][mc][cr][cc][turns] == 1; }
    if(cr == mr && cc == mc) { dp[mr][mc][cr][cc][turns] = -1; return false; }
    if(turns%2==0){
      for (int k = 0; k < 4; k++) {
        for (int i = 0; i <= mJ; i++) {
          int nr = mr + xs[k] * i, nc = mc + ys[k] * i;
          if (!isPathPossible(nr, nc)) break;
          if((g[nr][nc] == 'F') || doesWin(turns+1, cr, cc, nr, nc)) { dp[mr][mc][cr][cc][turns] = 1; return true; }
        }
      }
      dp[mr][mc][cr][cc][turns] = -1;
      return false;
    }else{
      for (int k = 0; k < 4; k++) {
        for (int i = 0; i <= cJ; i++) {
          int nr = cr + xs[k] * i, nc = cc + ys[k] * i;
          if (!isPathPossible(nr, nc)) break;
          if((mr == nr && mc == nc)||(g[nr][nc] == 'F')||!doesWin(turns+1, nr, nc, mr, mc)) { dp[mr][mc][cr][cc][turns] = -1; return false; }
        }
      }
      dp[mr][mc][cr][cc][turns] = 1;
      return true;
    }
  } // ismt = isMouseTurn

  class SolutionDFS { // Still wrong. Jumps are always in one direction.

    private char [][]g;
    private int cJ, mJ, fr, fc;
    private boolean [][][]isV;
    private int s;
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
      this.cJ = catJump;
      this.mJ = mouseJump;
      this.g = new char[grid.length][grid[0].length()];
      this.isV = new boolean[2][grid.length][grid[0].length()];
      s=0;
      int catr=0, catc=0, mr=0, mc=0;
      for(int i=0; i<grid.length; i++){
        for(int j=0; j<grid[i].length(); j++){
          char curr = grid[i].charAt(j);
          if(curr == 'C'){
            catr = i; catc = j;
          }else if(curr == 'M'){
            mr = i; mc = j;
            // }else if(curr == 'F'){
            //   fr = i; fc = j;
          }
          this.g[i][j] = curr;
        }
      }
      boolean res = doesWin(true, mJ, catr, catc, mr, mc);
      System.out.print(s);
      return res;
    }

    private boolean doesWin(boolean ismt, int moves, int catr, int catc, int mr, int mc) {
      s++;
      if(catr<0 || catc<0 || mr<0 || mc<0 ||
          catr>=g.length || mr>=g.length || catc>=g[0].length || mc>=g[0].length ||
          (ismt && isV[0][mr][mc]) || (!ismt && isV[1][catr][catc])){
        return false;
      }
      boolean res = false;
      if(g[catr][catc] == 'F' || (catr == mr && catc == mc) || g[catr][catc] == '#' || g[mr][mc] == '#') return false;
      else if(g[mr][mc] == 'F') return true;

      if(moves == 0){
        moves = ismt?cJ:mJ;
        ismt = !ismt;
      }

      if(isV[ismt?0:1][mr][mc]){
        return false;
      }

      if(ismt){
        isV[0][mr][mc] = true;
        res = doesWin(true, moves-1, catr, catc, mr+1, mc) ||
            doesWin(true, moves-1, catr, catc, mr-1, mc) ||
            doesWin(true, moves-1, catr, catc, mr, mc+1) ||
            doesWin(true, moves-1, catr, catc, mr, mc-1) ||
            doesWin(false, cJ, catr, catc, mr, mc)
        ;
        isV[0][mr][mc] = false;
        return res;
      }else{
        isV[1][mr][mc] = true;
        res = doesWin(false, moves-1, catr+1, catc, mr, mc) ||
            doesWin(false, moves-1, catr-1, catc, mr, mc) ||
            doesWin(false, moves-1, catr, catc+1, mr, mc) ||
            doesWin(false, moves-1, catr, catc-1, mr, mc) ||
            doesWin(true, mJ, catr, catc, mr, mc);
        isV[1][mr][mc] = false;
        return res;
      }
    } // ismt = isMouseTurn
  }
}
