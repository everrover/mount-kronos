package LordShiva;

public class LexoSmallestBeautifulString {
  /**
   * https://leetcode.com/problems/lexicographically-smallest-beautiful-string/
   * 
   * Obs1: Initiallly the string is already beautiful. Hence any subarray of size>=2 will be non-palindromic
   * For `...axb...` sub-string where we have replaced last char with `b`, b!=x and b!=a is condition for non-palindromic beautiful strings.
   * If `i=1`, then we need to check for duplicates only with `i=0`.
   * If b is replaced, all characters after `b` should be either amongs `a` or `b` or `c` to maintain smallest lexicographical order.
   * 
   * TC: O(n) SC: O(n)
   * 
   * #greedy #string
   */

   public String smallestBeautifulString(String s, int k) {
    char f = (char)('a'+k-1);
    char []str = s.toCharArray();
    if(str.length == 1 && (char)(str[0]+1)<=f) {
      str[0] = (char)(str[0]+1);
      return new String(str);
    }
    int i=str.length-1;
    while(i>=0){
      char last = (char)(str[i]+1);
      for(; last<=f; last++){
        if((i>1 && last!=str[i-1] && last!=str[i-2]) || (i==1 && last!=str[i-1]) || i==0){
          break;
        }
      }
      if(last == (char)(f+1)) { i--; continue; }
      else{
        str[i] = last;
        for(int j=i+1; j<str.length; j++){
          if(j>=2) { // choose the smallest possible char which prevents palindrome build-up
            boolean ma = str[j-1] != 'a' && str[j-2] != 'a';
            boolean mb = str[j-1] != 'b' && str[j-2] != 'b';
            // boolean mc = str[j-1] != 'c' && str[j-2] != 'c';
            if(ma) str[j] = 'a';
            else if(mb) str[j] = 'b';
            else str[j] = 'c';
          }else{
            if(str[j-1] == 'a') str[j] = 'b';
            else str[1] = 'a';
          }
        }
        break;
      }
    }
    return i>=0?(new String(str)):"";
  }
}
