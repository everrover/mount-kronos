package NinetySix;

import java.util.Arrays;

public class SubseqWithMinScore {
  /**
   * https://leetcode.com/problems/subsequence-with-the-minimum-score
   * It was a diff one. Took me 4h alone on it
   * TC: O(ln(n)*(m+n)) SC: O(m+n)
   * Alternate cheaper sol: https://leetcode.com/problems/subsequence-with-the-minimum-score/solutions/3224682/prefix-suffix-time-n-space-n/
   * I m still skeptical weather it's complete or not
   * int minimumScore(String s, String t) {
   *         int n = s.length(),i=0,j=0,m = t.length();
   *         int[] p,su;
   *         p = new int[n]; Arrays.fill(p, Integer.MAX_VALUE);
   *         su = new int[n]; Arrays.fill(su, Integer.MAX_VALUE);
   *         for(i = 0; i < n&&j<m; i++){
   *             if(s[i]==t[j]){
   *                 j++;
   *                 p[i]++;
   *             }
   *             if(i){
   *                 p[i] += p[i-1];
   *             }
   *         }
   *         j = m-1;
   *         for(i = n-1; i >=0&&j>=0; i--){
   *             if(s[i]==t[j]){
   *                 j--;
   *                 su[i]++;
   *             }
   *             if(i!=n-1){
   *                 su[i] += su[i+1];
   *             }
   *         }
   *         int ans = n+10;
   *         for(i = 0; i < n-1; i++){
   *             ans = Integer.min(ans,m-(p[i]+su[i+1]));
   *         }
   *         ans = Integer.min(ans,m-p[n-1]);
   *         ans = Integer.min(ans,m-su[0]);
   *         return Integer.max(ans,0);
   *     }
   *
   */
  public int minimumScore(String s, String t) {
    int high = t.length(), low = 0, mid = 0;
    while(low<=high){
      mid = (low+high)/2;
      if(checkLCS(s, t, mid)) high = mid-1;
      else low = mid+1;
    }
    return high+1;
  }

  private boolean checkLCS(String s, String t, int m){
    if(m >= t.length()) return true; // delete the entire `t`?
    final int slen = s.length(), tlen = t.length();
    int []pos = new int[tlen];
    Arrays.fill(pos, Integer.MAX_VALUE);
    int tleft = 0;
    for(int i=0; i<slen; i++){
      if(tleft == tlen) break;
      else if(t.charAt(tleft) == s.charAt(i)) {
        pos[tleft++] = i; // at which pos we find a complete sub-seq for t[0...tleft] in s
      }
    }

    if(tleft >= tlen-m) return true; // can remove right subarr of `t` sized `m` directly
    int sright = slen-1;
    for(int j=tlen-1; j>=m; j--){
      while(sright >= 0 && s.charAt(sright) != t.charAt(j)) sright--;
      if(sright < 0) return false; // sub-seq not possible with len `m`
        // t[j ... tlen-1] are subseq of s[sright...slen-1] if taken from r->l
      else if(j-m <= 0 || pos[j-m-1] < sright) return true; // skip `m` characters from `j`, and all chs in t[0...j-m-1] are sub-sequence of s[0...sright]
      sright--;
    }
    return false;
  }
}