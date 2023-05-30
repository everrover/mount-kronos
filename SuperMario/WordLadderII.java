package SuperMario;

import java.util.*;


class WordLadderII {
  /**
   * https://leetcode.com/problems/word-ladder/description/
   * ## Prev approach
   * Used BFS to find the shortest path within the un-directed graph.
   * To perform the BFS, performed the brute-force over all possible next according to the defined rules and stepped to next position only if possible
   * Earlier used Trie to track the words, but hashing is faster to implement in Java(no native libs present)
   * Stored all possible paths for `size=res`, by finishing the BFS iteration at level
   * ## Stupid but new approach
   * - backtracking for listing all shortest paths in an unweighted and undirected graph
   */
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    boolean flag = false;
    List<List<String>> output = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();

    for(String word : wordList) {// generate graph
      if(word.equals(endWord)) flag = true;

      for(int i = 0;i < word.length();++i) {
        String key = word.substring(0, i) + "*" + word.substring(i + 1);

        if(!map.containsKey(key)) map.put(key, new ArrayList<>());

        map.get(key).add(word);
      }
    }

    if(!flag) return output;

    flag = false;
    Queue<Pair<String, List<String>>> que = new LinkedList<>();
    que.offer(new Pair(beginWord, Arrays.asList(beginWord)));
    Map<String, Integer> visited = new HashMap<>();
    visited.put(beginWord, 0);
    int count = 0;

    while(!que.isEmpty()) {
      int size = que.size();

      for(int i = 0;i < size;++i) {
        Pair<String, List<String>> top = que.poll();
        String word = top.getKey();

        for(int j = 0;j < word.length();++j) {
          String key = word.substring(0, j) + "*" + word.substring(j + 1);

          if(map.containsKey(key)) {
            for(String next : map.get(key)) {
              if(visited.containsKey(next) && visited.get(next) != count) continue;

              List<String> list = new ArrayList<>(top.getValue());
              list.add(next);

              if(next.equals(endWord)) {
                flag = true;
                output.add(list);
              } else {
                visited.put(next, count);
                que.offer(new Pair(next, list));
              }
            }
          }
        }
      }

      if(flag) break;

      ++count;
    }

    return output;
  }
}