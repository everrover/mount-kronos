package SuperMario;

import java.util.Arrays;

public class CountAllRoutes {

  /**
   * https://leetcode.com/problems/count-all-possible-routes/
   * Used DFS(essentially memoization based DP only to mark nodes for which computation is already done) to find the
   * number of paths for a given amount of fuel and current position.
   * TC: O(n*f) SC: O(n*f)
   * #dfs #dynamic-programming #array
   */

  private int start, finish;
  private int []locations;
  private int [][]dp;
  private static final int MOD = (int)1e9+7;

  public int countRoutes(int[] locations, int start, int finish, int fuel) {
    this.start = start; this.finish = finish;
    this.locations = locations;
    dp = new int[locations.length][fuel+1];
    for(int []d: dp){
      Arrays.fill(d, -1);
    }
    return traverse(start, fuel);
  }
  private int traverse(int pos, int fuel) {
    if(dp[pos][fuel] != -1) return dp[pos][fuel];
    int dis = Math.abs(locations[finish]-locations[pos]);
    if(dis>fuel) return dp[pos][fuel] = 0;
    long res = pos==finish?1:0;
    for(int i=0; i<locations.length; i++){
      int ff = fuel-Math.abs(locations[i]-locations[pos]);
      if(pos != i && ff >= 0){
        int cnt = traverse(i, ff);
        res = (res+cnt)%MOD;
      }
    }
    return dp[pos][fuel] = (int)(res%MOD);
  }
}
