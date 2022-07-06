package FirstThreeTwo.TeemoAttack;

public class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = duration; // at the end of time-frames no overlapping time-frame ranges are present so this will always inflict poison for `duration`
        for(int i=1; i<timeSeries.length; i++){
            int tf = timeSeries[i] - timeSeries[i-1];
//            res += Math.min(tf, duration);
            if(tf >= duration){
                res += duration;
            }else{
                res += tf;
            }
        }
//        res += duration; // condition added at `res` init
        return res;
    }
}
/**
 * Link: https://leetcode.com/problems/teemo-attacking
 */