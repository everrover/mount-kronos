package LordShiva;

import java.util.HashSet;
import java.util.Set;

public class MaxNumberOfIntsToChooseFromRange1 {
  /*
   * https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/
   * 
   * Greedy choice: Pick the smallest unique elements to build a set. If larger numbers are included in result, set size would shrink due to `maxSum` constraint. If 4 is picked, 1&2 could not be used which is more optimal.
   * 
   * #contest #greedy #traversal
   * TC: O(n) SC: O(banned.length)
   */

  public int maxCount(int[] banned, int n, int maxSum) {
    int res = 0;
    Set<Integer> s = new HashSet<>();
    for(int b: banned) s.add(b);
    long sum = 0;
    for(int i=1; i<=n; i++){
      if(s.contains(i)) continue;
      sum += i;
      if(sum > maxSum) break;
      res++;
    }
    return res;
  }
}
