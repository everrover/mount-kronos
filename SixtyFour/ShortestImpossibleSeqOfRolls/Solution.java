package SixtyFour.ShortestImpossibleSeqOfRolls;

/**
 * https://leetcode.com/problems/shortest-impossible-sequence-of-rolls/
 *
 * Well, tricky again. Used the right direction... just got diverted to the wrong path.
 *
 * I must have had tried out on larger arrays. I might have had an easy visualization using it.
 *
 * Think about it. The smallest subarray [0...i] which contains all `k` elements will produce a roll sequence of length `1`.
 * In no way can it contain a roll sequence of length `2`. Some or the other sequence of length `2` will always be missed. The
 * next smallest subarray [i+1...j] which contains all `k` rolls will produce all subsequences of length `2`, with previous.
 * Again, [0...j] will always have atleast one missed roll seq of length `3` and greater. And cycle repeats...
 *
 * Proof of missed roll sequences:
 * For N sets, we have N unique values in each set(last element within each set). If we were to take them into a sequence roll,
 * we would never be able to create a roll seq of length >N for all numbers. Hence, atleast one missed roll seq of length >N will be there
 * for N such subarrays.
 *
 * TC: O(n) MC: O(K)
 *
 * https://leetcode.com/problems/shortest-impossible-sequence-of-rolls/discuss/2322280/JavaC%2B%2BPython-One-Pass-O(K)-Space
 */
class Solution {
    public int shortestSequence(int[] rolls, int k) {
        int dice[] = new int[k+1];
        int seq=0, cnt=0;
        for(int roll: rolls){
            if(dice[roll] == seq){
                dice[roll] = seq+1;
                ++cnt; // count of unique values encountered
                if(cnt % k == 0) seq++;
            }
        }
        return seq+1;
    }
}
