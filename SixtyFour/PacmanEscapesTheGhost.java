package SixtyFour;

/**
 * https://leetcode.com/problems/escape-the-ghosts/
 */
public class PacmanEscapesTheGhost {
  private int manhattenDistance(int x1, int y1, int x2, int y2){
    return Math.abs(x1-x2)+Math.abs(y1-y2);
  }
  public boolean escapeGhosts(int[][] ghosts, int[] target) {
    int pacmanDist = manhattenDistance(target[0], target[1], 0, 0);
    for(int []ghost: ghosts){
      if(manhattenDistance(target[0], target[1], ghost[0], ghost[1])<=pacmanDist) return false;
    }
    return true;
  }
}
