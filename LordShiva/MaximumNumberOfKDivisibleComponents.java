class MaximumNumberOfKDivisibleComponents {
  /**
  https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/

  Was only math-ful. At each subtree if sum of elements underneath it are divisible by `k` it'll become the reqd component. Awesome right?? And within the subtree(A) if there's another subtree(B) with sum(%k == ), then it'll again become a reqd component, without affecting other components.
  Since A%k = 0 && B%k = 0 => (A-B)%k = 0.
  DFS returns the count of reqd components. Component sum I am tracking in `sumAtNode`.
  
  #math #divisibility-rules #contest #dfs #greedy
  **/
  Set<Integer> []adj;
  int[] sumAtNode;
  int[] values;
  int k;
  public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
    adj = new HashSet[n];
    this.k = k;
    this.values = values;
    sumAtNode = new int[n];
    for(int i=0; i<n; i++) adj[i] = new HashSet<>();
    for(int []e:edges){
      adj[e[0]].add(e[1]);
      adj[e[1]].add(e[0]);
    }
    return calcSumDfs(0, new boolean[n]);
  }
  private int calcSumDfs(int curr, boolean []v){
    v[curr] = true;
    sumAtNode[curr]+=values[curr];
    int sum = 0;
    for(int a: adj[curr]){
      if(v[a]) continue;
      sum += calcSumDfs(a, v);
      if(sumAtNode[a]%k != 0){
        sumAtNode[curr]+=sumAtNode[a];
      }
    }
    return (sumAtNode[curr]%k==0)?(sum+1):sum;
  }
}
