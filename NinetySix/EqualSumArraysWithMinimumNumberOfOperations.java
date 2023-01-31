package NinetySix;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/
 * Some other sols::
 * - https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/discuss/1086142/C%2B%2BJava-O(n)
 * Steps::
 * - calculate the sum of both arrays
 * - we perform the two operations until sum1 == sum2
   * - if sum1>sum2: either increase sum2(by increasing smallest element in arr2) or decrease sum1(by decreasing largest element in arr1)
   * - else: either decrease `sum2`(by decreasing largest element in arr2) or increase `sum1`(by increasing smallest element in arr1)
 * - two important ceveats here
   * - if all elements in arr1 are `1`(minimum) and `6`(maximum) in arr2, and sum(arr1)>sum(arr2): then we return -1. since no operations now will help us reach the desired state of sum1==sum2
   * - we perform the most extreme increase or decrease to reach the desired state quickly and
     * - if abs(sum1-sum2) >= (the increase or decrease) at any step then we reach the result state with that operation
 */
public class EqualSumArraysWithMinimumNumberOfOperations {
  public int minOperations(int[] nums1, int[] nums2) {
    int sum1, sum2, small1=10, small2=10, large1=0, large2=0;
    sum1 = sum2 = 0;
    int []counts1 = new int[7];
    int []counts2 = new int[7];

    for(int num: nums1){
      counts1[num]++;
      small1 = Integer.min(small1, num);
      large1 = Integer.max(large1, num);
      sum1 += num;
    }
    for(int num: nums2){
      counts2[num]++;
      small2 = Integer.min(small2, num);
      large2 = Integer.max(large2, num);
      sum2 += num;
    }
    int res = 0;
    while(sum1 != sum2){
      if(sum1 > sum2){
        int diff1 = large1-1, diff2 = 6-small2;
        if(diff1 == 0 && diff2 == 0) return -1;
        else if(diff1 >= (sum1-sum2) || diff2 >= (sum1-sum2)) return res+1;
        else if(diff1 > diff2){
          counts1[large1]--;
          while(large1>=1 && counts1[large1]==0) large1--;
          counts1[1]++;
          sum1-=diff1;
        }else{
          counts2[small2]--;
          while(small2<=6 && counts2[small2]==0) small2++;
          counts2[6]++;
          sum2+=diff2;
        }
      }else{
        int diff1 = 6-small1, diff2 = large2-1;
        if(diff1 == 0 && diff2 == 0) return -1;
        else if(diff1 >= (sum2-sum1) || diff2 >= (sum2-sum1)) return res+1;
        else if(diff1 > diff2){
          counts1[small1]--;
          while(small1<=6 && counts1[small1]==0) small1++;
          counts1[6]++;
          sum1+=diff1;
        }else{
          counts2[large2]--;
          while(large2>=1 && counts2[large2]==0) large2--;
          // if(counts2[large2]==1) ts2.remove(large2);
          counts2[1]++;
          sum2-=diff2;
        }
      }
      res++;
    }
    return res;
  }
  public int minOperationsPart2(int[] nums1, int[] nums2) {
    int sum1, sum2;
    sum1 = sum2 = 0;
    // could have used treeset+hashmap or treemap for maintaining counts along with maintaining order of elements
    TreeSet<Integer> ts1 = new TreeSet<>();
    int []counts1 = new int[10000];
    TreeSet<Integer> ts2 = new TreeSet<>();
    int []counts2 = new int[10000];

    for(int num: nums1){
      counts1[num]++;
      ts1.add(num);
      sum1 += num;
    }
    for(int num: nums2){
      counts2[num]++;
      ts2.add(num);
      sum2 += num;
    }
    int res = 0;
    while(sum1 != sum2){
      int small1 = ts1.first(), large1 = ts1.last();
      int small2 = ts2.first(), large2 = ts2.last();
      if(sum1 > sum2){
        int diff1 = large1-1, diff2 = 6-small2;
        if(diff1 == 0 && diff2 == 0) return -1;
        else if(diff1 >= (sum1-sum2) || diff2 >= (sum1-sum2)) return res+1;
        else if(diff1 > diff2){
          if(counts1[large1]==1) ts1.remove(large1);
          counts1[large1]--;
          ts1.add(1);
          counts1[1]++;
          sum1-=diff1;
        }else{
          if(counts2[small2]==1) ts2.remove(small2);
          counts2[small2]--;
          ts2.add(6);
          counts2[6]++;
          sum2+=diff2;
        }
      }else{
        int diff1 = 6-small1, diff2 = large2-1;
        if(diff1 == 0 && diff2 == 0) return -1;
        else if(diff1 >= (sum2-sum1) || diff2 >= (sum2-sum1)) return res+1;
        else if(diff1 > diff2){
          if(counts1[small1]==1) ts1.remove(small1);
          counts1[small1]--;
          ts1.add(6);
          counts1[6]++;
          sum1+=diff1;
        }else{
          if(counts2[large2]==1) ts2.remove(large2);
          counts2[large2]--;
          ts2.add(1);
          counts2[1]++;
          sum2-=diff2;
        }
      }
      res++;
    }
    return res;
  }
}
