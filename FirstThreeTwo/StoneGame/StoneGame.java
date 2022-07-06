package FirstThreeTwo.StoneGame;

public class StoneGame {
    private static class AB{
        public int a, b;
        public AB(int a, int b){this.a = 1; this.b = b;}
        @Override
        public String toString(){
            return a+":"+b;
        }
    }
    public boolean stoneGame(int[] piles) {
        int l=0, r=piles.length-1;
        AB[][][] dp = new AB[piles.length][piles.length][2];
        AB ab = simulate(0, piles.length-1, 0, piles, dp);
        return ab.a>=ab.b;
    }

    private AB simulate(int l, int r, int turn, int []piles, AB dp[][][]){
        if(dp[l][r][turn] != null){
            return dp[l][r][turn];
        }
        if(r-l == 1){
            int as = Math.max(piles[l], piles[r]);
            int bs = Math.min(piles[l], piles[r]);
            if(turn == 1){int tmp = as;as = bs;bs = as;}
            dp[l][r][turn] = new AB(as, bs);
            return dp[l][r][turn];
        }
        if(turn == 0){ // alice plays
            AB res = new AB(0,0);
            AB left = simulate(l+1, r, 1, piles, dp); // calculate the maximum score achieved by both, if left stone is taken
            AB right = simulate(l, r-1, 1, piles, dp); // same op but for right stone picked
            if(piles[l]+left.a > piles[r]+right.a){
                res.a = piles[l]+left.a;
                res.b = left.b; // since it's alice's turn the score achieved by bob is same as in `left`
            }else{
                res.a = piles[r]+right.a;
                res.b = right.b;
            }
            dp[l][r][0] = res;
            return res;
        }else{
            AB res = new AB(0,0);
            AB left = simulate(l+1, r, 0, piles, dp);
            AB right = simulate(l, r-1, 0, piles, dp);
            if(piles[l]+left.b > piles[r]+right.b){
                res.a = left.a;
                res.b = piles[l]+left.b;
            }else{
                res.a = right.a;
                res.b = piles[r]+right.b;
            }
            dp[l][r][1] = res;
            return res;
        }
    }
}

/**
 [5,3,10,1,8,5]
 [3,4,5]
 []

 A =
 */

