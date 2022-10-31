package SixtyFour;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

  // https://leetcode.com/problems/subarray-sum-equals-k/submissions/
  public int subarraySum(int[] nums, int k) {
    int ans = 0, j = 0;
    int prefix = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for(int i=0; i<nums.length; i++){
      prefix = prefix+nums[i];
      if(map.containsKey(prefix-k)){
        ans += map.get(prefix-k);
      }
      map.put(prefix, map.getOrDefault(prefix, 0)+1);
    }
    return ans;
  }

  public static void main(String[] args) {
    SubarraySumEqualsK subarraySumEqualsK = new SubarraySumEqualsK();
    System.out.println(subarraySumEqualsK.subarraySum(new int[]{1, 0, -1, 2, -1, 3, 4}, 2));
    System.out.println(subarraySumEqualsK.subarraySum(new int[]{1, 1, 1}, 2));
  }
}
