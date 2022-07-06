package FirstThreeTwo.SumOfAbsoluteDiffInSortedArrays;

public class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int res[] =  new int[nums.length];
        int prefixSum[] = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            prefixSum[i] = nums[i]+prefixSum[i-1];
        }
        res[0] = (prefixSum[nums.length-1]-prefixSum[0])-((nums.length-1)*nums[0]);
        for(int i=1; i<nums.length; i++){
            res[i] = (prefixSum[nums.length-1]-prefixSum[i])-((nums.length-i-1)*nums[i]); // right sum
            // System.out.println(res[i]+":");
            res[i] += (i*nums[i])-prefixSum[i-1];
        }
        return res;
    }
}
