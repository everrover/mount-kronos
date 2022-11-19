package SixtyFour;

/**
 * https://leetcode.com/problems/course-schedule/
 * WE BASICALLY CHECK IF CYCLE EXISTS OR NOT! If it does, it'll mean all courses in the cycle can't be completed.
 * The course requirement graph can have several-different graph components.
 */

public class CourseScheduling {
  class Solution {
    public boolean adj[][], visited[], rec[];
    public boolean canFinish(int num, int[][] pre) {
      adj = new boolean[num][num];
      for(int []pair: pre){
        adj[pair[1]][pair[0]] = true;
      }
      return !cycleDetection(adj, num);
    }
    public boolean cycleDetection(boolean [][]adj, int num){
      visited = new boolean[num];
      rec = new boolean[num];
      for(int i=0; i<num; i++){
        if(cycleDetectionUtil(i)) return true;
      }
      return false;
    }
    public boolean cycleDetectionUtil(int curr){
      if(rec[curr]) return true;
      if(visited[curr]) return false;
      visited[curr] = true;
      rec[curr] = true;
      for(int i=0; i<visited.length; i++){
        if(adj[curr][i] && cycleDetectionUtil(i)) return true;
      }
      rec[curr] = false;
      return false;
    }
    // public boolean dfs(boolean [][]adj, int num){
    //     boolean visited[] = new boolean[num];
    //     for(int i=0; i<num; i++){
    //         if(!visited[i] && !dfsUtil(adj, visited, i))
    //             return false;
    //     }
    //     return true;
    // }
    // public boolean dfsUtil(boolean [][]adj, boolean []visited, int curr){
    //     for(int i=0; i<visited.length; i++){
    //         if(visited[i]) return false;
    //         if(adj[curr][i] && !dfsUtil(adj, visited, i))
    //             return false;
    //     }
    //     visited[curr] = true;
    //     return true;
    // }
  }

  public static void main(String[] args) {

  }
}
