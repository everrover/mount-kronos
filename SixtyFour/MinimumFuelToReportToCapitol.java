package SixtyFour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */

public class MinimumFuelToReportToCapitol {
  private static boolean []isVisited;
  private static Map<Integer, List<Integer>> routes;
  private static long res;
  private static int seats;
  public long minimumFuelCost(int[][] roads, int seats) {
    if(roads.length == 0) return 0;
    res = 0;
    MinimumFuelToReportToCapitol.seats = seats;
    routes = new HashMap<>();
    for(int []road: roads){
      routes.putIfAbsent(road[0], new ArrayList<>());
      routes.putIfAbsent(road[1], new ArrayList<>());
      routes.get(road[0]).add(road[1]);
      routes.get(road[1]).add(road[0]);
    }
    isVisited = new boolean[routes.size()];
    dfs(0);
    dfs(0, 0);
    return res;
  }

  private long dfs(int curr){ // all fuel calculations on current node's parent's edge for all child elements
    if(isVisited[curr]) return 0L;
    isVisited[curr] = true;
    long carried = 1;
    for(int to: routes.get(curr)){
      carried += dfs(to);
    }
    if(curr == 0) return 0L;
    res += (carried/seats) + ((carried%seats > 0)?1:0);
    return carried;
  }

  private long dfs(int curr, int depth){ // all fuel calculations on current node only
    if(isVisited[curr]) return 0L;
    isVisited[curr] = true;
    long carried = 1L;
    for(int to: routes.get(curr)){
      carried += dfs(to, depth+1);
    }
    if(depth == 0) return 0;
    res += ((carried/seats)*depth) + ((carried%seats > 0)?1:0);
    return carried%seats;
  }
}
