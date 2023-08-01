package LordShiva;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrangePrinterII {
  /**
   * https://leetcode.com/problems/strange-printer-ii/
   * 
   * First I never read the constraint 2. And started building accordingly. Resolved even.
   * 
   * The constraint made it too easy...
   * Since a color once printed can't be printed again, we repeatedly find the color which can be printed in rectangular order.
   * And put it in a set. If we can't find any color to print, we return false.
   * TC: O(m*n*max(m,n)) SC: O(m*n)
   * 
   * #topological-sort #graph #made-easy-with-a-constraint
   * 
   */


  public boolean isPrintable(int[][] targetGrid) {
    int m = targetGrid.length, n = targetGrid[0].length;
    Map<Integer, int[]> coords = new HashMap<Integer, int[]>();
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        coords.putIfAbsent(targetGrid[i][j], new int[]{m,n,-1,-1});
        int []co = coords.get(targetGrid[i][j]);
        co[0] = Math.min(co[0], i);
        co[1] = Math.min(co[1], j);
        co[2] = Math.max(co[2], i);
        co[3] = Math.max(co[3], j);
      }
    }
    int res = 0;
    Set<Integer> colors = new HashSet<>(coords.keySet());
    while(!colors.isEmpty()){
      Set<Integer> news = new HashSet<>();
      for(int color: colors){
        if(!isToPrint(targetGrid, coords.get(color), color)){
          news.add(color);
        }
      }
      // System.out.println(colors+":"+news);
      if(news.size() == colors.size()) return false;
      colors =  news;
    }
    return true;
  }

  private boolean isToPrint(int [][]t, int[] co, int col){
    for(int i=co[0]; i<=co[2]; i++){
      for(int j=co[1]; j<=co[3]; j++){
        if(t[i][j] != col && t[i][j]>0) return false;
      }
    }
    for(int i=co[0]; i<=co[2]; i++){
      for(int j=co[1]; j<=co[3]; j++){
        t[i][j] = 0;
      }
    }
    return true;
  }
}
