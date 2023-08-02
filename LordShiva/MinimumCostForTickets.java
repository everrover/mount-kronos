package LordShiva;

import java.util.Arrays;

public class MinimumCostForTickets {

  /**
   * https://leetcode.com/problems/minimum-cost-for-tickets/
   * 
   * At each day we have an option to buy a 1-day pass, 7-day pass or 30-day pass.
   * If we buy a 1-day pass, we pay costs[0] and move to the next day.
   * If we buy a 7-day pass, we pay costs[1] and move to the next 7th day.
   * If we buy a 30-day pass, we pay costs[2] and move to the next 30th day. And hence we use `while` loop to skip the days.
   * 
   * P.S. Due to greedy nature of the problem, we don't do the iteration for any day before 7th day or 30th day 
   * when we buy a 7-day pass or 30-day pass respectively.
   * Since, it'll only increase the cost of all the passes. It works but with an increased time-complexity of O(n^2).
   * 
   * TC: O(n) SC: O(n)
   * 
   * #dynamic-programming #array #greedy
   * 
   */

  public int mincostTickets(int[] days, int[] costs) {
    boolean[] daymark = new boolean[366];
    for(int day: days) daymark[day] = true;
    final int n = 366;
    int []dp = new int[366];
    for(int i=1; i<n; i++){
      if(!daymark[i]){
        dp[i]=dp[i-1];
        continue;
      }
      dp[i] = Math.min(
        dp[i-1]+costs[0],
        Math.min(
          dp[Math.max(0, i-7)]+costs[1],
          dp[Math.max(0, i-30)]+costs[2]
        )
      );
    }
    return dp[365];
  }

  // top-down solution
  private int[] days, costs, dp;
  private int n;
  public int mincostTicketsTopDown(int[] days, int[] costs) {
    this.days = days;
    this.costs = costs;
    n = days.length;
    this.dp = new int[n];
    Arrays.fill(dp,-1);
    return dfs(0);
  }

  private int dfs(int idx){
    if(idx>=n) return 0;
    else if(dp[idx] != -1) return dp[idx];
    int res = costs[0] + dfs(idx+1), jdx;
    jdx = idx+1;
    while(jdx<n && (days[idx]+7)>days[jdx]) jdx++;
    res = Math.min(res, costs[1] + dfs(jdx));
    jdx = idx+1;
    while(jdx<n && (days[idx]+30)>days[jdx]) jdx++;
    res = Math.min(res, costs[2] + dfs(jdx));
    return dp[idx] = res;
  }
}
