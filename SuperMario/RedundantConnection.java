package SuperMario;

public class RedundantConnection {

  /*
    https://leetcode.com/problems/redundant-connection/description/
    TC: O(n) SC: O(n)
    Used DisjointSet to solve this problem. Essentially it's just cycle detection with Disjoint Sets and UnionFind algorithm. Cheers!
   */
  private static class DisjointSet{
    int []parent;
    int []rank;
    DisjointSet(int n){
      parent = new int[n];
      rank = new int[n];
      for(int i=0; i<n; i++){
        parent[i] = i;
      }
    }

    public int find(int x){
      if(parent[x] != x){
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    public void union(int x, int y){
      int px = find(x);
      int py = find(y);
      if(px == py) return;
      if(rank[px] > rank[py]){
        parent[py] = px;
      }else if(rank[px] < rank[py]){
        parent[px] = py;
      }else{
        parent[px] = py;
        rank[py]++;
      }
    }
  }

  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    DisjointSet ds = new DisjointSet(n+1);
    int []res = null;
    for(int[] edge: edges){
      if(ds.find(edge[0]) == ds.find(edge[1])) res = edge;
      ds.union(edge[0], edge[1]);
    }
    return res;
  }
}
