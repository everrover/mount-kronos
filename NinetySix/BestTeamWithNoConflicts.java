package NinetySix;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-team-with-no-conflicts/
 * Brute DP:: Packaged scores and ages together, sorted them across age and score(tiebreaker), and applied DP over the recursion
 * sol(prev, curr) = max(
 *      [SKIPPED CURRENT CASE] sol(prev, curr+1),
 *      [USED CURRENT CASE] sol(curr, curr+1)+scores[curr], age[curr]==age[prev] || scores[prev] >= scores[curr]
 * )
 *
 * Using LIS:: DP could also have been applied over scores list after sorting across age and score(for tiebreakers) as in for
 * Longest Increasing Subsequence(LIS). Instead of incrementing our result by 1 as in LIS, we'd increment it by scores.
 *
 * Using BIT:: We could construct a BIT of scores after sort operation. At each step `i` we'd query to find sum of scores where
 * scores[0...i-1] >= scores[i]. For ones with same age, all scores will be greater than score[i], hence scores[j+1...i-1] >= scores[i].
 * For all others with larger ages(last larger age at `j`, we'd want sum of their sum(scores[k], 0<=k<=j where scores[k] >= scores[i])
 * to be higher than score[i] to avoid conflicts. Hence, one aggregated query is enough.
 *
 * AggregatedScoreAt(i) = sum(scores[k])+scores[i], k=0...i, where scores[k]>=scores[i]
 * TC: O(n*log(m)+n*log(n)) MC: O(n+m)
 * This isn't implemented below...
 */

public class BestTeamWithNoConflicts {
  private static class SA implements Comparable<SA>{
    public int s, a;
    public SA(int s, int a){
      this.s = s;
      this.a = a;
    }
    @Override
    public int compareTo(SA two){
      if(this.a == two.a) return two.s-this.s;
      return two.a-this.a;
    }
  }
  // TC: O(n^2+n*log(n)), MC: O(n)
  public int bestTeamScoreLIS(int[] scores, int[] ages) {
    SA []sas = new SA[scores.length];
    for(int i=0; i<scores.length; i++){
      sas[i] = new SA(scores[i], ages[i]);
    }
    Arrays.sort(sas);
    int res = Integer.MIN_VALUE;
    int[] dp = new int[ages.length];
    for(int i=0; i<scores.length; i++){
      ages[i] = sas[i].a;
      res = Math.max(res, dp[i] = scores[i] = sas[i].s);
    }
    // Longest non-incresing subsequence
    for(int i=0; i<ages.length; i++){
      for(int j=i-1; j>=0; j--){
        if(scores[i] <= scores[j]){
          dp[i] = Math.max(dp[i], dp[j]+scores[i]);
        }
      }
      res = Math.max(dp[i], res);
    }

    return res;
  }

  // TC: O(n^2+n*log(n)), MC: O(n^2)
  public int bestTeamScoreDP(int[] scores, int[] ages) {
    SA []sas = new SA[scores.length];
    for(int i=0; i<scores.length; i++){
      sas[i] = new SA(scores[i], ages[i]);
    }
    Arrays.sort(sas);
    for(int i=0; i<scores.length; i++){
      scores[i] = sas[i].s;
      ages[i] = sas[i].a;
    }
    // for(SA sa: sas){
    //   System.out.println(sa.s+":"+sa.a);
    // }
    int[][] dp = new int[ages.length+2][ages.length];
    int res = solve(ages, scores, dp, -1, 0);
    return res;
  }

  private int solve(int []ages, int []scores, int [][]dp, int prev, int curr){
    if(curr == ages.length){
      return 0;
    }else if(dp[prev+2][curr] != 0){
      return dp[prev+2][curr];
    }
    int res = solve(ages, scores, dp, prev, curr+1);
    if(prev == -1 || ages[prev] == ages[curr] || scores[prev] >= scores[curr]){
      res = Integer.max(solve(ages, scores, dp, curr, curr+1)+scores[curr], res);
    }
    dp[prev+2][curr] = res;
    return res;
  }
}
