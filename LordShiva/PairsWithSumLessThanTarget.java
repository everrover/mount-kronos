package LordShiva;

import java.util.List;

public class PairsWithSumLessThanTarget {
  /**
   * https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
   * 
   * Could have used sorting and two-pointer approach. But this is a easy contest problem, so just brute-forced it.
   * 
   * TC: O(n^2) SC: O(1)
   * #easy #brute-force #contest #sorting #two-pointer
   */

  public int countPairs(List<Integer> nums, int target) {
    int res = 0;
    for(int i=0; i<nums.size(); i++){
      for(int j=i+1; j<nums.size(); j++){
        if(nums.get(i) + nums.get(j) < target) res++;
      }
    }
    return res;
  }
}
