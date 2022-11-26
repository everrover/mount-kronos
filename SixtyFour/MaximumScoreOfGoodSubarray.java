package SixtyFour;

import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-score-of-a-good-subarray/
 */
public class MaximumScoreOfGoodSubarray {
  public int maximumScore(int[] nums, int k) {
    if(nums == null) return 0;
    int res = nums[k];
    int i, j, min;
    i = j = k; min = nums[k];
    while(i>0 && j<(nums.length-1)){
      if(nums[i-1] > nums[j+1]){
        min = Math.min(nums[--i], min);
        res = Math.max(res, min*(j-i+1));
      }else{
        min = Math.min(nums[++j], min);
        res = Math.max(res, min*(j-i+1));
      }
    }
    while(i>0){
      min = Math.min(nums[--i], min);
      res = Math.max(res, min*(j-i+1));
    }
    while(j<(nums.length-1)){
      min = Math.min(nums[++j], min);
      res = Math.max(res, min*(j-i+1));
    }
    return res;
  }

  public int maximumScoreUnoptimized(int[] nums, int k) {
    if(nums == null) return 0;
    int res = 0;
    Stack<Integer> s = new Stack<>();
    s.push(-1);
    for(int i=0; i<=nums.length; i++){
      int currHt = i==nums.length?0:nums[i];
      while(s.peek()!=-1 && currHt<nums[s.peek()]){
        int top = s.pop();
        if(i>k && s.peek()<k){
          res = Math.max(res, nums[top]*(i-s.peek()-1));
        }
      }
      s.push(i);
    }
    return res;
  }
}
