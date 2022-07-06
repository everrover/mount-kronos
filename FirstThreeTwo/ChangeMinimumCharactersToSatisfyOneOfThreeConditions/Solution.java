package FirstThreeTwo.ChangeMinimumCharactersToSatisfyOneOfThreeConditions;

class Solution {
    private int getMaxCountOccurrenceOfChar(int[] counts){
        int maxC=0, cum=0;
        for(int count: counts){
            maxC = Math.max(maxC, count);
            cum += count;
        }
        return cum-maxC;
    }

    private int calcSwaps(int countsA[], int countsB[], int aSize, int bSize){
        int swaps = Integer.MAX_VALUE;
        for(int i=0; i<25; i++){
            swaps = Math.min(swaps, bSize-countsB[i]+countsA[i]);
        }
        return swaps;
    }
    public int minCharacters(String a, String b) {
        int countsA[] = new int[26];
        int countsB[] = new int[26];
        for(char ch: a.toCharArray()){ countsA[ch-'a']++; }
        for(char ch: b.toCharArray()){ countsB[ch-'a']++; }
        int maxNotOccurCount = getMaxCountOccurrenceOfChar(countsA)+ getMaxCountOccurrenceOfChar(countsB);
        for(int i=1; i<26; i++){
            countsA[i] += countsA[i-1];
            countsB[i] += countsB[i-1];
        }
        // System.out.println(maxNotOccurCount);
        return Math.min(Math.min(calcSwaps(countsA, countsB, a.length(), b.length()), calcSwaps(countsB, countsA, b.length(), a.length())), maxNotOccurCount);
    }
}