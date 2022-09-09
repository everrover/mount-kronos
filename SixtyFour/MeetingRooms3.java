package SixtyFour;

import java.util.*;

public class MeetingRooms3 {
    private static class Meet{
        public int start, end, duration, room;
        public Meet(int start, int end){
            this.start = start; this.end = end;
            this.duration = end-start;
            this.room = -1;
        }
    }

    public int mostBooked(int n, int[][] meetings) {
        List<Meet> meets = new ArrayList<>(meetings.length);
        for(int []meeting: meetings){
            meets.add(new Meet(meeting[0], meeting[1]));
        }
        Collections.sort(meets, (a, b)->(a.start-b.start));
        int N=0;
        int []meetCounts = new int[n];
//        Queue<Meet> pq = new PriorityQueue<>((a, b)->(a.end-b.end));
        Queue<Meet> pq = new PriorityQueue<>((a, b)->(int)(a.end==b.end?(a.room-b.room):(a.end-b.end)));
        Queue<Integer> smallestRooms = new PriorityQueue<>((a, b)->(a-b));
        for(int i=0;i<n;i++) smallestRooms.add(i);
        for(Meet meet: meets){
            while(!pq.isEmpty() && pq.peek().end < meet.start){
                smallestRooms.add(pq.poll().room);
            }

            if(pq.size() < n){
                meetCounts[smallestRooms.peek()]++;
                meet.room = smallestRooms.poll();
                pq.add(meet);
            }else{
                Meet firstEndingMeet = pq.poll();
                meet.start = firstEndingMeet.end;
                meet.end = firstEndingMeet.end+meet.duration;
                meet.room = firstEndingMeet.room;
                meetCounts[firstEndingMeet.room]++;
                pq.add(meet);
            }
        }
        int res = -1; N = -1;
        for(int i=0; i<meetCounts.length; i++){
            if(meetCounts[i] > N){
                N = meetCounts[i];
                res = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MeetingRooms3 rooms = new MeetingRooms3();
        System.out.println(rooms.mostBooked(2, new int[][]{{0, 10},{1,2},{12,14},{13,15}}));
        System.out.println(rooms.mostBooked(2, new int[][]{{0, 10},{1,5},{2,7},{3,4}}));
        System.out.println(rooms.mostBooked(3, new int[][]{{0, 20},{2,10},{3,5},{4,9},{6,8}}));
    }
}
