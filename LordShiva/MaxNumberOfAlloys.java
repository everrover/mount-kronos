package LordShiva;

import java.util.List;

public class MaxNumberOfAlloys {
/**
 * https://leetcode.com/problems/maximum-number-of-alloys/
 * 
 * Found the relevant equation to compute the count of alloys that can be produced by a single m/c.
 * fn(count) = min(count*composition[i][j]-stock[i], 0) * cost[i]
 * 
 * On top of it put in bin-search over solution space for each m/c. 
 * Basically, performed optimization with bin-search on brute-force solution.
 * 
 * #contest #binary-search #greedy
 * TC: O(n*log(1e9)*m) SC: O(1)
 */

  // private static long MOD = (int)(1e10);
  public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
    long res = 0;
    for(List<Integer> composite: composition){
      long l = 0, r = (int)(1e9), m;
      while(l<=r){
        m = l + (r - l) /2;
        if(findCost(composite, stock, cost, n, m, budget)){
          res = Math.max(res, m);
          l = m+1;
        }else{
          r = m-1;
        }
      }
    }
    return (int)res;
  }
  private boolean findCost(List<Integer> composite, List<Integer> stock, List<Integer> costMetal, int n, long units, int budget){
    long cost = 0;
    for(int i=0; i<n; i++){
      cost += Math.max(units*composite.get(i)-stock.get(i), 0)*costMetal.get(i);
      if(cost > budget) return false;
    }
    return cost<=budget;
  }
}
