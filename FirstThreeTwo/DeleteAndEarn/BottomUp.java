package FirstThreeTwo.DeleteAndEarn;

public class BottomUp {
    public int deleteAndEarn(int[] nums) {
        int count[] = new int[10001];
        int dp[] = new int[10001];
        for(int num: nums) count[num]++;
        int prevSum=0, currSum=0, prev=-1;
        dp[1] = count[1];
        for(int i=2; i<=10000; i++){
            dp[i] = Integer.max(dp[i-2]+count[i]*i, dp[i-1]);
        }
        return Integer.max(dp[10000], dp[9999]);
    }
}