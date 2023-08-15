package LordShiva;

import java.util.List;
import java.util.TreeSet;

public class MinAbsDiffBetweenElementsWithConstraint {
  /**
   * https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint/
   * 
   * Approaches can vary. I found that the minimum absolute difference will be between the elements 
   * which are inorder predecessor and successor of the current element. This is calculated while traversing
   * from left to right and right to left.
   * 
   * We can use other approaches as well. Like using binary search to find the closest element to the current in seen elements.
   * 
   * #array #binarysearch #binary-search-tree 
   * 
   * TC: O(nlogn) SC: O(n)
   */
  public int minAbsoluteDifference(List<Integer> nums, int x) {
    int n = nums.size();
    int res = Integer.MAX_VALUE;
    TreeSet<Integer> ts = new TreeSet<>();
    for(int i=x,j=0; i<n; i++,j++){
      int y = nums.get(i), z = nums.get(j);
      ts.add(z);
      Integer low = ts.lower(y);
      if(low!=null) res = Math.min(Math.abs(y-low), res);
      Integer high = ts.higher(y);
      if(high!=null) res = Math.min(Math.abs(high-y), res);
      if(ts.contains(y)) return 0;
    }
    ts.clear();
    for(int i=n-x-1,j=n-1; i>=0; i--,j--){
      int y = nums.get(i), z = nums.get(j);
      ts.add(z);
      Integer low = ts.lower(y);
      if(low!=null) res = Math.min(Math.abs(y-low), res);
      Integer high = ts.higher(y);
      // System.out.println(y+":"+high+":"+low+":"+ts);
      if(high!=null) res = Math.min(Math.abs(high-y), res);
      if(ts.contains(y)) return 0;
    }
    return res;
  }
}
