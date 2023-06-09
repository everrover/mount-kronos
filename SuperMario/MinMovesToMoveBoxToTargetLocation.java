package SuperMario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location
 * Brute force backtracking mentioned below. Using state-traversal and DP(encoding and decoding done to track DP key and state)
 * TC: O(m*n*4^m*n), SC: O(m*n*4^m*n)
 */
public class MinMovesToMoveBoxToTargetLocation {
  private static class Pt{
    int x,y;
    public Pt(int x, int y){
      this.x = x;
      this.y = y;
    }
  }
  private int encode(int bx, int by, int sx, int sy) {
    return (bx << 24) | (by << 16) | (sx << 8) | sy;
  }
  private int[] decode(int num) {
    int[] ret = new int[4];
    ret[0] = (num >>> 24) & 0xff;
    ret[1] = (num >>> 16) & 0xff;
    ret[2] = (num >>> 8) & 0xff;
    ret[3] = num & 0xff;
    return ret;
  }
  public int minPushBox(char[][] grid) {
    final int m = grid.length, n = grid[0].length;
    // find target `T`, shopkeeper-bot `S` & target
    int[] box=null, target=null, storekeeper=null;
    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
      if (grid[i][j] == 'B') box = new int[]{i, j};
      else if (grid[i][j] == 'T') target = new int[]{i, j};
      else if (grid[i][j] == 'S') storekeeper = new int[]{i, j};
    }
    if(box == null || target == null || storekeeper == null) return -1;
    Queue<Integer> q = new LinkedList<>();
    Map<Integer, Integer> dist = new HashMap<>();
    final int moves[][] = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    int res = Integer.MAX_VALUE;
    int start = encode(box[0], box[1], storekeeper[0], storekeeper[1]);
    dist.put(start, 0);
    q.offer(start);
    while(!q.isEmpty()){
      int u = q.poll();
      if(dist.get(u) > res) continue;
      int []decoded = decode(u);
      if(decoded[0] == target[0] && decoded[1] == target[1]){
        res = Integer.min(res, dist.get(u));
        continue;
      }
      for(int []move: moves){
        int ns[] = new int[]{decoded[2]+move[0], decoded[3]+move[1]};
        if(ns[0]<0 || ns[1]<0 || ns[0]>=m || ns[1]>=n || grid[ns[0]][ns[1]] == '#') continue;
        if(decoded[0] == ns[0] && decoded[1] == ns[1]){ // encountered box - move the box
          int nbx = decoded[0]+move[0], nby = decoded[1]+move[1];
          if(nbx<0 || nby<0 || nbx>=m || nby>=n || grid[nbx][nby] == '#') continue;
          int v = encode(nbx, nby, ns[0], ns[1]);
          if(dist.containsKey(v) && dist.get(v) <= dist.get(u)) continue;
          dist.put(v, dist.get(u)+1);
          q.offer(v);
        }else{ // NOT encountered box - move the box - don't move the box
          int v = encode(decoded[0], decoded[1], ns[0], ns[1]);
          if(dist.containsKey(v) && dist.get(v) <= dist.get(u)) continue;
          dist.put(v, dist.get(u));
          q.offer(v);
        }
      }
    }

    return res==Integer.MAX_VALUE?-1:res;
  }
}
