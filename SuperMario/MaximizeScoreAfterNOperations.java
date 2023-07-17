package SuperMario;

public class MaximizeScoreAfterNOperations {
  /*
   * https://leetcode.com/problems/maximize-score-after-n-operations/
   * 
   * Performed a backtracking with memoization to find the maximum score.
   * And for storing the gcd of all pairs, used a 2D array.
   * Also, used bitmasking to store the visited pairs.
   * 
   * TC: O(n*2^n) SC: O(2^n)
   * #backtracking #dynamic-programming #gcd #memoization #tricky-optimization #bitmasking
   */


  private int m, n;
  private int [][]gcdarr;
  private int []dp;
  public int maxScore(int[] nums) {
    m = nums.length; n = nums.length/2;
    gcdarr = new int[m][m];
    for(int i=0; i<m; i++){
      for(int j=i+1; j<m; j++){
        gcdarr[i][j] = gcd(nums[i], nums[j]);
      }
    }
    dp = new int[1<<14];
    return backtrack(1, 0);
  }

  private int backtrack(int k, int mask){
    if(k>n) return 0;
    if(dp[mask]>0) return dp[mask];
    int res = 0;
    for(int i=0; i<m; i++){
      if((mask & (1<<i)) > 0) continue;
      for(int j=i+1; j<m; j++){
        if((mask & (1<<j)) > 0) continue;
        int newmask = mask | 1<<i | 1<<j;
        res = Math.max(res, k*gcdarr[i][j] + backtrack(k+1, newmask));
      }
    }
    return dp[mask] = res;
  }

  private int gcd(int a, int b){
    if(a==0) return b;
    return gcd(b%a, a);
  }
}
