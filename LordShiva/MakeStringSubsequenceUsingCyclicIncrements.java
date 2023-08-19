package LordShiva;

public class MakeStringSubsequenceUsingCyclicIncrements {
  /**
   * https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/
   * 
   * Didn't read properly, thought it was a longest subsequence problem. Hence started building graphs and Dikstra's algo around it.
   * Additionally dinner was served and got distracted.
   * 
   * Just needed to check for sub-sequence, with a constraint around cyclic characters  
   * 
   * #easy #greedy #two-pointer #contest
   * 
   * TC: O(n) SC: O(1)
   */
  public boolean canMakeSubsequence(String s1, String s2) {
    int i=0;
    for(char c: s2.toCharArray()){
      char t = c=='a'?'z':(char)(c-1);
      while(i<s1.length() && s1.charAt(i)!=c && s1.charAt(i)!=t) i++;
      if(i == s1.length()) return false;
      i++;
    }
    return true;
  }

  /*
  
  bool canMakeSubsequence(string str1, string str2) // backtracking brute force - just for ref
	{
      return dfs(str1, str2, 0, 0);
  }
  
  bool dfs(string &s1, string &s2, int start1, int start2)
  {
  // if full str2 has been scanned, return true
      if(start2 == s2.size()) return true;
  // else, if no more str1 left, return false
      if(start1 == s1.size()) return false;
      // if we can apply cyclical increment or two characters are identical, move on
      if( s1[start1] - s2[start2] == -1 | s1[start1] - s2[start2] == 0 | (s1[start1] == 'z' & s2[start2] == 'a'))
      {
          return dfs(s1, s2, start1+1, start2+1);
      }
  // if we can't apply cyclical increment, scan next character in str1 and check result
      return dfs(s1, s2, start1+1, start2);
  }   
  */

}
