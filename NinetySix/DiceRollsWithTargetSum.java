package NinetySix;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 * Simply a variant of Coin-Change problem::https://leetcode.com/problems/coin-change-ii/
 */
public class DiceRollsWithTargetSum {
  int [][]dp = null;
  int []dps = null;
  int k=0;
  public int numRollsToTarget(int n, int k, int target) {  // TC:: O(n*k*target), MC:: O(n*k)
    dp = new int[n+1][target+1];
    this.k = k;
    for(int []d: dp) Arrays.fill(d, -1);
    return numRollsToTargetUtils(n, target);
  }
  private int numRollsToTargetUtils(int n, int target){
    if(target <= 0 || n == 0) return target == 0?1:0;
    else if(dp[n][target] != -1) return dp[n][target];

    long res = 0;
    for(int i=1; i<=k; i++){
      res = (numRollsToTargetUtils(n-1, target-i) + res) % (long)(1e9+7);
    }
    dp[n][target] = (int)res;
    return dp[n][target] ;
  }


  public int numRollsToTargetBottomUp(int n, int k, int target) { // TC:: same, MC:: same
    dp = new int[n+1][target+1];
    this.k = k;
    dp[0][0] = 1;
    for(int i=1; i<= n; i++){
      for(int j=1; j<=k; j++){
        // dp[i][j] = dp[i-1][j];
        for(int l=j; l<=target; l++){
          dp[i][l] = (int)((dp[i][l] + dp[i-1][l-j])%1000000007);
        }
      }
    }
    return dp[n][target];
  }

  public int numRollsToTargetMemoryOptimized(int n, int k, int target) { // TC:: same, MC:: O(target)
    dps = new int[target+1];
    this.k = k;
    dps[0] = 1;
    for(int i=1; i<= n; i++){
      for(int j=target; j>=0; j--){
        dps[j] = 0;
        for(int l=1; l<=k && j-l >= 0; l++){
          dps[j] = (int)((dps[j] + dps[j-l])%1000000007);
        }
      }
    }
    return dps[target];
  }

  public static void main(String[] args) {
    new DiceRollsWithTargetSum().numRollsToTarget(30, 30, 50);
  }
}
