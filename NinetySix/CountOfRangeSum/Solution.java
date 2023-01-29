package NinetySix.CountOfRangeSum;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 * - Merge Sort
 * - Using Fenwick tree and BST :: Not my approach, somebody else's ::
 *   - Store prefixSum[i] with index in BST
 *   - Maintain a Bin-indexed tree to track how many prefixSum[i] values have been encountered :: Tracking index of traversed elements
 *   - For each element(at index i) in array
 *     - Using BST find left = index of largest element in BST with prefix-sum <= (sum-upper-1)
 *     - right = index of largest element in BST with prefix-sum <= (sum-lower)
 *     - res += count_of_elements_in_fenwick_tree(right)-count_of_elements_in_fenwick_tree(left)+1;
 *     - mark count_of_elements_in_fenwick_tree(i) as 1
 *
 */
public class Solution {
  private int lower = 0, upper = 0;
  public int countRangeSum(int[] nums, int lower, int upper) {
    this.lower = lower; this.upper = upper;
    long []prefix = new long[nums.length+1];
    for(int i=1; i<=nums.length; i++){
      prefix[i] = prefix[i-1]+nums[i-1];
    }
    /**
     * ALL [i,j] pairs which satisfy UPPER >= prefix[j]-prefix[i] >= LOWER are part of result
     */
    int res = mergeSort(prefix, 0, nums.length);
    // for(int i=0; i<prefix.length; i++) System.out.print(prefix[i]);
    return res;
  }
  private int mergeSort(long []arr, int l, int r){
    if(l >= r) return 0;
    int m = (l+r)/2;
    int res = mergeSort(arr, l, m) + mergeSort(arr, m+1, r);
    for(int i=m+1, j=m+1, k=l; k<=m; k++){
      // finding the LOWER AND UPPER pivot:: Used Sliding window for finding Pivots :: TC=O(n*log(n) + 2 * log(n)* n/2)
      // This op could have been done via binary search as well, overhead is big though in terms of TC = sum(log(n) * ceil(n/k) * 2 * log(k)), where k=2,4,8,...
      // Binary search TC = log(n)*n + overhead
      while(i<=r&&arr[i]<lower+arr[k]) i++;
      while(j<=r&&arr[j]<=upper+arr[k]) j++;
      res += j-i;
    }
    merge(arr, l, m, r);
    return res;
  }

  private void merge(long []arr, int l, int m, int r){
    long []tmp = new long[r-l+1];
    int i = l, j = m+1, k=0;
    while(i<=m && j<=r){
      if(arr[i] < arr[j]) tmp[k++] = arr[i++];
      else tmp[k++] = arr[j++];
    }
    while(i<=m) tmp[k++] = arr[i++];
    while(j<=r) tmp[k++] = arr[j++];
    for(i=0; i<tmp.length; i++) arr[i+l] = tmp[i];
  }
}
/* // BST + Fenwick tree
class Solution {
    int bitLen;
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long sum = 0;
        int ans = 0;
        long[] pre = new long[n];
        pre[0] = nums[0];
        for(int i=1;i<n;i++){
            pre[i] = pre[i-1]+nums[i];
        }
        Arrays.sort(pre);
        TreeMap<Long,Integer> map = new TreeMap<>();
        for(int i=0;i<n;i++){
            map.put(pre[i],i+1);
        }
        bitLen = n+1;
        int[] bit = new int[bitLen];
        sum = 0;
        for(int num:nums){
            sum += num;
            if(sum >=lower && sum <=upper){
                ans++;
            }
            int left=0, right=0;
            if(map.floorKey(sum-lower)!=null){
                int key = map.floorEntry(sum-lower).getValue();
                right = query(bit,key);
            }
            if(map.floorKey(sum-upper-1)!=null){
                int key = map.floorEntry(sum-upper-1).getValue();
                left = query(bit,key);
            }
            ans += (right-left);
            insert(bit,map.get(sum));
        }
        return ans;
    }

    private void insert(int[] bit, int index){
        while(index<bitLen){
            bit[index]++;
            index = index + (index & -index);
        }
    }

    private int query(int[] bit, int index){
        int ans = 0;
        while(index>0){
            ans += bit[index];
            index = index - (index & -index);
        }
        return ans;
    }
}
 */