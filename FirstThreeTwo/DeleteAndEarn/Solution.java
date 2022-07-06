package FirstThreeTwo.DeleteAndEarn;

import java.util.Arrays;

class Solution {
  public int deleteAndEarn(int[] nums) {
    int []hash = new int[10001];
    int []hashSum = new int[10001];
    for(int num: nums){
      hash[num]+=num;
    }
    // now it's house robber
    int dp[] = new int[10001];
    Arrays.fill(dp, -1);
    dp[0] = 0;
    return recurseDP(dp, hash, 10000);
  }

  private int recurseDP(int []dp, int []hash, int idx){
    if(idx<0) return 0;
    if(dp[idx]!=-1){
      return dp[idx];
    }
    int res = 0;
    res = Integer.max(recurseDP(dp, hash, idx-1), recurseDP(dp, hash, idx-2)+hash[idx]);
    dp[idx] = res;
    return res;
  }

  // Bottom up approach
//  private int solve(int []hash, int maxInArr){
//    int res = Integer.MIN_VALUE;
//    for(int i=2; i<=maxInArr; i++){
//      res = Math.max(res, )
//    }
//    return res;
//  }
}