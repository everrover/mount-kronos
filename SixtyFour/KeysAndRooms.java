package SixtyFour;

import java.util.List;

public class KeysAndRooms {

    private void dfs(int currRoom, int[] roomsVisited, List<List<Integer>> rooms, boolean []isVisited){
        if(isVisited[currRoom]){
            return;
        }
        isVisited[currRoom] = true;
        roomsVisited[0]++;
        for(int room: rooms.get(currRoom)){
            dfs(room, roomsVisited, rooms, isVisited);
        }
        return;
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int []roomsVisited = new int[1];
        dfs(0, roomsVisited, rooms, new boolean[rooms.size()]);
        return roomsVisited[0]==rooms.size();
    }
}
