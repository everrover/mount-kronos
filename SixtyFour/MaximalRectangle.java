package SixtyFour;

import java.util.Stack;
/*
https://leetcode.com/problems/maximal-rectangle/

Optimal but how🤔?? https://leetcode.com/submissions/detail/343146841/
 */
public class MaximalRectangle {
  public int maximalRectangle(char[][] matrix) {
    if(matrix.length == 0 || matrix[0].length == 0) return 0;
    final int m = matrix.length, n = matrix[0].length;
    int []hts = new int[n];
    for(int i=0; i<n; i++) hts[i] = matrix[0][i]=='0'?0:1;
    int res = largestRectangleArea(hts);
    for(int i=1; i<m; i++){
      for(int j=0; j<n; j++){
        hts[j] = matrix[i][j] == '0'? 0: (hts[j]+1);
      }
      res = Math.max(res, largestRectangleArea(hts));
    }
    return res;
  }

  private int largestRectangleArea(int []heights) {
    if(heights == null || heights.length == 0) return 0;
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
}
