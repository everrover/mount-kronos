package SixtyFour.SplitMessageBasedOnLimitS;

public class Solution {

  private int S1(int L, int x){
    int b = Integer.toString(x).length();
    return (L-b-3)*x;
  }

  private int S2(int sum, int x){
    int b = Integer.toString(x).length();
    return sum + b*x;
  }

  private int[] findX(final int L, final int S){
    int ll = 1, ul = 9;
    int A = S2(0, ul), B = S1(L, ul);
    int s = B-A, t = 0, Aprev = 0;
    while(s < S && t<s){
      t = s; Aprev += A;
      ll *= 10; ul = ul*10+9;
      B = S1(L, ul); A = S2(Aprev, ul);
      s = B-A;
    }
    if(t>=s) return null;
    return new int[]{ll, ul, Aprev};
  }
  private int findB(int l, int r, int prev, final int L, final int S){
    while(l<r){
      int mid = (l+r)/2;
      if(S1(L, mid)-S2(prev, mid) < S) l=mid+1;
      else r = mid;
    }
    return r;
  }
  public String[] splitMessage(String message, int limit){
    final int L = limit, S = message.length();
    final int []x = findX(L, S); // find via exponential linear
    if(x == null) return new String[0];
    final int b = findB(x[0], x[1], x[2], L, S); // find via binary search
    String []res = new String[b];
    int j = 0;
    for(int a=1; a<=b; a++){
      int r = L-(Integer.toString(a).length()+Integer.toString(b).length()+3);
      if(j+r > message.length()) break;
      res[a-1] = message.substring(j, j+r)+'<'+a+'/'+b+'>';
      j+=r;
    }
    if(j<message.length()) res[b-1] = message.substring(j)+'<'+b+'/'+b+'>';
    return res;
  }

  public static void main(String[] args) {
    StringBuilder msg = new StringBuilder();
    for(int i=1; i<=20; i++){
      msg.append("this is really a very awesome message. ").append(i).append(". ");
    }
    new Solution().splitMessage(msg.toString(), 15);
  }
}