package NinetySix;


/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Used bloody stacks
 * TWIST:: What if it was asked about additions and not deletions>?
 * ANS:: We could have appended the orphan parenthesis over at extreme ends. Only one loop have been required.
 * If `stack` count < 0, `)` are in excess. We add `stack` number of `(` in extreme left
 * If `stack` count > 0, `(` are in excess. We add `stack` number of `)` in extreme right
 * ```
 * for(int i=0, j=0; i<s.length(); i++){
 *       char ch = s.charAt(i);
 *       if(ch == '('){
 *         stack++;
 *       }else if(ch == ')'){
 *         stack--;
 *       }
 *     }
 * sb = new StringBuilder();
 * for(int i=stack; i<0; i++) sb.insert(0, '(');
 * for(int i=0; i<stack; i++) sb.append(')');
 * return sb.toString();
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
