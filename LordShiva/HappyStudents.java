package LordShiva;

import java.util.Collections;
import java.util.List;

public class HappyStudents {
  /**
   * https://leetcode.com/problems/happy-students/
   * 
   * In the group we can either pick no students, all students, or some of them. 
   * If larger happy-quo student is picked in the group the smaller happy-quo students can definately be picked in the group.
   * Similarly with not being picked. That's where sorting helped.
   * 
   * Essentially the constraint of finding the group is
   * fn(i) = i+1 > nums[i] && i+1 < nums.get(n-1) && i+1 < nums[i+1] ? 1 : 0, for i in [0,n-1)
   * 
   * #array #greedy #sorting #contest
   * TC: O(n*log(n)) SC: O(1)
   */

  public int countWays(List<Integer> nums) {
    int res = 0, n = nums.size();
    Collections.sort(nums);
    for(int i=0; i<n-1; i++){
      if(i+1 > nums.get(i) && i+1 < nums.get(n-1) && i+1<nums.get(i+1)) res++;
    }
    if(nums.get(n-1) < n) res++; // all not in set
    if(nums.get(0) != 0 && nums.get(n-1) < n) res++; // all in set
    return res;
  }
}
