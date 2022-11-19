package SixtyFour;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/design-a-food-rating-system/
 * Have used two maps:
 *  - `cuisineMap`: Defines a PQ for a particular cuisine
 *  - `foodMap`: Defines the details of food mapped to FCR
 *
 * FCR stores index of element within PQ for tracking rating updates
 * Idea's simple: Update `food` associated `FCR` element in `cuisine-PQ` for each rating update ,O(m*ln(n))::m=number of updates, n=size of PQ.
 * Seeking PQ for top-rated `cuisine-food` is simple as getting the HEAD of associated PQ, O(1)
 *
 * Better way:
 * Using two maps:
 * - one: For `food`->`FCR` map
 * - two: For `cuisine`->[{rating(int): {bst_of_food_names/PQ_of_food_names}}]
 *
 * `idx` wasn't required here
 */

class FoodRatings {
  private static class FCR implements Comparable<FCR>{
    String food, cuisine;
    int rating, idx;
    public FCR(String food, String cuisine, int rating, int idx){
      this.food = food;
      this.cuisine = cuisine;
      this.rating = rating;
      this.idx = idx;
    }
    public FCR(String food, String cuisine, int rating){
      this.food = food;
      this.cuisine = cuisine;
      this.rating = rating;
      this.idx = -1;
    }

    @Override
    public int compareTo(FCR fcr){
      return ((this.rating == fcr.rating)? fcr.food.compareTo(this.food): (this.rating-fcr.rating));
    }
  }
  Map<String, FCR> foodMap;
  Map<String, Heap> cuisineMap;

  private static class Heap {
    public int capacity, size;
    public FCR []arr;
    public Heap(int capacity){
      this.capacity = capacity;
      this.size = 0;
      this.arr = new FCR[capacity];
    }

    // public int getMax() { // O(1)
    //     if(size == 0) return -1;
    //     return this.arr[0].rating;
    // }

    public String getMax() { // O(1)
      // for(FCR fcr: arr) {
      //     if(fcr == null) break;
      //     System.out.println(fcr.food+"::"+fcr.rating);
      // }
      if(size == 0) return "underflow!!";
      return this.arr[0].food;
    }

    public int getSize() { // O(1)
      return this.size;
    }

    public void insert(FCR fcr){ // O(ln(size+1))
      if(size == capacity) return;
      this.arr[this.size++] = fcr;
      if(this.size == 1) return;
      goUp(size-1);
    }

    public FCR delete(int idx){ // extract op
      if(idx >= size) return null;
      swapFcr(idx, size-1);
      size--;
      if(parent(idx)!=-1&&arr[parent(idx)].compareTo(arr[idx])<0){
        goUp(idx);
      }else{
        goDown(idx);
      }
      FCR res = arr[size];
      return res;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public void updateRating(int idx, int rating) {
      if(idx >= size) return;
      arr[idx].rating = rating;
      if(parent(idx)!=-1&&arr[parent(idx)].compareTo(arr[idx])<0){
        goUp(idx);
      }else{
        goDown(idx);
      }
      return;
    }

    private void goUp(int curr){
      int n = 0;
      while(true){
        n = parent(curr);
        if(n == -1) break;
        if(arr[n].compareTo(arr[curr])<0) {
          swapFcr(n, curr);
        }else break;
        curr = n;
      }
    }

    private void goDown(int curr){
      int n = curr;
      while(true){
        int left = left(curr);
        if(left!=-1 && arr[left].compareTo(arr[n])>0){
          n = left;
        }
        int right = right(curr);
        if(right!=-1 && arr[right].compareTo(arr[n])>0){
          n = right;
        }
        if(n == curr) break;
        swapFcr(curr, n);
        curr = n;
      }
    }

    private int parent(int idx){
      if(idx == 0) return -1;
      return (idx-1)/2;
    }

    private int left(int idx){
      return (idx*2+1)>=size?-1:(idx*2+1);
    }

    private int right(int idx){
      return (idx*2+2)>=size?-1:(idx*2+2);
    }

    private void swapFcr(int idx1, int idx2){
      FCR tmp = arr[idx1];
      arr[idx1] = arr[idx2];
      arr[idx2] = tmp;

      arr[idx1].idx = idx1;
      arr[idx2].idx = idx2;
    }

  }

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    this.foodMap = new HashMap<>();
    this.cuisineMap = new HashMap<>();
    Map<String, Integer> tmp = new HashMap<>();
    for(String cuisine: cuisines){
      tmp.putIfAbsent(cuisine, 0);
      tmp.put(cuisine, tmp.get(cuisine)+1);
    }
    for(int i=0; i<foods.length; i++){
      FCR fcr = new FCR(foods[i], cuisines[i], ratings[i]);
      foodMap.put(foods[i], fcr);
      cuisineMap.putIfAbsent(cuisines[i], new Heap(tmp.get(cuisines[i])+2));
      Heap cuisineHeap = cuisineMap.get(cuisines[i]);
      fcr.idx = cuisineHeap.size;
      cuisineHeap.insert(fcr);
    }

  }

  public void changeRating(String food, int newRating) {
    FCR fcr = foodMap.get(food);
    cuisineMap.get(fcr.cuisine).updateRating(fcr.idx, newRating);
  }

  public String highestRated(String cuisine) {
    return cuisineMap.get(cuisine).getMax();
  }
}

class FoodRatingsBetter {
  private static class CR{
    String cuisine;
    int rating;
    public CR(String cuisine, int rating){
      this.cuisine = cuisine;
      this.rating = rating;
    }

    @Override
    public String toString() {
      return "CR{" +
          "cuisine='" + cuisine + '\'' +
          ", rating=" + rating +
          '}';
    }
  }
  Map<String, CR> foodMap;
  Map<String, TreeMap<Integer, TreeSet<String>>> cuisineMap;
//  Map<String, Integer> cToRMap;

  public FoodRatingsBetter(String[] foods, String[] cuisines, int[] ratings) {
    this.foodMap = new HashMap<>();
//    this.cToRMap = new HashMap<>();
    this.cuisineMap = new HashMap<>();
    for(int i=0; i<foods.length; i++){
      String food = foods[i], cuisine = cuisines[i];
      int rating = ratings[i];

      CR cr = new CR(cuisine, rating);
      foodMap.put(food, cr);

      cuisineMap.putIfAbsent(cuisine, new TreeMap<>());
      cuisineMap.get(cuisine).putIfAbsent(rating, new TreeSet<>());
      cuisineMap.get(cuisine).get(rating).add(food);

//      if(cToRMap.get(cuisine) == null || cToRMap.get(cuisine)<rating) cToRMap.put(cuisine, rating);
    }

  }

  public void changeRating(String food, int newRating) {
    CR cr = foodMap.get(food);
    Map<Integer, TreeSet<String>> cm = cuisineMap.get(cr.cuisine);

    cm.get(cr.rating).remove(food);
    if(cm.get(cr.rating)!=null && cm.get(cr.rating).isEmpty()) cm.remove(cr.rating);

    cr.rating = newRating;

    cm.putIfAbsent(newRating, new TreeSet<>());
    cm.get(newRating).add(food);
  }

  public String highestRated(String cuisine) {
    return cuisineMap
        .get(cuisine)
        .lastEntry().getValue()
        .first();
  }
}

public class DesignFoodRatingSystem {
  public static void main(String[] args) {
//    FoodRatings foodRatings = new FoodRatings(new String[]{"x","wf","j","u","a","q","wz","i"}, new String[]{"l","l","l","l","l","l","l","l"}, new int[]{8,6,1,17,20,2,17,14});
//    foodRatings.changeRating("wf",17);
//    foodRatings.changeRating("a",9);
//
//    foodRatings.changeRating("u",10);
//
//    foodRatings.changeRating("x",11);
//    foodRatings.changeRating("i",15);
//    foodRatings.changeRating("a",10);
//    foodRatings.changeRating("wf",10);
//    foodRatings.changeRating("x",16);
//    foodRatings.changeRating("i",12);
//    foodRatings.changeRating("a",16);
//
//    foodRatings.changeRating("wz",12);
//    FoodRatings foodRatings1 = new FoodRatings(new String[]{"s","d","j","a","v"}, new String[]{"yrfziuszqu","yrfziuszqu","yrfziuszqu","yrfziuszqu","yrfziuszqu"}, new int[]{12,19,13,12,1});
//    foodRatings1.changeRating("a", 17);
//    foodRatings1.changeRating("d", 9);
//    foodRatings1.changeRating("v", 4);

    FoodRatingsBetter foodRatings2 = new FoodRatingsBetter(new String[]{"x","wf","j","u","a","q","wz","i"}, new String[]{"l","l","l","l","l","l","l","l"}, new int[]{8,6,1,17,20,2,17,14});

    foodRatings2.changeRating("wf",17);
    foodRatings2.changeRating("a",9);

    foodRatings2.changeRating("u",10);

    foodRatings2.changeRating("x",11);
    foodRatings2.changeRating("i",15);
    foodRatings2.changeRating("a",10);
    foodRatings2.changeRating("wf",10);
    foodRatings2.changeRating("x",16);
    foodRatings2.changeRating("i",12);
    foodRatings2.changeRating("a",16);

    foodRatings2.changeRating("wz",12);
  }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */