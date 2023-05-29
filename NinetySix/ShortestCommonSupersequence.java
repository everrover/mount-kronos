package NinetySix;

import java.util.Arrays;

public class ShortestCommonSupersequence {
  /**
   * https://leetcode.com/problems/shortest-common-supersequence
   * Traversed the DP arr of LCS and found the common part. Filled the gaps to generate SCS as req
   */
  private int dp[][];
  public String shortestCommonSupersequence(String str1, String str2) {
    StringBuilder res = new StringBuilder();
    char []s1 = str1.toCharArray();
    char []s2 = str2.toCharArray();
    dp = new int[s1.length][s2.length];
    for(int []d: dp) Arrays.fill(d, -1);
    int l = lcs(s1, s2, 0, 0);

    // for(int []d: dp) {
    //   for(int c: d){
    //     System.out.print(c+",");

    //   }
    // System.out.println();
    // }
    String lcsS = lcsFinder(s1, s2, dp);
    System.out.println(lcsS);
    int i = 0, j = 0;
    for(char ch: lcsS.toCharArray()){
      while(i<s1.length && ch != s1[i]) {
        // System.out.println(s1[i]);
        res.append(s1[i++]);
      }
      while(j<s2.length && ch != s2[j]) {
        // System.out.println(s2[j]);

        res.append(s2[j++]);
      }
      res.append(ch);
      i++; j++;
    }
    while(i<s1.length) res.append(s1[i++]);
    while(j<s2.length) res.append(s2[j++]);
    return res.toString();
  }

  private int lcs(char []s1, char []s2, int i, int j){
    if(i >= s1.length || j >= s2.length) return 0;
    if(dp[i][j] != -1) return dp[i][j];
    int res = 0;
    if(s1[i] == s2[j]){
      res = lcs(s1, s2, i+1, j+1)+1;
    } else {
      res = Integer.max(lcs(s1,s2,i+1,j), lcs(s1,s2,i,j+1));
    }
    dp[i][j] = res;
    return res;
  }

  private String lcsFinder(char[] s1, char []s2, int dp[][]){
    StringBuilder sb = new StringBuilder();
    int i = 0, j = 0;
    while(i<s1.length && j<s2.length){
      // System.out.println(i+":"+j);
      if(s1[i] == s2[j]){
        sb.append(s1[i]);
        i++; j++;
      }else{
        if(j+1<s2.length && i+1<s1.length){
          if(dp[i][j+1] > dp[i+1][j]) j++;
          else i++;
        }else if(j+1<s2.length){
          j++;
        }else if(i+1<s1.length){
          i++;
        }else{
          i++; j++;
        }
      }
    }
    return sb.toString();
  }
}
