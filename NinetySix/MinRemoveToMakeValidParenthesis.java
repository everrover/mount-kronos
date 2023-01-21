package NinetySix;


/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Used bloody stacks
 * ```
 */
public class MinRemoveToMakeValidParenthesis {
  public String minRemoveToMakeValid(String s) {
    int stack = 0;
    StringBuilder sb = new StringBuilder();
    for(int i=0, j=0; i<s.length(); i++){
      char ch = s.charAt(i);
      if(ch == '('){
        stack++;
        sb.append(ch);
      }else if(ch == ')'){
        if(stack != 0) {
          stack--;
          sb.append(ch);
        }
      }else{
        sb.append(ch);
      }
    }
    s = sb.toString();
    sb.setLength(0);
    stack = 0;
    for(int i=s.length()-1; i>=0; i--){
      char ch = s.charAt(i);
      if(ch == '('){
        if(stack != 0){
          stack--;
          sb.append(ch);
        }
      }else if(ch == ')'){
        stack++;
        sb.append(ch);
      }else{
        sb.append(ch);
      }
    }

    return sb.reverse().toString();
  }
}
