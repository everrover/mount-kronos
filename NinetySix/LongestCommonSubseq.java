package NinetySix;

import java.util.Arrays;

public class LongestCommonSubseq {
  /**
   * Fairly standard stuff: https://leetcode.com/problems/longest-common-subsequence
   * Applied DP to store sub-sol for recursion: dp(s1,s2,i,j) = s1[i] == s2[j]?dp(s1,s2,i+1,j+1)+1: max(dp(s1,s2,i+1,j), dp(s1,s2,i,j+1))
   * TC: O(m*n), SC: O(m*n)
   * Could have used top-down approach as well...
   * dp[i][j] = s1[i] == s2[j]?dp[i-1][j-1]+1: max(dp[i-1][j], dp[i][j-1])
   * TC: O(m*n), SC: O(n)
   * Space optimized version:
   * dp[i] = s1[i] == s2[j]?dp[i-1]+1: max(dp[i-1], dp[i]) and would have saved the space as well
   */
  private int dp[][];
  public int longestCommonSubsequence(String text1, String text2) {
    char []s1 = text1.toCharArray();
    char []s2 = text2.toCharArray();

    dp = new int[s1.length][s2.length];
    for(int []d: dp) Arrays.fill(d, -1);
    return lcs(s1, s2, 0, 0);
  }

  private int lcs(char []s1, char []s2, int i, int j){
    if(i >= s1.length || j >= s2.length) return 0;
    if(dp[i][j] != -1) return dp[i][j];
    int res = 0;
    if(s1[i] == s2[j]) res = lcs(s1, s2, i+1, j+1)+1;
    else res= Integer.max(lcs(s1,s2,i+1,j), lcs(s1,s2,i,j+1));
    dp[i][j] = res;
    return res;
  }

}
