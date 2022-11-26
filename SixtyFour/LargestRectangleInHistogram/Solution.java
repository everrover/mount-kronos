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
  // try finding leftmost and rightmost index for each `curr` element where `curr` is minimum across [left...right]
  // approach never works in linear complexity. STree or Fenwick trees or sth ~ or unknown to me are needed. Example: [2,1,2], [4,2,0,3,2,4,3,4]
  public int largestRectangleAreaLeftRight(int[] heights) {
    if(heights == null || heights.length == 0) return 0;
    int res = 0;
    int []left = new int[heights.length];
    int []right = new int[heights.length]; right[heights.length-1]=heights.length-1;
    for (int i = 1; i < heights.length; i++) {
      left[i] = heights[i]>heights[left[i-1]]?i:left[i-1];
    }
    for (int i =heights.length-2; i >= 0; i--) {
      right[i] = heights[i]>heights[right[i+1]]?i:right[i+1];
    }
    for(int i=0; i<heights.length; i++){
      res = Math.max(res, heights[i]*(right[i]-left[i]+1));
    }
    return res;
  }

  public static void main(String[] args) {

  }
}
