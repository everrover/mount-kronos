package SuperMario;

import java.util.ArrayList;
import java.util.List;

public class InformEmployee {

  /*
  https://leetcode.com/problems/time-needed-to-inform-all-employees/
  TC: O(n) SC: O(n)
  Used DFS to traverse the tree. Could have used BFS(Level-order traversal) as well. Cheers!
   */

  private static class Node{
    int id;
    int time;
    List<Node> next;
    Node(int id, int time){
      this.id = id;
      this.time = time;
      this.next = new ArrayList<>();
    }

    public void addNode(Node node){
      this.next.add(node);
    }
  }
  
  public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    boolean []mark = new boolean[n];
    Node[] nodes = new Node[n];
    for(int i=0; i<n; i++){
      nodes[i] = new Node(i, informTime[i]);
    }
    for(int i=0; i<n; i++){
      if(manager[i] != -1){
        nodes[manager[i]].addNode(nodes[i]);
      }
    }
    // perform dfs
    return informTime[headID] + dfs(nodes[headID], mark);
  }

  private int dfs(Node node, boolean []mark){
    if(mark[node.id]) return 0;
    mark[node.id] = true;
    int res = 0;
    for(Node next: node.next){
      res = Math.max(res, next.time+dfs(next, mark));
    }
    return res;
  }

  // private int dfs(int []manager, int []informTime, int headID, boolean []mark){
  //   if(mark[headID]) return 0;
  //   mark[headID] = true;
  //   int res = 0;
  //   for(int i=0; i<manager.length; i++){
  //     if(manager[i] == headID){
  //       res = Math.max(res, informTime[i]+dfs(manager, informTime, i, mark));
  //     }
  //   }
  //   return res;
  // }
  
}
