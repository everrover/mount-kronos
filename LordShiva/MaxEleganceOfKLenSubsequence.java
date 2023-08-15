package LordShiva;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MaxEleganceOfKLenSubsequence {
  /**
   * https://leetcode.com/problems/maximum-elegance-of-a-k-length-subsequence/
   * 
   * 
   */

   public long findMaximumElegance(int[][] items, int k) {
    Arrays.sort(items, (a,b)->(b[0]-a[0]));
    long res = 0, curr = 0;
    List<Integer> dup = new LinkedList<>(); // can use PQ as well but not needed
    Set<Integer> seen = new HashSet<>();
    for(int i=0; i<items.length; i++){
      if(i<k){
        curr += items[i][0];
        if(seen.contains(items[i][1])){
          dup.add(items[i][0]);
        }
      }else if(!seen.contains(items[i][1])){
        if(dup.isEmpty()){
          break;
        }
        curr += items[i][0] - dup.remove(dup.size()-1);
      }
      seen.add(items[i][1]);
      res = Math.max(curr+1L*seen.size()*seen.size(), res);
    }
    return res;
  }
}
