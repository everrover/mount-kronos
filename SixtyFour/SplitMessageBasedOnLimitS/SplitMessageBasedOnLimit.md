# [2468. Split Message Based on Limit](https://leetcode.com/problems/split-message-based-on-limit/)

### O(n) solution

```java
class Solution {

  private int S1(int L, int x, int S){
    return x*(L-3-(""+x).length())-S;
  }
  
  private int S3(int x, int L){ // forgot to modify for A before submission - still worked on all TCs though - ??
    return 3+(""+x).length()*2-L;
  }
  private int findX(int L, int S){
    int x = 1;
    int S2 = (""+x).length();
    while(S3(x, L)<0 && S2 > S1(L, x, S)){
      x++;
      S2 += (""+x).length();
    }
    if(S3(x, L)>=0) return -1;
    return x;
  }
  public String[] splitMessage(String message, int limit) {
    List<String> res = new ArrayList<>();
    final int L = limit, S = message.length();
    final int B = findX(L, S);
    if(B == -1) return new String[0];
    int j = 0;
    for(int A=1; A<=B; A++){
      int r = L-((""+A).length()+(""+B).length()+3);
      if(j+r > message.length()) {
        break;
      }
      res.add(message.substring(j, j+r)+'<'+A+'/'+B+'>');
      j += r;
    }
    if(j<message.length()){
      res.add(message.substring(j)+'<'+B+'/'+B+'>');
    }
    String[] ans = new String[res.size()];
    res.toArray(ans);
    return ans;
  }
}
```

### O(ln(n)) solution

Basically, the number of characters in message have to be split into multiple elements. 
Split message formula is `{message_component}<A/B>`. If we know we'll use `x` number of elements `S1=(B+3)*x, B=len(str(x))` 
will be used for encoding the characters `<`, `>`, `/` and number `x`. 

Calculating spaces required by `A` is a bit complex. From `[1...9]`, 1 character will be needed. For `[10...99]`, 2 char 
spaces will be needed and so on. To keep track of that I have used variable `Aprev`, which is added to `A*mid` for 
calculating required spaces in binary-search. However, before executing binary-search we need to be in a range where 

We exponentially increase our ranges from `[1...9]`, `[10...99]`, `[100...999]` and so on... to find where our `x` lies and 
calculate the actual `x` via binary search to find the pivot in that range.

There are cases where S-S1-S2 becomes -ve we can say that current limit can't be used for encoding our string. For that `t` 
variable is used for storing previous sum.

```java
public class Solution {

  private int S1(int L, int x){
    int B = Integer.toString(x).length();
    return (L-B-3)*x;
  }

  private int S2(int sum, int x){
    int A = Integer.toString(x).length();
    return sum + A*x;
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
```