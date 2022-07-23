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
 * For [0...i] the last element added will be unique in the sub-array. It can never pair up with any other element in the
 * sub-array to create a roll seq of length `2`. For [i+1...j] the jth element will be unique in the sub-array. Won't work.
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