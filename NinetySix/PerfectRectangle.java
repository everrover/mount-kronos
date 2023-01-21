package NinetySix;


import java.util.HashSet;
import java.util.Set;

/**
 * 391. Perfect Rectangle
 * https://leetcode.com/problems/perfect-rectangle/
 * Approach 1::
 * - The area made via rectangle's extremes(calculated via iterating over all points) should equal the area formed via all rectangles
 * - Any slots or overlapping rectangles in overall rectangle will cause number of non-overlapping points in component rectangles to exceed 4.
 *
 * Approach 2:: Line sweep algorithm
 * -
 */
public class PerfectRectangle {
  // approach :: smart one
  public boolean isRectangleCover(int[][] rectangles) {
    long areaFullRectangle = 0L;
    long areaAllRectangle = 0L;
    long lX = Integer.MAX_VALUE, lY = Integer.MAX_VALUE, rX = Integer.MIN_VALUE, rY = Integer.MIN_VALUE;
    Set<String> isPointPresent = new HashSet<>();
    for(int []rectangle: rectangles){
      String a = rectangle[0]+":"+rectangle[1];
      String b = rectangle[0]+":"+rectangle[3];
      String c = rectangle[2]+":"+rectangle[3];
      String d = rectangle[2]+":"+rectangle[1];
      if(isPointPresent.contains(a)) isPointPresent.remove(a); else isPointPresent.add(a);
      if(isPointPresent.contains(b)) isPointPresent.remove(b); else isPointPresent.add(b);
      if(isPointPresent.contains(c)) isPointPresent.remove(c); else isPointPresent.add(c);
      if(isPointPresent.contains(d)) isPointPresent.remove(d); else isPointPresent.add(d);
      areaAllRectangle += (rectangle[3]-rectangle[1])*(rectangle[2]-rectangle[0]);
      lX = Math.min(lX, rectangle[0]);
      lY = Math.min(lY, rectangle[1]);
      rX = Math.max(rX, rectangle[2]);
      rY = Math.max(rY, rectangle[3]);
    }
    areaFullRectangle = (rX - lX)*(rY - lY);
    return isPointPresent.contains(lX+":"+lY) && isPointPresent.contains(lX+":"+rY) && isPointPresent.contains(rX+":"+lY) && isPointPresent.contains(rX+":"+rY) &&  isPointPresent.size()==4 && areaAllRectangle == areaFullRectangle;
  }
  public static void main(String[] args) {

  }
}
