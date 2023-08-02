package SuperMario;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WordBreakII {
  // https://leetcode.com/problems/word-break-ii/

  /**
   * Used Trie to find if a string can be broken into words from a dictionary.
   * Used DP to find if a string can be broken into words from a dictionary.
   * TC: O(n^2) SC: O(n)
   * DP[I] stores if the string from I till N can be broken into words from the dictionary.
   * Optimization::
   * If, iteration would have been done from N -> i for each i, then the TC would have been O(n^2) but slightly better.
   * Especially for more complex similar problems. eg: Extra characters in string[./ExtraCharsInString.java]
   *
   * #dynamic-programming #trie #string #hashing[could've used]
   */
  public class TrieNode{
    boolean isWord;
    TrieNode[] map;
    public TrieNode(){
      isWord = false;
      map = new TrieNode[128];
      Arrays.fill(map, null);
    }
  }
  public void insertTrieNode(char str[]){
    TrieNode crawler = root;
    for(int i=0; i<str.length; i++){
      int ch = str[i]-'a';
      if(crawler.map[ch] == null)
        crawler.map[ch] = new TrieNode();
      crawler = crawler.map[ch];
    }
    crawler.isWord = true;
  }
  public boolean findString(int beg, int end){
    TrieNode crawler = root;
    for(int i=beg; i<end; i++){
      int ch = str[i]-'a';
      if(crawler.map[ch] == null)
        return false;
      crawler = crawler.map[ch];
    }
    return crawler.isWord;
  }
  TrieNode root;
  Set<String> set;
  boolean dp[];
  boolean visited[];
  char[] str;
  public boolean wordBreak(String s, List<String> wordDict) {
    root = new TrieNode();
    // set = new HashSet<>();
    int n = s.length();
    dp = new boolean[n+1];
    visited = new boolean[n+1];
    dp[n] = true;
    visited[n] = true;
    str = s.toCharArray();
    for(String str: wordDict) insertTrieNode(str.toCharArray());

    return wordBreakUtil(0);
  }
  public boolean wordBreakUtil(int curr) {
    if(dp[curr]) return true;

    for(int i=curr+1; i<=str.length; i++){
      if(!findString(curr, i)) continue;
      if(visited[i]){
        dp[curr] = dp[i] || dp[curr];
      }else if(wordBreakUtil(i)){
        dp[curr] = true;
      }

    }
    visited[curr] = true;
    return dp[curr];
  }
}
