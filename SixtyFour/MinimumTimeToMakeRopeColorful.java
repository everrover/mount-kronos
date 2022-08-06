package SixtyFour;

public class MinimumTimeToMakeRopeColorful {
    /**
     * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
     * Picked two consecutive colors and popped the one with largest pop time.
     * If more than two consecutive ones are observed we delete all except smallest one.
     * Hence, keeping track of largest within the same time array. Could have used commented code block otherwise.
     */
    public int minCost(String colors, int[] time) {
        int result =0;
        for(int i =1;i<colors.length();i++){
            if(colors.charAt(i)==colors.charAt(i-1)){
                result +=Math.min(time[i],time[i-1]);
                time[i]=Math.max(time[i],time[i-1]);
            }

//            if(colors.charAt(i)==colors.charAt(i-1)){
//                result +=Math.min(time[i],time[i-1]);
//                prev=Math.max(time[i],time[i-1]);
//            }else{
//                prev = time[i];
//            }
        }
        return result ;
    }
}
