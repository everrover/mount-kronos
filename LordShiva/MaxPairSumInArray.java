package LordShiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPairSumInArray {
  /**
   * https://leetcode.com/problems/max-pair-sum-in-an-array/
   * 
   * #array #hashing
   */
  public int maxSum(int[] nums) {
    Map<Integer, List<Integer>> h= new HashMap<>();
    for(int i=0; i<10; i++){
      h.put(i, new ArrayList<>());
    }
    int res=-1;
    for(int num: nums){
      int t = num, md = 0;
      while(t>0){
        md = Math.max(md, t%10);
        t/=10;
      }
      h.get(md).add(num);
    }
    for(int i=0; i<10; i++){
      int sz = h.get(i).size();
      if(sz<=1) continue;
      Collections.sort(h.get(i));
      res = Math.max(res, h.get(i).get(sz-1)+h.get(i).get(sz-2));
    }
    return res;
  }
}
