package SuperMario;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
  /**
   * https://leetcode.com/problems/number-of-provinces/
   * Can use simple DFS/BFS
   * For optimizing the code mentioned below, can use AdjacencyList => TC: O(V+E) SC: O(V+E)
   * TC: O(n^2)[Due to Adjacency Matrix] SC: O(n)
   */
  public int findCircleNum(int[][] isC) {
    final int n = isC.length;
    int res = 0;
    Queue<Integer> q = new LinkedList<>();
    boolean[] mark = new boolean[n];
    for(int i=0; i<n; i++){
      if(!mark[i]){
        q.offer(i);
        res++;
        while(!q.isEmpty()){
          int polled = q.poll();
          mark[polled] = true;
          for(int j=0; j<n; j++){
            if(isC[polled][j]==1 && !mark[j]){
              q.offer(j);
            }
          }
        }
      }
    }
    return res;
  }

  /*
  public void dfsUtil(int [][]isConnected, boolean []visited, int curr){
        visited[curr] = true;
        for(int i=0; i<isConnected.length; i++){
            if(isConnected[curr][i] == 1 && !visited[i]){
                dfsUtil(isConnected, visited, i);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        boolean []visited = new boolean[isConnected.length];
        int ans=0;
        for(int i=0; i<isConnected.length; i++){
            if(!visited[i]){
                ans++;
                dfsUtil(isConnected, visited, i);
            }
        }
        return ans;
    }
   */
}