package SuperMario;

import java.util.Arrays;

public class FindKthSmallestPairDist {

  /**
   * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
   * Used a PQ based nth-order-statistic to find the kth smallest pair distance. 
   * TC: O(n^2) SC: O(1)
   * 
   * Optimized sol:
   * Used binary search on the solution space to find the kth smallest pair distance.
   * For max possible difference, we can find the element count which are less than or equal to that difference(`mid`).
   * If the count is greater than or equal to k, then we can reduce the difference, else we can increase the difference.
   * 
   * Earlier thought of using n*(n+1)/2 to find 
   * the count of elements less than or equal to `mid`, but it found an alternative way.
   * 
   * TC: O(nlog(max(arr[i]))) SC: O(1)
   * #binary-search #binary-search-on-solution-space 
   */

  public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    int l = 0, r = nums[nums.length-1]-nums[0], res=Integer.MAX_VALUE;
    while(l<r){
      int mid = (l+r)/2;
      if(findElemCount(nums, mid)>=k){
        r = mid;
      }else l = mid+1;
    }
    return l;
  }

  private int findElemCount(int []nums, int mid){
    int i=0, j = 0, count = 0;
    while(i<nums.length){
      while(j<nums.length && nums[j]-nums[i]<=mid) j++;
      count += j-i-1;
      i++;
    }
    // count += ((j-i+1)*(j-i+2)/2); // not needed after replacing j<nums.length with i<nums.length on line 38
    return count;
  }
}
