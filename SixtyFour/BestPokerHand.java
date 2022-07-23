package SixtyFour;

public class BestPokerHand {
    /**
     * https://leetcode.com/problems/best-poker-hand/
     * Nothing to do!!!
     */
    public String bestHand(int[] ranks, char[] suits) {
        boolean flush = true;
        for (int i = 1; i < suits.length; i++) {
            if (suits[i] != suits[i - 1]) {
                flush = false;
                break;
            }
        }
        if (flush) return "Flush";

        int cnts[] = new int[14];
        for (int rank : ranks) cnts[rank]++;
        boolean isTwo = false;
        for (int cnt : cnts) {
            System.out.print(cnt + ":");
            if (cnt >= 3) return "Three of a Kind";
            else if (cnt >= 2) {
                return "Pair";
            }
        }
        System.out.println();
        return "High Card";
    }
}
