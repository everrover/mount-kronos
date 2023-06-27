package SuperMario;

public class ExtraCharsInString {
  private static class TrieNode{
    TrieNode []children;
    boolean isEnd;
    public TrieNode(){
      children = new TrieNode[26];
      isEnd = false;
    }

    public void insert(String word){
      TrieNode curr = this;
      for(char c: word.toCharArray()){
        if(curr.children[c-'a'] == null) curr.children[c-'a'] = new TrieNode();
        curr = curr.children[c-'a'];
      }
      curr.isEnd = true;
    }

    public boolean find(String word) {
      TrieNode curr = this;
      for (char c : word.toCharArray()) {
        if (curr.children[c - 'a'] == null) return false;
        curr = curr.children[c - 'a'];
      }
      return curr.isEnd;
    }
  }

}
