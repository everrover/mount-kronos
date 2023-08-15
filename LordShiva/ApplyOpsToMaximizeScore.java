package LordShiva;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ApplyOpsToMaximizeScore {

  /**
   * https://leetcode.com/problems/apply-operations-to-maximize-score/
   * 
   * Got the hint from 'Max area of histogram' problem to find the maximum leftmost and rightmost indexes.
   * 
   * Used 'Sieving' to generate primes. Used 'Monotonic Stack' to find the maximum leftmost and rightmost indexes as per the provided condition..
   * Instead of generating primes over and over again, I used a static variable to store the primes.
   * 
   * Had to optimize the `pow` method to avoid time-limit breaches
   * 
   * TC: O(n+log(n)) SC: O(n)
   * 
   * #array #stack #queue #priorityqueue #math #prime #optimization #important
   */


  final long MOD = (int)(1e9+7);
  private static int [][]primes;
  private static void generatePrimes(){
    primes = new int[2][100001];
    Arrays.fill(primes[0], -1);
    // Arrays.fill(primes[1], 1);
    primes[0][1] = primes[1][1] = 0;
    primes[0][0] = primes[0][1] = 0;
    for(int i=2; i<100001; i++){
      if(primes[0][i] != -1) continue;
      primes[1][i] = 1;
      for(int j=i*2; j<100001; j+=i){
        primes[0][j] = 0;
        primes[1][j]++;
      }
    }
  }
  
  public int maximumScore(List<Integer> nums, int k) {
    long res = 1;
    final int n = nums.size();
    if(primes == null) generatePrimes();
    Stack<Integer> mono = new Stack<>();
    Queue<int[]> pq  = new PriorityQueue<>((a,b)->b[0]-a[0]);
    int []primeScore = new int[n];
    int []maxL = new int[n];
    int []maxR = new int[n];
    for(int i=0; i<n; i++){
      primeScore[i] = primes[1][nums.get(i)];
      pq.offer(new int[]{nums.get(i), i});
    }
    for(int i=0; i<n; i++){
      while(!mono.isEmpty() && primeScore[mono.peek()]<primeScore[i]) mono.pop();
      if(!mono.isEmpty()) maxL[i] = i-mono.peek();
      else maxL[i] = i+1;
      mono.push(i);
    }
    mono.clear();
    for(int i=n-1; i>=0; i--){
      while(!mono.isEmpty() && primeScore[mono.peek()]<=primeScore[i]) mono.pop();
      if(!mono.isEmpty()) maxR[i] = mono.peek()-i;
      else maxR[i] = n-i;
      mono.push(i);
    }
    // for(int i=0; i<n; i++) {
    //   System.out.println (primeScore[i]+"::"+maxL[i]+":"+maxR[i]);
    // }
    while(k>0){
      int []maxFetch = pq.poll();
      int num = maxFetch[0], cnt = maxL[maxFetch[1]]*maxR[maxFetch[1]];
      // System.out.println (num+"::"+cnt);
      int t = Math.min(k, cnt);
      res = (res*pow(num, t))%MOD;
      k-=cnt;
    }
    return (int)(res%MOD);
  }

  public long pow(long x, long n) {
    long res = 1;
    while (n>0) {
      if (n%2 == 1) res = (res * x) % MOD;
      x = (x * x) % MOD;
      n /= 2;
    }
    return res;
  }
}
