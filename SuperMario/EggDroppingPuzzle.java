package SuperMario;

import java.util.Arrays;

public class EggDroppingPuzzle {

  /**
   * https://leetcode.com/problems/super-egg-drop/
   * // sol1
   * Used Binary Search to find the minimum number of drops required to find the critical floor.
   * If egg breaks, we need to check for the floors below it, else we need to check for the floors above it.
   * Recurrance(DFS): sol(n, k) = min(1+max(sol(i-1, k-1), sol(n-i, k))) for i in [1, n] // i is the floor from which we drop the egg
   * Recurrance with b.search: sol(n, k) = 1+max(sol(mid-1, k-1), sol(n-mid, k)) for mid in [1, n] via Binary Search
   * TC: O(n*k*log(n)) SC: O(n*k)
   * // sol2 - read about it online - https://leetcode.com/problems/super-egg-drop/solutions/158974/c-java-python-2d-and-1d-dp-o-klogn/
   * As suggested by lee215:
   * "So I consider this problem in a different way: dp[M][K] means that, given K eggs and M moves, what is the maximum number of floor that we can check."
   * Optimized recurrance(BFS): dp[n][k] = 1+max(dp[n-1][k-1], dp[n-1][k]) for i in [1, n] via Binary Search
   * public int superEggDrop(int K, int N) {
   *   int[][] dp = new int[N + 1][K + 1];
   *   int m = 0;
   *   while (dp[m][K] < N) {
   *       ++m;
   *       for (int k = 1; k <= K; ++k) dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
   *   }
   *   return m;
   * }
   * In worst case, the number of drops required is `n`. At each step, either egg can break(dp[m-1][k-1] floors to check) or not(dp[m-1][k] floors to check).
   * TC: O(n*k) SC: O(n*k)
   * #binary-search #dynamic-programming #tricky-optimization
   */
  int [][]dp;
  public int superEggDrop(int k, int n) {
    int res = Integer.MAX_VALUE;
    dp = new int[n+1][k+1];
    for(int i=2; i<=n; i++) {
      Arrays.fill(dp[i], -1);
      dp[i][1] = i;
    }
    Arrays.fill(dp[0], 0);
    Arrays.fill(dp[1], 1);
    res = droprecurse(k, n);
    return res;
  }

  private int droprecurse(int k, int n){
    if(dp[n][k]!=-1) return dp[n][k];
    int res = Integer.MAX_VALUE, l=1, r=n, mid;
    while(l<=r){
      mid = (l+r)/2;
      int left = droprecurse(k-1, mid-1);
      int right = droprecurse(k, n-mid);
      if(left<right){
        l = mid+1;
      }else{
        r = mid-1;
      }
      res = Math.min(res, 1+Math.max(left, right));
    }
    return dp[n][k] = res;
  }
}
