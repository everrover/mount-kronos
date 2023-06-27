package SuperMario;

public class LongestChunkedPalindromeDecomposition {
  /**
   * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/
   * Used DP to find the longest chunked palindrome decomposition.
   * TC: O(n^2) SC: O(n^2)
   *
   * Two-pointer approach: The my way ;)
   * // sol here
   * TC: O(n*2) SC: O(1)
   *
   * Tail Recursion: https://leetcode.com/problems/longest-chunked-palindrome-decomposition/solutions/350560/java-c-python-easy-greedy-with-prove/
   * // sol here
   * TC: O(n*2) SC: O(1)
   *
   * #dynamic-programming #palindrome #no-effort #two-pointer #string
   */
//  public int longestDecomposition(String text) {
//    int n = text.length();
//    int [][]dp = new int[n][n];
//    for(int i=0; i<n; i++) dp[i][i] = 1;
//    for(int i=0; i<n-1; i++) dp[i][i+1] = text.charAt(i) == text.charAt(i+1) ? 2 : 1;
//    for(int l=3; l<=n; l++){
//      for(int i=0; i<n-l+1; i++){
//        int j = i+l-1;
//        dp[i][j] = 1;
//        for(int k=i; k<j; k++){
//          if(text.charAt(k) == text.charAt(j)){
//            dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j]);
//          }
//        }
//      }
//    }
//    return dp[0][n-1];
//  }

  public int longestDecomposition(String text) {
    char str[] = text.toCharArray();
    int l1 = 0, l2 = 0, r2 = str.length-1, r1 = str.length-1, res = 0;
    while(l1<=r1){
      // System.out.println(l1+":"+r2);
      while(r2>l1 && str[r2]!=str[l1]) r2--;
      int len = Math.abs(r1-r2+1);
      boolean isEq = text.substring(l1, l1+len).equals(text.substring(r2, r2+len));
      // System.out.println(text.substring(l1, l1+len)); // needed debugging for case `ababa`, where `i` set l1<=r1 instead of l1<r1
      // System.out.println(text.substring(r2, r2+len));
      // System.out.println(len);
      if(isEq){
        res+=l1==r2?1:2;
        r1 = r2 = r2-1;
        l1 += len;
      }else{
        r2--;
      }
    }
    return res;
  }
}
