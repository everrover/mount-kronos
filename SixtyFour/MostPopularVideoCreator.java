package SixtyFour;

// https://leetcode.com/problems/most-popular-video-creator/

import java.util.*;

public class MostPopularVideoCreator {

  private static class Solution {
    private static class O {
      public int vw, high;
      public String cr, id;

      public O(int vw, String cr, String id) {
        this.vw = this.high = vw;
        this.cr = cr;
        this.id = id;
      }

      public void addViews(String id, int vw){
        this.vw += vw;
        if(this.high < vw){
          this.high = vw;
          this.id = id;
        }else if(this.high == vw && this.id.compareTo(id)>0){
          this.id = id;
        }
      }
    }
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
      List<List<String>> res = new ArrayList<>();
      Map<String, O> creatorViews = new HashMap<>();
      for(int i=0; i<creators.length; i++){
        if(!creatorViews.containsKey(creators[i])) creatorViews.put(creators[i], new O(views[i], creators[i], ids[i]));
        else{
          O o = creatorViews.get(creators[i]);
          o.addViews(ids[i], views[i]);
        }
      }
      Queue<O> pq = new PriorityQueue<>((a,b)->(a.vw==b.vw)?a.id.compareTo(b.id):(b.vw-a.vw));
      for(O o: creatorViews.values()) pq.add(o);
      if(pq.isEmpty()) return res;
      int topViews = pq.peek().vw;
      while(!pq.isEmpty()){
        O o = pq.poll();
        if(topViews != o.vw) break;
        topViews = o.vw;
        List<String> tmp = new ArrayList<>();
        tmp.add(o.cr);
        tmp.add(o.id);
        res.add(tmp);
      }
      return res;
    }
  }

  public static void main(String[] args) {
    Solution sol  =new Solution();
    System.out.println(sol.mostPopularCreator(
        new String[]{"alice","bob","alice","chris"},
        new String[]{"one","two","three","four"},
        new int[]{5,10,5,4}
    ));
  }
}
