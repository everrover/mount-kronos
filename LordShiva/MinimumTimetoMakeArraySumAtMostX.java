package LordShiva;

import java.util.Arrays;
import java.util.List;

public class MinimumTimetoMakeArraySumAtMostX {
  /**
   * https://leetcode.com/problems/minimum-time-to-make-array-sum-at-most-x/
   * 
   * Misread the statement and worked with the assumption that all elements must be less than or equal to x.
   * Deleting an element more than once is allowed, but isn;t needed.
   * 
   * Logic:: If no deletions occur, then sum of all elements is `sa+sb*n`. 
   * If we are at `t` seconds, then we can delete atmost `t` elements. SUM = sa+sb*t-SUM
   * SUM = bt*t+at + bt*(t-1)+a2 + b3*(t-2)+a3 + ... + bt*0+at, with any possible combination of atopst `t` elements.
   * Maximum SUM would be obtained if we delete the largest elements first.
   * 
   * TC: O(n^2) SC: O(n^2)
   * 
   * Since, dp[i][j] is dependent only on dp[i-1][j] and dp[i-1][j-1], we can optimize the space complexity to O(n) by using a single array.
   * #dynamic-programming #array #sorting #greedy #tricky
   */

  public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
    int sa =0, sb = 0, n = nums1.size();
    int [][]dp = new int[n+1][n+1];
    int[][] arr = new int[n][2];
    for (int i = 0; i < n; i++) {
      int a = nums1.get(i), b = nums2.get(i);
      arr[i][0]=b; arr[i][1]=a;
      sa += a; sb += b;
    }
    Arrays.sort(arr, (a,b)->a[0]-b[0]);
    if(sa<=x) return 0;
    for(int i=1; i<=n; i++){ // Pair which is deleted
      for (int j=1; j<=i; j++){ // number of seconds
        int withoutIthPair = dp[i-1][j]; // with `i-1` elements at `j` seconds
        int withIthPair = dp[i-1][j-1] + j*arr[i-1][0] + arr[i-1][1]; // with `i-1` elements at `j-1` seconds + sum provided by current element
        dp[i][j] = Math.max(withIthPair, withoutIthPair);
      }
    }
    for(int i=0; i<=n; i++){
      if(sb*i+sa-dp[n][i] <= x) return i;
    }
    return -1;
  }
}
