package NinetySix;

import java.util.*;

public class MinimumDegreeOfConnectedTrioInGraph {
  public int minTrioDegree(int n, int[][] edges) {
    int []degs = new int[n+1];
    boolean[][] adj = new boolean[n+1][n+1];
    for(int []edge: edges){
      adj[edge[0]][edge[1]]=true;
      adj[edge[1]][edge[0]]=true;
      degs[edge[0]]++;
      degs[edge[1]]++;
    }
    int res = Integer.MAX_VALUE;
    for(int []edge: edges){
      int a = edge[0], b = edge[1];
      for(int c=1; c<=n; c++){
        if(adj[a][c] && adj[b][c]){
          res = Math.min(res, degs[a]+degs[b]+degs[c]-6);
        }
      }
    }
    return res == Integer.MAX_VALUE? -1:res;
  }
  // Sol 2 :: same logic
  public int minTrioDegreesSet2(int n, int[][] edges) {
    // int []status = new int[n+1]; // colors: 0,1(partial),2(complete)
    int []degs = new int[n+1];
    Set<Integer>[] adj = new Set[n+1];
    // List<List<Integer>> trios = new LinkedList<>();
    for(int i=1; i<=n; i++){
      adj[i] = new HashSet<>();
    }
    for(int []edge: edges){
      adj[edge[0]].add(edge[1]);
      adj[edge[1]].add(edge[0]);
      degs[edge[0]]++;
      degs[edge[1]]++;
    }
    int res = Integer.MAX_VALUE;
    for(int a=1; a<=n; a++){
      for(int b=a+1; b<=n; b++){
        for(int c=b+1; c<=n; c++){
          if(adj[a].contains(b) && adj[a].contains(c) && adj[b].contains(c)){
            res = Math.min(res, degs[a]+degs[b]+degs[c]-6);
          }
        }
      }
    }
    return res == Integer.MAX_VALUE? -1:res;
  }

  // Sol 3:: Same logic
  public int minTrioDegreeSol3(int n, int[][] edges) {
    // int []status = new int[n+1]; // colors: 0,1(partial),2(complete)
    int []degs = new int[n+1];
    Set<Integer>[] adj = new Set[n+1];
    // List<List<Integer>> trios = new LinkedList<>();
    for(int i=1; i<=n; i++){
      adj[i] = new HashSet<>();
    }
    for(int []edge: edges){
      adj[edge[0]].add(edge[1]);
      adj[edge[1]].add(edge[0]);
      degs[edge[0]]++;
      degs[edge[1]]++;
    }
    int res = Integer.MAX_VALUE;
    for(int a=1; a<=n; a++){
      for(int b: adj[a]){
        for(int c: adj[b]){
          if(adj[a].contains(c)){
            res = Math.min(res, degs[a]+degs[b]+degs[c]-6);
          }
        }
      }
    }
    return res == Integer.MAX_VALUE? -1:res;
  }

  // Attempted to compute trio's using next-to-next traversal and status marking nodes:: #incorrect
  public int minTrioDegreeSol4(int n, int[][] edges) {
    int []status = new int[n+1]; // colors: 0,1(partial),2(complete)
    int []degs = new int[n+1];
    Set<Integer>[] adj = new Set[n+1];
    List<List<Integer>> trios = new LinkedList<>();
    for(int i=1; i<=n; i++){
      adj[i] = new HashSet<>();
    }
    for(int []edge: edges){
      adj[edge[0]].add(edge[1]);
      adj[edge[1]].add(edge[0]);
      degs[edge[0]]++;
      degs[edge[1]]++;
    }
    for(int curr=1; curr<=n; curr++){
      for(int next: adj[curr]){
        if(status[next]>0) continue;
        status[next] = 1;
        for(int pos3: adj[next]){
          if(status[pos3]>0) continue;
          if(adj[next].contains(pos3) && adj[curr].contains(pos3)){
            List<Integer> trio = new ArrayList<>();
            trio.add(curr);
            trio.add(next);
            trio.add(pos3);
            trios.add(trio);
          }
        }
      }
      status[curr] = 2;
    }
    int res = Integer.MAX_VALUE;
    for(List<Integer> trio: trios){
      res = Math.min(res, degs[trio.get(0)]+degs[trio.get(1)]+degs[trio.get(2)]-6);
    }
    return res == Integer.MAX_VALUE? -1:res;
  }
}
