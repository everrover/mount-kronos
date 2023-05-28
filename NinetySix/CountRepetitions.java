package NinetySix;

/**
 * https://leetcode.com/problems/count-the-repetitions/
 * Got stuck in the suffix part.
 * Result(tbc when we encounter a character at pos where we previously found next at) =
 * prefixCount(count encountered till now) +
 * patternCount(count we'll require for (n1-start)/(k-start) seen patterns)
 * suffixCount(count we'll req for (n1-start)%(k-start) patterns remaining) eg:: `aaa|3` `aa|1`
 * https://leetcode.com/problems/count-the-repetitions/solutions/95398/c-solution-inspired-by-70664914-with-organized-explanation/
 */
public class CountRepetitions {
  public int getMaxRepetitions(final String s1, final int n1, final String s2, final int n2) {
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int []count = new int[n1+1];
    int []next = new int[n1+1];
    int j=0, k=0, cnt=0;
    while(++k <= n1) {
      for(int i=0; i<str1.length; i++){
        if(str1[i] == str2[j]){
          if(++j == str2.length){
            j=0;
            cnt++;
          }
        }
      }
      count[k] = cnt;
      next[k] = j;
      for(int start=0; start<k; start++){
        if(next[start] == j) {
          int prefixCount = count[start];
          int patternCount = (count[k] - count[start])*((n1-start)/(k-start));
          int suffixCount = count[start + (n1-start)%(k-start)] - count[start];

          return (prefixCount+patternCount+suffixCount)/n2;
        }
      }
    }
    return count[n1]/n2;
  }
}
