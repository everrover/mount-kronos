package LordShiva;

import java.util.List;

public class SortThreeGroups {
  /*
   * https://leetcode.com/problems/sorting-three-groups/description/
   * 
   * Simple greedy memoization was applied.
   * 
   * Thought initially:: 
   * Could have used two iterations for left and right, counting the number of swaps reqd for 1,2&3 group respectively.
   * And for each `i`(0->(n-2)) would have found a `j`(i+1->(n-1)) such that minimum number of swaps for 2&3 was needed for all three splits. 
   * TC would have been O(n^2) and SC O(n)
   * 
   * TC: O(n) SC: O(1)
   * 
   * #easy #greedy #memoization #dp #contest
   */


  public int minimumOperations(List<Integer> nums) {
    int n = nums.size();
    int [][]dp = new int[n][3];
    dp[0][0] = dp[0][1] = dp[0][2] = 1;
    dp[0][nums.get(0)-1] = 0;
    for(int i=1; i<n; i++){
      for(int j=1; j<=3; j++){
        int res = Integer.MAX_VALUE;
        for(int k=j; k>0; k--) res = Math.min(res, ((nums.get(i)==j)?0:1)+dp[i-1][k-1]);
        dp[i][j-1] = res;
      }
    }    
    return Math.min(dp[n-1][0], Math.min(dp[n-1][2], dp[n-1][1]));
  }
}
