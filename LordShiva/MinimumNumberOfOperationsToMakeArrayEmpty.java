class Solution {
/**
https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
Performed the brute-force first, followed up with DP to check if a given number's occurance count is valid or not. 
fn(idx) = min(
          (fn(idx-3))!=-1?(fn(idx-3)+1):MAX,
          (fn(idx-2))!=-1?(fn(idx-2)+1):MAX
        ), idx>0
        = -1, idx==1
        = 1, idx==2
        = 1, idx==3
Then it hit me, all elements except for 1 have some way of 
If we represent all numbers as represented a multiple of 3, we'd have three cases
      count = 3x => x = count/3                                     :: makes count of x
      count = 3x+1 = 3(x-1)+3+1 = 3(x-1)+2+2 => x = (count-1)/3 + 1 :: makes count of x+1
      count = 3x+2 => x = (count-2)/3 + 1                           :: makes count of x+1
#dp #math
**/
  public int minOperations(int[] nums) {
    int set[] = new int[1000001];
    for(int num: nums){
      set[num]++;
    }
    int res = 0;
    for(int s: set){
      if(s == 1) {
        return -1;
      } else if (s == 2) {
        res++;
      } else {
        int rem = s % 3;
        if(rem == 1) {
          res += ((s - 1) / 3) + 1;
        } else if(rem == 2) {
          res += ((s - 2) / 3) + 1;
        } else {
          res += s / 3;
        }
      }
    }
    return res;
  }
}
