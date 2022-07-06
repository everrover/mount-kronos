package FirstThreeTwo.SmallestStringWithSwaps;

import java.util.*;

public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        final int size = s.length();
        boolean visited[] = new boolean[size];
        boolean isConnected[][] = new boolean[size][size];
        char []chs = new char[size];

        for(List<Integer> pair: pairs){ // building graph edges
            isConnected[pair.get(0)][pair.get(1)] = isConnected[pair.get(1)][pair.get(0)] = true;
        }

        for(int i=0, j=0; i<size; i++, j=0){
            if(visited[i]) continue;
            char ch = s.charAt(i);
            // perform bfs or dfs from this node and get all indices in traversal
            List<Integer> toSort = new LinkedList<>();
            bfs(i, toSort, visited, isConnected);
            // insert all indexed chars in string
            char[] tmp = new char[toSort.size()];
            for(int ts: toSort){
                tmp[j++] = s.charAt(ts);
            }
            // sort the chars in string
            Arrays.sort(tmp);
            Collections.sort(toSort);
            // reinsert them into result string
            for(j=0; j<toSort.size(); j++){
                chs[toSort.get(j)] = tmp[j];
            }
        }
        return new String(chs);
    }

    private void bfs(int curr, List<Integer> toSort, boolean []visited, boolean[][] isConnected){
        if(visited[curr]) return;
        Queue<Integer> q = new LinkedList<>();
        q.add(curr);
        toSort.add(curr);
        visited[curr] = true;
        while(!q.isEmpty()){
            int c = q.poll();
            for(int i=0; i<visited.length; i++){
                if(visited[i] || !isConnected[c][i]) continue;
                toSort.add(i);
                visited[i] = true;
                q.add(i);
            }
        }
        return;
    }
}
