package SuperMario;

import java.util.Arrays;

public class MinimumDistancetoTypeWordUsingTwoFingers {

  /**
   * https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/
   * Used 3D DP to find the minimum distance to type a word using two fingers.
   * dfs(f1, f2, pos) = min(
   *    dfs(f1, ch, pos+1)+dist(f2, ch), 
   *    dfs(ch, f2, pos+1)+dist(f1, ch)
   * ) for ch in [0, 26]
   * private int dfs(int finger1, int finger2, int pos){
      if(pos == chs.length) return 0;
      if(dp[finger1][finger2][pos] != -1) return dp[finger1][finger2][pos];
      int charIdx = chs[pos]-'A'+1;
      // System.out.println("::"+pos+":"+charIdx+":"+dist(finger1-1, chs[pos])+":"+dist(finger2-1, chs[pos]));
      return dp[finger1][finger2][pos] = Math.min(
        dfs(charIdx, finger2, pos+1)+(finger1>0?dist(finger1-1, chs[pos]):0),
        dfs(finger1, charIdx, pos+1)+(finger2>0?dist(finger2-1, chs[pos]):0)
      );
    }
   * TC: O(n^3) SC: O(n^3) or TC = MC = O(n*26^(m-1)) for m fingers
   * Optimized DP:
   * At pos, we know for `pos-1` we used atleast one finger. 
   * So, we can use the either use the other finger to type the next character or maybe not
   * dfs(other, pos) = min(
   *   dfs(other, pos+1)+dist(currFinger, ch), // using same
   *   dfs(currFinger, pos+1)+(other<26?dist(other, ch):0)
   * ) for ch in [0, 26]
   * TC: O(n*26^(m-1)) SC: O(n*26^(m-1)))
   * #dynamic-programming #dp-optimization
   */

  char []chs;
  int [][]dp;
  public int minimumDistance(String word) {
    int res = 0;
    chs = word.toCharArray();
    dp = new int[27][chs.length];
    for(int []vv: dp){
      Arrays.fill(vv, -1);
    }
    System.out.println(dist(5,25));

    res = dfs(26, 1);
    return res;
  }

  private int dfs(int other, int pos){
    if(pos >= chs.length) return 0;
    if(dp[other][pos] != -1) return dp[other][pos];
    int charIdx = chs[pos]-'A';
    int currFinger = chs[pos-1]-'A';
    // System.out.println("::"+(char)('A'+other)+":"+pos+":"+charIdx+":"+currFinger+":"+dist(currFinger, charIdx)+":"+dist(other, charIdx));
    return dp[other][pos] = Math.min(
      dfs(other, pos+1)+dist(currFinger, charIdx), // using same
      dfs(currFinger, pos+1)+(other<26?dist(other, charIdx):0)
    );
  }

  private int dist(int finger, int ch){
    return Math.abs(finger/6-ch/6)+Math.abs(finger%6-ch%6);
  }
}