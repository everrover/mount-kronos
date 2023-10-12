package LordShiva;

import java.util.ArrayList;
import java.util.List;

public class SeparateDigitsInArr {
/**
 * https://leetcode.com/problems/separate-the-digits-in-an-array/
 * 
 * Simply used utils in java.
 * 
 * #easy #traversal
 * TC: O(m*n)
 */

  public int[] separateDigits(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for(int num: nums){
      char []str = (""+num).toCharArray();
      for(char s: str){
        res.add(Character.getNumericValue(s));
      }
    }
    int []ans = new int[res.size()];
    for(int i=0; i<res.size(); i++) ans[i] = res.get(i);
    return ans;
  }
}
