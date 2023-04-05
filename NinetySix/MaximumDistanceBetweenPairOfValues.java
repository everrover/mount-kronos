package NinetySix;

/**
 * https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
 * Sol with binary search: TC: O(n*log(n)) MC: O(1)
 * - Both arrays are sorted and hence we could traverse array 1 and for each element could have found the pivot element
 * - And then compute the max value of `j-i`
 * Sol with two-pointers: TC: O(2*n) MC:O(1)
 * - For each `l` I traversed `r` from L->R until the constraints aren't violated
 *  - All elements after `l+1` after `r`'s traversal would satisfy the constraint from `r`'s previous position till `r`'s new position, but `j-i`'s value would be smaller
 *  - Hence re-traversing from `0` till `len-1` in arr2 isn't needed for each element
 * - Note: Once `r` reaches the end, the maximum possible value of `j-i` would already have been found, hence I broke there. Otherwise even without it it'd work
 */

public class MaximumDistanceBetweenPairOfValues {
  public int maxDistance(int[] nums1, int[] nums2) {
    int l = 0, r = 0, ans = 0; // Going from R->L is also possible
    while(l<nums1.length && r<nums2.length){
      while(r<nums2.length && nums2[r]>=nums1[l]) r++;
      // System.out.println(r+":"+l);
      ans = Integer.max(ans, r-l-1);
      r = Integer.max(++l, r);
    }
    return ans;
  }
}