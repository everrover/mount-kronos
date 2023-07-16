package SuperMario;

public class DeleteColumnsToMakeSortedIII {

  /**
   * https://leetcode.com/problems/delete-columns-to-make-sorted-iii/
   * TC: O(n^2*m) SC: O(n)
   * 
   * Simply used LIS to find the longest increasing subsequence.
   *
   * #dynamic-programming #longest-increasing-subsequence #no-effort #string
   */

  private char [][]strs;
  private int []dp;
  private int slen;
  private int stlen;
  public int minDeletionSize(String[] Ss) {
    strs = new char[Ss.length][];
    slen = Ss.length;
    stlen = Ss[0].length();
    dp = new int[stlen];
    for(int i=0; i<slen; i++) strs[i] = Ss[i].toCharArray();
    int res = 0;
    for(int i=0; i<stlen; i++)
      res = Integer.max(res, lis(i));
    return stlen-res;
  }

  private int lis(int idx){
    if(idx>=stlen) return 0;
    if(dp[idx]>0) return dp[idx];
    int res = 1;
    for(int j=idx+1; j<stlen; j++){
      if(check(idx, j)){
        res = Math.max(res, 1+lis(j));
      }
    }
    return dp[idx] = res;
  }
  private boolean check(int idx1, int idx2){
    for(int i=0; i<slen; i++){
      if(strs[i][idx1] > strs[i][idx2]) return false;
    }
    return true;
  }
}
