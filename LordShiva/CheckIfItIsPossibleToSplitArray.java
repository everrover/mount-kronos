package LordShiva;

import java.util.Arrays;
import java.util.List;

public class CheckIfItIsPossibleToSplitArray {
  /**
   * https://leetcode.com/problems/check-if-it-is-possible-to-split-array/description/
   * 
   * Earlier used DP while optimizing for a very blunt method. But later realized that we can use greedy approach.
   * 
   * TC: O(n^2) SC: O(n^2)
   * For the concise solution, TC: O(n) SC: O(1)
   * 
   * #greedy #array #short-and-concise
   */

  public boolean canSplitArray(List<Integer> nums, int m) {
    int []prefix = new int[nums.size()+1];
    for(int i=1; i<=nums.size(); i++){
      prefix[i] = prefix[i-1]+nums.get(i-1);
    }
    if(nums.size()<=2) return true;
    int [][]dp = new int[nums.size()][nums.size()]; for(int []d: dp) Arrays.fill(d, -1);
    return split(0, nums.size()-1, prefix, m, dp)==1;
  }

  private int split(int idx, int jdx, int []prefix, final int m, int [][]dp){
    if(jdx==idx) return dp[idx][jdx] = 1;
    else if(prefix[jdx+1]-prefix[idx]<m) return dp[idx][jdx] = 0;
    if(dp[idx][jdx] != -1) return dp[idx][jdx];
    for(int i=idx; i<jdx; i++){
      if((split(idx, i, prefix, m, dp)==1&&split(i+1, jdx, prefix, m, dp)==1)) return dp[idx][jdx] = 1;
    }
    // System.out.println(idx+":"+jdx+"::"+res);
    return dp[idx][jdx] = 0;
  }

  public boolean canSplitArrayConcise(List<Integer> nums, int m) {
    if(nums.size() <= 2) return true;
    for(int i = 1; i < nums.size(); i++){
        if(nums.get(i) + nums.get(i-1) >= m) return true; // if any two consecutive elements have sum>=m (lets say for `idx` and `jdx`), we can pick off elements in sub- arr[0...idx] and arr[jdx...n-1] from extreme ends
    }
    return false;
  }
}
