package NinetySix.FirstDayWhereYouHaveBeenInAllRooms;

/**
 * https://leetcode.com/problems/first-day-where-you-have-been-in-all-the-rooms/
 * nextVisit[0] = 0, always
 */
public class FirstDayWhereYouHaveBeenInAllRooms {
  private static final long MOD=1000000007;
  public int firstDayBeenInAllRooms(int[] nextVisit) {
    final int n = nextVisit.length;
    int []dp = new int[n];
    dp[0] = 0;
    for(int i=1; i<n; i++){
      dp[i] = (int)((MOD+2+dp[i-1]*2-dp[nextVisit[i-1]])%MOD);
    }
    return dp[n-1];
  }
}
