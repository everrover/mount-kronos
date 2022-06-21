package ReplaceElementsInAnArray;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }
        // for(int elem[]: elems){System.out.println(elem[0]+":"+elem[1]);}
        for(int []op: operations){
            int idx = map.get(op[0]);
            // remove elem
            map.remove(op[0]);
            map.put(op[1], idx);
//            nums[idx] = op[1];
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            nums[entry.getValue()] = entry.getKey();
        }
        return nums;
    }
}