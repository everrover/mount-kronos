package SixtyFour.LargestRectangleInHistogram;


import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Explanation remaining!!!!
 */
public class Solution {
  public int largestRectangleArea(int[] heights) {
    if(heights == null) return 0;
    int max = 0, len = heights.length;
    Stack<Integer> s = new Stack<>();
    int i = 0;
    while(i <= len){
      int h = i == len ? 0: heights[i];
      if(s.isEmpty() || h >= heights[s.peek()]){
        s.push(i++);
      }else{
        int tp = s.pop();
        max = Math.max(max, heights[tp] * (s.isEmpty()? i : i - 1 - s.peek()));
      }
    }
    return max;
  }

  public int largestRectangleAreaConcise(int[] heights) {
    if(heights == null) return 0;
    int res = 0;
    Stack<Integer> s = new Stack<>();
    s.push(-1);
    for(int i=0; i<=heights.length; i++){
      int currHt = i==heights.length?0:heights[i];
      while(s.peek()!=-1 && currHt<heights[s.peek()]){
        int top = s.pop();
        res = Math.max(res, heights[top]*(i-s.peek()-1));
      }
      s.push(i);
    }
    return res;
  }

  public static void main(String[] args) {

  }
}
