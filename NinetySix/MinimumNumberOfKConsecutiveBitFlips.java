package NinetySix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 * WAS NOT ABLE TO DO BY MYSELF :: Got hints from :: https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/discuss/239284/C%2B%2B-greedy-stack-and-O(1)-memory
 * Essentially started moving from left-to-right flipping all 0s to 1s. That resulted in OPTIMAL answer, unlike what I
 * thought of before where some arbitrary seq of flips had to be executed...
 *
 * Most important thing to remember is that DSA ques are meant to be solved within the hour. Seq of exn would always
 * be following some pattern.
 * */
public class MinimumNumberOfKConsecutiveBitFlips {
  // MC:: O(n)
  public int minKBitFlips(int[] nums, int k) {
    int res = 0;
    Queue<Integer> q = new LinkedList<>(); // stores flip indices and
    for(int r=0; r<nums.length; r++){
      if(nums[r] != (((q.size()%2)==1)?0:1)){
        q.offer(r);
        res++;
      }
      if(!q.isEmpty() && q.peek() <= (r-k+1)) q.poll();
    }
    return q.isEmpty()?res:-1;
  }
  // MC:: O(1)
  public int minKBitFlipsConstant(int[] nums, int k) {
    int res = 0, flips = 0;
    for(int r=0; r<nums.length; r++){
      if(nums[r] == (flips%2)){
        if(r+k > nums.length) return -1;
        flips++;
        res++;
        nums[r]-=2;
      }
      if(r>=k-1 && nums[r-k+1] < 0) {nums[r-k+1]+=2; flips--;}
    }
    return res;
  }
}
