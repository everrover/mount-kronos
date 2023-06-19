package SuperMario;

import java.util.Arrays;

public class MinWindowSubstring {
  /**
   * https://leetcode.com/problems/minimum-window-substring/description/'
   * TC: O(n) SC: O(n)
   * Description:
   * - Used Sliding Window technique to solve this problem. Cheers!
   * - Kept two pointers left and right. Initially both are at 0th index.
   * - Kept two arrays map and extras. `map` is used to keep track of the characters in `t` and `extras` is used to keep track of the extra characters in substring `s`.
   * - Initially, `map` is filled with the count of characters in `t` and `extras` is filled with -1.
   * - Now, we start moving the right pointer. If the character at `right` is present in `map`, we decrement the count in `map` and increment the count in `extras`.
   * - If the count in `extras` is greater than the count in `map`, we start moving the left pointer and decrement the count in `extras` and increment the count in `map`.
   * - If the count in `extras` is less than or equal to the count in `map`, we check if the current window size is less than the answer size. If yes, we update the answer size and the answer string.
   * - We keep moving the right pointer until it reaches the end of the string `s`.
   * - Finally, we return the answer string.
   * Initially used HashMap, but replaced with array as the characters are ASCII characters and fetch ops are faster in array.
   * #sliding-window #hashmap #array #string
   */
  public String minWindow(String s, String t) {
    int left = 0, right = 0, ansSize = Integer.MAX_VALUE, size=0;
    char []str = s.toCharArray();
    char []ttr = t.toCharArray();
    final int m=s.length(), n=t.length();
    String ans = "";

    if(m == 0 || n == 0 || m<n){ // empty strings
      return ans;
    }

    int[] map = new int[128];
    int[] extras = new int[128];
    Arrays.fill(map, -1);
    Arrays.fill(extras, -1);
    for(int i=0; i<n; i++){
      if(map[ttr[i]] == -1){
        map[ttr[i]] = 0;
        extras[ttr[i]] = 0;
      }
      map[ttr[i]]++;
    }

    while(right<m) {
      if (map[str[right]] != -1 && map[str[right]] > 0) {
        size++;
        map[str[right]]--;
        while(size == n) {
          while (map[str[left]] == -1) left++;
          if (ansSize > (right - left + 1)) {
            ans = s.substring(left, right + 1);
            ansSize = (right - left + 1);
          }

          if (extras[str[left]]!=-1 && extras[str[left]]>0) {
            extras[str[left]]--;

          } else {
            map[str[left]]++;
            size--;
          }
          left++;
        }
      } else if (map[str[right]] != -1 && map[str[right]] == 0) {
        extras[str[right]]++;
      }
      right++;
    }
    return ans;
  }
}
