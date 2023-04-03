package NinetySix;

public class ReverseOnlyLetters {
  /*
  https://leetcode.com/problems/reverse-only-letters/submissions/
   */

  public String reverseOnlyLetters(String s) {// MC:O(1)
    int i=0, j=s.length()-1;
    StringBuilder res = new StringBuilder();
    while(i<s.length()){
      int cnt = 0;
      while(i<s.length()&&Character.isLetter(s.charAt(i))) {cnt++;i++;}
      while(j>=0&&cnt-->0){
        while(!Character.isLetter(s.charAt(j)))j--;
        res.append(s.charAt(j--));
      }
      if(i<s.length())res.append(s.charAt(i++));
    }
    return res.toString();
  }
  public String reverseOnlyLettersV0(String s) { // MC:O(n)
    int i=0;
    StringBuilder sb = new StringBuilder(), res = new StringBuilder();
    while(i<s.length()){
      if(Character.isLetter(s.charAt(i))) {
        sb.append(s.charAt(i));
      }
      i++;
    }
    // System.out.println(sb.toString());
    sb.reverse(); i=0;
    for(int k=0; k<s.length(); k++){
      if(!Character.isLetter(s.charAt(k))) res.append(s.charAt(k));
      else res.append(sb.charAt(i++));
    }
    return res.toString();
  }
}
