package MinimumCostToCutStick;

import java.util.Arrays;

public class HuffmansAlgo {
    public int minCost(int n, int[] cuts) {
        int []rods = new int[cuts.length+1];
        Arrays.sort(cuts);
        int res = 0;
        rods[0] = cuts[0];
        for(int i=1; i<cuts.length; i++){
            rods[i] = cuts[i]-cuts[i-1];
        }
        rods[cuts.length] = n - cuts[cuts.length-1];

        int rodNum = rods.length;
        while(rodNum-->1){
            int minCost = Integer.MAX_VALUE, idx = 0;
            for(int i=0; i<rodNum; i++){
                if((rods[i]+rods[i+1])<minCost){
                    minCost = rods[i]+rods[i+1];
                    idx = i;
                }
            }

            res += minCost;
            rods[idx] = minCost;
            for(int i=idx+1; i<rodNum; i++){
                rods[i] = rods[i+1];
            }
        }
        return res;
    }
}
