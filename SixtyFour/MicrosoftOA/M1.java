package SixtyFour.MicrosoftOA;

import java.util.HashMap;
import java.util.Map;

public class M1 {
    int contiguousArraysWithAM(int a[],int S){
        int n = a.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int runningSum = 0;
        long res=0;
        for(int i=0;i<n;i++){
            runningSum += a[i];
            final int key = runningSum - (S * (i + 1));
            res += map.getOrDefault(key,0);
            if(res > 1000000000) return 1000000000;
            map.put(key,map.getOrDefault(key,0)+1);
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(new M1().contiguousArraysWithAM(new int[]{2,1,3},2));
        System.out.println(new M1().contiguousArraysWithAM(new int[]{2,1,3},3));
        System.out.println(new M1().contiguousArraysWithAM(new int[]{0,4,3,-1},2));
        System.out.println(new M1().contiguousArraysWithAM(new int[]{2,1,4},3));
    }
}
