package NinetySix;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * https://leetcode.com/problems/the-k-strongest-values-in-an-array/
 *
 * // median finding can be done using QUICKSELECT algorithm :: https://rcoh.me/posts/linear-time-median-finding/
 * // - further using heaps can help reduce TC to TC=O(n)+O(n*log(k)+k*log(k))=O(n+n*log(k))
 * [quick-select avg case]+[maintaining heap of size k]+[polling k times]
 * // - also using quick-select twice, can reduce TC=O(n) [avg case]
 *      - once for finding median
 *      - once for finding first k elements along the pivot
 * https://leetcode.com/problems/the-k-strongest-values-in-an-array/discuss/674384/C%2B%2BJavaPython-Two-Pointers-%2B-3-Bonuses
 *
 * Please note: for me the QS solution didn't work and threw a TLE. Since overhead is huge for QS in my impl.
 * Also the mentioned median finding method is incorrect... ;)
 */
public class TheKStrongestValuesInArray {
  public int[] getStrongest(int[] arr, int k) { // O(n*log(n))
    Arrays.sort(arr);
    int median = arr[(arr.length-1)/2];
    int []newarr=new int[arr.length];
    for(int i=0; i<arr.length; i++){
      newarr[i] = Math.abs(arr[i]-median);
    }
    int []res = new int[k];
    int l=0, r=arr.length-1;
    while(k-->0 && l<=r){
      if(newarr[l] > newarr[r]) res[k] = arr[l++];
      else if(newarr[l] < newarr[r]) res[k] = arr[r--];
      else {
        res[k] = arr[l]>arr[r]?arr[l++]:arr[r--];
      }
    }
    return res;
  }
  // QS algorithm
  private static Random random = new Random();
  private static int rand(int l, int r){
    return random.nextInt(r-l+1)+l;
  }
  private static void swap(int []arr, int l, int r){
    int tmp = arr[l];
    arr[l] = arr[r];
    arr[r] = tmp;
  }
  public int partition(int[] arr, int l, int r, int p){
    int pivot = arr[p];
    swap(arr, p, r);
    p = l;
    for (int i = l; i <= r; i++) {
      if (arr[i] <= pivot) {
        swap(arr, i, p);
        p++;
      }
    }

    swap(arr, p, r);
    return p;
  }

  private int quickSelect(int []arr, int l, int r, int k){
    if (l == r) return arr[l];
    int pIndex = rand(l, r);
    pIndex = partition(arr, l, r, pIndex);

    if (k == pIndex) return arr[k];

    else if (k < pIndex) return quickSelect(arr, l, pIndex - 1, k);
    else return quickSelect(arr, pIndex + 1, r, k);
  }
  private int quickSelect(int []arr){
    return quickSelect(arr, 0, arr.length-1, (arr.length-1)/2);
  }
  public int[] getStrongestQSWithHeaps(int[] arr, int k) { // O(n)
    int median = quickSelect(arr);
    Queue<Integer> pq = new PriorityQueue<>(
        (a, b)->(Math.abs(a-median)==Math.abs(b-median)
            ?(a-b)
            :(Math.abs(a-median)-Math.abs(b-median))
        )
    );
    for(int i=0; i<arr.length; i++){
      pq.offer(arr[i]);
    }
    int []res = new int[k];
    while(k-->0) res[k] = pq.poll();
    return res;
  }


}
