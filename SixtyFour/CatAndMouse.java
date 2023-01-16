package SixtyFour;

public class CatAndMouse {
  private int isVisited[][][];
  private int [][]graph;
  private static int cnt = 0;
  public int catMouseGame(int[][] graph) {
    int size = graph.length;
    this.graph = graph;
    isVisited = new int[size][size][2];
    for(int[][] pp: isVisited){
      for(int []p: pp){
        p[0] = p[1] = -1;
      }
    }
    int res = doesMouseWin(0, 1, 2);
    return res;
  }

  private int doesMouseWin(int ismt, int m, int c){
    if(isVisited[m][c][ismt] != -1){
      return isVisited[m][c][ismt];
    }
    if(m == 0) return isVisited[m][c][ismt] = 1;
    if(m == c) return isVisited[m][c][ismt] = 2;
    isVisited[m][c][ismt] = 0;
    if(ismt == 0){
      boolean res = true;
      for(int v: graph[m]){
        int tmp = doesMouseWin(1, v, c);
        if(tmp == 1) { return isVisited[m][c][0] = 1; }
        if(tmp == 0) { res = false; }
      }
      return isVisited[m][c][0] = res?2:0;
    }else{
      boolean res = true;
      for(int v: graph[c]){
        if(v == 0) continue;
        int tmp = doesMouseWin(0 , m, v);
        if(tmp == 2) { return isVisited[m][c][1] = 2; }
        if(tmp == 0) { res = false; }
      }
      return isVisited[m][c][1] = res?1:0;
    }
  }

  public static void main(String[] args) {
    new CatAndMouse().catMouseGame(new int[][]{{5,6},{3,4},{6},{1,4,5},{1,3,5},{0,3,4,6},{0,2,5}});
  }
}
