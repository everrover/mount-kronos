package SixtyFour;

import java.util.Arrays;

public class MaxWhiteTilesCoveredByCarpet {
    /**
     * https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/
     */
    private int binarySearch(int [][]tiles, int l, int r, int val){
        int pivot = -1;
        while(l<=r){
            int mid = (l+r)/2;
            if(tiles[mid][1] <= val){
                pivot = mid;
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return pivot;
    }

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b)->(a[0]-b[0]));
        int res = 0;
        long []prefix = new long[tiles.length];
        prefix[0] = tiles[0][1]-tiles[0][0]+1;
        for(int i=1; i<tiles.length; i++){
            prefix[i] = prefix[i-1]+(tiles[i][1]-tiles[i][0]+1);
        }
        for(int i=0; i<tiles.length; i++){
            if(tiles[i][1]-tiles[i][0]+1 >= carpetLen){ return carpetLen; }
            // tile[1]...r range
            int carpetReach = tiles[i][0]+carpetLen-1;
            int r = binarySearch(tiles, i, tiles.length-1, carpetReach);
            int count = (int)(prefix[r]-(i>0?prefix[i-1]:0));
            if(r<(tiles.length-1) && carpetReach>=tiles[r+1][0]){
                // if (tiles[i][0]+carpetLen-1) is smaller than tiles[r+1][0], this'll be negative.
                // Hence answer will be reduced. Whereas since carpet never reaches here 0 is to be added, since no range is covered.
                int partialCarpetting = Math.min(tiles[r+1][1], carpetReach)-tiles[r+1][0]+1;
                count += partialCarpetting;
//                count += Math.max(0, partialCarpetting); // if cond isn't placed, this'll be negative. Hence, need to mitigate it.`
            }
            res = Math.max(res,count);
        }
        return res;
    }
}
