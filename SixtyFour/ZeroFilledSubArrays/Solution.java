package SixtyFour.ZeroFilledSubArrays;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/design-a-number-container-system/
 * Find contiguous ranges of zeros and count their length. For each provided range with count the number f sub-arrays is C(n+1, 2).
 * This result is (n+1)*n/2 which is to be added to actual answer. Works in `O(n)` time.
 */
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        List<Long> counts = new LinkedList<>();
        int i=0;
        while(i<nums.length){
            long cnt = 0;
            while(i<nums.length&&nums[i++] == 0){
                cnt++;
            }
            if(cnt != 0) counts.add(cnt);
        }
        long res = 0L;
        for(long cnt: counts){
            res += ((cnt+1)*(cnt)/2);
        }
        return res;
    }
}
