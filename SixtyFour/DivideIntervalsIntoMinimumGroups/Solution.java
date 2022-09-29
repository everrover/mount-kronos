package SixtyFour.DivideIntervalsIntoMinimumGroups;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/
 * Applied principle from meeting-rooms.
 * Meeting in same room can only starts when other one ends.
 * If over-lapping ones are found simply increment meeting room count by 1.
 * Effectively becomes a variation of line-sweep algorithm.
 */

public class Solution {
    private static class T{
        public int num;
        public boolean isS;
        public T(int num, boolean isS){
            this.num = num;
            this.isS = isS;
        }
    }
    public int minGroups(int[][] intervals) {
        T []ts = new T[intervals.length*2];
        int tmp = 0;
        for(int []interval: intervals){
            ts[tmp++] = new T(interval[0], true);
            ts[tmp++] = new T(interval[1], false);
        }
        Arrays.sort(ts, (a, b)->(a.num == b.num)?(a.isS?-1:1):(a.num-b.num));
        int res = 1;
        tmp = 0;
        for(T t: ts){
            // System.out.print(t.num+":"+t.isS+"--");
            if(t.isS){
                tmp++;
            }else{
                tmp--;
            }
            res = Math.max(res, tmp);
        }
        return res;
    }

    public int minGroupsWithPQ(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int []interval: intervals){
            if(!pq.isEmpty() && pq.peek()<interval[0]){ // means at-least one more room is now available
                pq.poll();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minGroups(new int[][]{{1,3},{5,6},{8,10},{11,13}}));
    }
}