package LordShiva;

import java.util.Arrays;
import java.util.List;

public class MinTimeToMakeArraySumAtMostX {
  /**
   * https://leetcode.com/problems/minimum-time-to-make-array-sum-at-most-x/description/
   * 
   * Obs1:: Deleting an element more than once is allowed, but isn't needed. So, max number of deletions is `n`.
   * Obs2:: If no deletions occur, then sum of all elements is `sa+sb*n`.
   * Obs3:: If we are at `t` seconds, then we can delete atmost `t` elements. Total remaining = sa+sb*t-SUM
   * Obs4:: SUM = bt*t+at + bt*(t-1)+a2 + b3*(t-2)+a3 + ... + bt*0+at, with any possible combination of atmost `t` elements.
   * 
   * Logic:: Maximum SUM would be obtained if we delete the largest elements last(Obs4). Largest elements are sorted in ascending order of `b`.
   * For any number to be deleted, at time `t` the sum for nodes below it is calculated again and again. Hence DP/memoization over it was essential.
   * 
   * #tricky #very-hard #dynamic-programming #array #sorting #greedy #ai-wrote-this-description #contest
   * P.S. Resolved after contest.
   * 
   * TC: O(n^2) SC: O(n)
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

  private int sa, sb, n;
  private int[][] arr;
  private int []dp;
  public int minimumTimeSpaceOptimised(List<Integer> nums1, List<Integer> nums2, int x) {
    sa = sb = 0;
    n = nums1.size();
    dp = new int[n+1];
    arr = new int[n][2];
    for (int i = 0; i < n; i++) {
      int a = nums1.get(i), b = nums2.get(i);
      arr[i][0]=b; arr[i][1]=a;
      sa += a; sb += b;
    }
    Arrays.sort(arr, (a,b)->a[0]-b[0]);
    Arrays.fill(dp, 0);
    for(int j=0; j<n; j++){
      for (int i=j+1; i>0; i--){
        dp[i] = Math.max(dp[i], dp[i-1]+i*arr[j][0]+arr[j][1]);
      }
    }
    for(int i=0; i<=n; i++){
      if(sb*i+sa-dp[i] <= x) return i;
    }
    return -1;
  }
}
