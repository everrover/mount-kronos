package FirstThreeTwo.ShortestWordDistance;

public class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        Integer w1=null, w2=null;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<wordsDict.length; i++){
            if(word1.equals(wordsDict[i])) w1 = i;
            else if(word2.equals(wordsDict[i])) w2 = i;
            if(w1 != null && w2 != null) res = Math.min(res, Math.abs(w2-w1));
        }
        return res;
    }
}
