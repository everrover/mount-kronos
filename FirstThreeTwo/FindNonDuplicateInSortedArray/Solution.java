package FirstThreeTwo.FindNonDuplicateInSortedArray;

public class Solution {
    public int binarySearch(int []arr){
        int res = -1;
        int l=0, r=arr.length-1, mid;
        while(l<=r){
            mid = (l+r)/2;
            if(mid<(arr.length-1) && arr[mid] == arr[mid+1]){
                if(mid%2 == 1){
                    r = mid-1;
                }else {
                    l = mid;
                }
            }else if(mid > 0 && arr[mid] == arr[mid-1]){
                if(mid%2 == 1){
                    l = mid+1;
                }else {
                    r = mid;
                }
            }else{
                res = arr[mid];
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().binarySearch(new int[]{0,1,1,3,3,6,6,9,9}));
    }
}
