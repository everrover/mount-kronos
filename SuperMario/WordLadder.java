package SuperMario;

import java.util.*;


class WordLadder {
  /**
   * https://leetcode.com/problems/word-ladder/description/
   * Used BFS to find the shortest path within the un-directed graph.
   * To perform the BFS, performed the brute-force over all possible next according to the defined rules and stepped to next position only if possible
   * Earlier used Trie to track the words, but hashing is faster to implement in Java(no native libs present)
   */
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> hashset = new HashSet<>(wordList);
    Set<String> isVisited = new HashSet<>();
    if(!hashset.contains(endWord)) return 0;
    Queue<String> q = new LinkedList<>();
    q.add(beginWord);
    isVisited.add(beginWord);
    int res = 1;
    while(!q.isEmpty()){
      int size = q.size();
      for(int i=0; i<size; i++){
        String s = q.poll();
        if(endWord.equals(s)) return res;
        for(int j=0; j<s.length(); j++){
          for(char ch='a'; ch<='z'; ch++){
            char []wordArr = s.toCharArray();
            wordArr[j] = ch;
            String toCheck = new String(wordArr);
            if(hashset.contains(toCheck) && !isVisited.contains(toCheck)){
              // System.out.println(s+"::"+toCheck);

              q.offer(toCheck);
              isVisited.add(toCheck);
            }
          }
        }
      }
      res++;
    }
    return 0;
  }
}