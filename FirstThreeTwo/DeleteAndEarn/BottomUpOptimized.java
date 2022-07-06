package FirstThreeTwo.DeleteAndEarn;

public class BottomUpOptimized {
    public int deleteAndEarn(int[] nums) {
        int count[] = new int[10001];
        int dp[] = new int[10001];
        for(int num: nums) count[num]++;
        int prevSum=0, currSum=count[1], tmp;
        for(int i=2; i<=10000; i++){
            tmp = currSum;
            currSum = Integer.max(prevSum+count[i]*i, currSum);
            prevSum = tmp;
        }
        return Integer.max(prevSum, currSum);
    }
}