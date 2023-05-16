package NinetySix;

/**
 * A delivery company wants to build a new service center in a new city. The company knows the positions of all the customers in this city on a 2D-Map and wants to build the new center in a position such that the sum of the euclidean distances to all customers is minimum.
 *
 * Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the minimum sum of the euclidean distances to all customers.
 *
 * In other words, you need to choose the position of the service center [xcentre, ycentre] such that the following formula is minimized:
 * F(x,y) = sum(sqrt((x_center-x_i)^2+(x_center-x_i)^2)), 0<=i<=(n-1)
 *
 * Answers within 10^-5 of the actual value will be accepted.
 */
public class MinimumDistanceOfServiceCenter {
  public double getMinDistSum(int[][] positions) {
    double res = 0;
    double x = 0, y = 0;
    for (int i = 0; i < 100; i++) {
      double[] center = getCenter(positions, x, y);
      x = center[0];
      y = center[1];
      res = center[2];
    }
    return res;
  }
}
