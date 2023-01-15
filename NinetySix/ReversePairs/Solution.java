package NinetySix.ReversePairs;

/**
 * https://leetcode.com/problems/reverse-pairs/
 * Could have used BST!!
 * - Complexity would shoot up tom O(n) in extreme case. eg: sorted array in either inc or dec order
 * Could have used segment tree or binary-indexed tree also!!
 * -
 * - https://leetcode.com/problems/reverse-pairs/discuss/97268/General-principles-behind-problems-similar-to-%22Reverse-Pairs%22
 */


public class Solution {
  public int reversePairs(int[] nums) {
    int []res = new int[nums.length];
    int cnt = mergeSort(0, nums.length-1, nums, res);
    return cnt;
  }
  private int mergeSort(int l, int r, int []nums, int []res){
    if(l >= r) return 0;
    int mid = (l+r)/2;
    int cnt = mergeSort(l, mid, nums, res) + mergeSort(mid+1, r, nums, res);
    // for(int i:nums) System.out.print(i+":");
    // System.out.println();
    for(int i=l, j=mid+1; i<=mid; i++){
      while(j<=r && nums[i]/2.0 > nums[j]) j++; // for finding pivot element J could have used binary search as well
      cnt += (j-mid-1);
    }
    merge(l, mid, r, nums, res);
    return cnt;
  }
  private void merge(int l, int mid, int r, int []nums, int []res){
    int p1 = l, p2 = mid+1, p = 0;
    while(p1 <= mid && p2 <= r){
      if(nums[p1] < nums[p2]) res[p++] = nums[p1++];
      else res[p++] = nums[p2++];
    }
    while(p1 <= mid) res[p++] = nums[p1++];
    while(p2 <= r) res[p++] = nums[p2++];
    for(int i=0; i<p; i++) nums[l+i] = res[i];
  }
}