package SuperMario;

import java.util.Arrays;

public class TallestBillboard {
  /**
   * https://leetcode.com/problems/tallest-billboard/
   * Used DP to find the tallest possible billboard.
   * TC: O(n*sum) SC: O(n*sum)
   *
   * Needed help in this. I was able to come up with the recursive function, but wasn't able to translate 3D DP to 2D DP.
   * My original code:
   private int []rods;
   public int tallestBillboard(int[] rods) {
     this.rods = rods;
     return dfs(0, 0, 0);
   }

   private int dfs(int i, int sA, int sB){ // tried memoization, but ofcourse it was breaching memory bounds
     int lsum = sA == sB?sA:0;
     if(i>=rods.length) return lsum;
     lsum = Math.max(dfs(i+1, sA, sB), Math.max(dfs(i+1, sA, sB+rods[i]), dfs(i+1, sA+rods[i], sB)));
     return lsum;
   }
   *
   * #dynamic-programming #knapsack #memoization #tricky #unbounded-knapsack
   */
  private int []rods;
  private int [][]dp;
  private int sum;
  public int tallestBillboard(int[] rods) {
    this.rods = rods;
    this.sum = Arrays.stream(rods).sum();
    this.dp = new int[rods.length][2*sum+1];
    for(int []d: dp) Arrays.fill(d, -1);
    return dfs(0, 0);
  }

  /* `diff` is the difference between the sum of rods in set A and set B
  // it is sufficient to store only the difference,
  // because for any sum of rods in set A and set B, if difference is the same for `i`th rod
  // the only variable remaining are the remaining rods, which are not yet put in either set
  // so, if we know the difference, we can easily calculate the sum of rods in set A and set B
   */
  private int dfs(int i, int diff){ // for each rod, we have 3 choices: put it in set A, put it in set B, or don't put it at all
    if(i>=rods.length){
      if(diff == 0) return 0;
      return Integer.MIN_VALUE;
    }
    if(dp[i][diff+sum] != -1) return dp[i][diff+sum];
    int lsum = Math.max(
        dfs(i+1, diff), // don't put it at all
        Math.max(
            rods[i]+dfs(i+1, diff+rods[i]), // put it in set A
            dfs(i+1, diff-rods[i]) // put it in set B
        )
    );
    return dp[i][diff+sum]=lsum;
  }
}
