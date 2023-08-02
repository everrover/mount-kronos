package SuperMario;

import java.util.Arrays;

public class ShortestCycle {
  boolean [][]graph;

  public int findShortestCycle(int n, int[][] edges) {
    int res = Integer.MAX_VALUE;
    boolean mark[] = new boolean[n+1];
    graph = new boolean[n+1][n+1];
    for(int []edge: edges) graph[edge[0]+1][edge[1]+1] = graph[edge[1]+1][edge[0]+1] = true;
    for(int i=1; i<=n; i++){
      Arrays.fill(mark, false);
      for(int j=1; j<=n; j++)
        if(graph[i][j] && !mark[i]) res = Integer.min(res, 1+dfs(mark, i, j));
    }
    return res;
  }

  private int dfs(boolean[] mark, int start, int curr){
    if(start == curr) return 0;
    int res = Integer.MAX_VALUE;
    mark[curr] = true;
    for(int i=1; i<mark.length; i++){
      if(!mark[i] && graph[curr][i]){
        System.out.println(start+"::"+i+":"+res);
        res = Integer.min(res, 1+dfs(mark, start, i));
      }
    }
    return res;
  }
}
