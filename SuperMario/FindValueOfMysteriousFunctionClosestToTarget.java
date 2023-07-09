package SuperMario;

import java.util.HashSet;
import java.util.Set;

public class FindValueOfMysteriousFunctionClosestToTarget {
  /**
   * https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/
   * 
   * Got optimised sol from : https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/solutions/743741/detailed-general-idea-solution-for-such-problems-and-or-gcd-in-o-n-log-max-arr-i/
   * Details:: ![](1521_fig1.png)
   * Got a big reminder around the mathematical process
   * To list all obsrvations: and proceed after that 
   * TC: O(nlog(max(arr[i]))) SC: O(n)
   */
  public int closestToTarget(int[] arr, int target) {
    int res = Integer.MAX_VALUE;
    Set<Integer> tmp, set = new HashSet<>();
    for(int i=0; i<arr.length; i++){
      tmp = new HashSet<>();
      tmp.add(arr[i]);
      for(int n: set) tmp.add(n&arr[i]);
      for(int n: tmp) res = Math.min(res, Math.abs(target - n));
      set=tmp;
    }
    return res;
  }
  public int closestToTargetOn_2(int[] arr, int target) {
    int res = Integer.MAX_VALUE;
    for(int i=0; i<arr.length; i++){
      int ans = ~0;
      for(int j=i; j<arr.length; j++){
        ans = ans&arr[j];
        res = Math.min(res, Math.abs(target - ans));
        if (ans <= target) break; // early termination
      }
    }
    return res;
  }
  public int closestToTargetOn_3(int[] arr, int target) {
    int res = Integer.MAX_VALUE;
    for(int i=0; i<arr.length; i++){
      for(int j=i; j<arr.length; j++){
        res = Math.min(res, Math.abs(target - func(arr, i, j)));
      }
    }
    return res;
  }
  private int func(int []arr, int l, int r) {
    if (r < l) return -1000000000;
    int ans = arr[l];
    for (int i = 1 + 1; i <= r; i++) ans = ans & arr[i];
    return ans;
  }
}
