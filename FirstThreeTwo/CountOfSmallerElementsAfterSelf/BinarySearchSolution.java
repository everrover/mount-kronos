package FirstThreeTwo.CountOfSmallerElementsAfterSelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchSolution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> sortedArr = new ArrayList<>();
        for(int n: nums) sortedArr.add(n);
        Collections.sort(sortedArr);
        int i=0;
        for(int num: nums){
            int idx = binarySearch(sortedArr, num);
            // int idx = Collections.binarySearch(sortedArr, num); // it'll return a arbitary element with specific value, we need specifically the last occuring element
            sortedArr.remove(idx);
            ans.add(idx);
        }
        return ans;
    }

    private int binarySearch(List<Integer> arr, int num){
        int l = 0, r = arr.size()-1, mid=0;
        while(l<=r){
            mid = (l+r)/2;
            int ele = arr.get(mid);
            if(num>ele){
                l = mid+1;
            }else if(num<ele){
                r = mid-1;
            }else if(mid!=0 && arr.get(mid-1)<num){
                return mid;
            }else{
                r = mid-1;
            }
        }
        return mid;
    }

    private int binarySearchAnother(List<Integer> arr, int num){
        int l = 0, r = arr.size()-1, mid=0;
        while(l<=r){
            mid = (l+r)/2;
            int ele = arr.get(mid);
            if(num>ele){
                l = mid+1;
                // }else if(num<ele){
                //     r = mid-1;
                // }else if(mid!=0 && arr.get(mid-1)<num){
                //     return mid;
            }else{
                r = mid-1;
            }
        }
        if(arr.get(l) == num) return l;
        return mid;
    }
}