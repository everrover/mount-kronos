package LordShiva;

public class FaultyKeyboard {
  /**
   * https://leetcode.com/problems/faulty-keyboard/
   * 
   * #greedy #string
   */
  public String finalString(String s) {
    StringBuilder res = new StringBuilder();
    for(char c: s.toCharArray()){
      if(c=='i') res.reverse();
      else res.append(c);
    }
    return res.toString();
  }
}
