package SixtyFour.UberOA1;

import java.util.*;

public class Solution {

    private int subsequenceOfAInRepeatedB(String A, String B){
        /**
         * Constraints allow us to keep limited number of BST's for each character for storing indexes.
         * For each char in B find the successor of current `cursor`(init with 0). Whenever no successor is found, we reset cursor to `0` and find successor again.
         * If again not found, means character is absent from target string and subsequence can't be generated.
         */
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        for(char a='a'; a<='z'; a++){
            map.put(a, new TreeSet<>());
        }
        for(int i=0; i<A.length(); i++){
            map.get(A.charAt(i)).add(i);
        }
        int curr = 0;
        int iterations = 0;
        for(char a: B.toCharArray()){
            TreeSet<Integer> bst = map.get(a);
            Integer next = bst.ceiling(curr);
            if(next == null){ // hv to pass around the string
                next = bst.ceiling(0);
                if(next == null) return -1;
                iterations++;
            }
            curr = next+1;
        }
        return iterations*A.length()+curr;
    }

    private static class FarmerJohn{

        private void modifyCrop(int i, int cap){

        }

        private void waterCrops(int l, int r, int w){

        }

        private int damagedCropCount(int l, int r){
            return 0;
        }

        public void eventStreamProcessor(Integer ...args){
            if(args[0] == 1){
                int i = args[1], c = args[2];
            }else if(args[0] == 2){
                int l = args[1], r = args[2], w = args[3];
                // damaged crop count
                System.out.println(damagedCropCount(l, r));
            }
        }

        public void createDummyEvents(){

        }
    }

    private int orderSum(int n) {
        /**
         * Well, the largest difference pair is 1 and n. That's what we take in the beginning in our result.
         * The 3rd element will be 2 since it returns the next maximum difference with n after 1... then (n-1) and so on.
         * The series becomes 1, n, 2, n-1, 3, n-2, ...
         * The absolute difference series is (n-1), (n-2), (n-3), (n-4), ... 1.
         */
        return n*(n-1)/2;
    }

    private static class Item implements Comparable<Item>{
        public int beauty, cost;

        public Item(int beauty, int cost){
            this.beauty = beauty;
            this.cost = cost;
        }

        @Override
        public int compareTo(Item item) {
            return (item.cost!=this.cost)? (item.cost-this.cost): (item.beauty-this.beauty);
        }
    }

    public int binarySearch(List<Item> items, int val, int l, int r){
        int mid = -1, ll = l;
        while(l<=r){            mid = (l+r)/2;
            if(items.get(mid).cost>val){ // `12` 11 `10` 9 8 6 5 3 `2` 1
                l = mid+1;
            }else if(items.get(mid).cost < val){
                if((mid-1) >= ll && items.get(mid-1).cost > val) return mid;
                else if(mid == ll) return mid;
                r = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    private int coinsAndDiamonds(int [][]fountains, int C, int D){
        List<Item> diamondItems = new ArrayList<>();
        List<Item> coinItems = new ArrayList<>();
        int maxDiamond = 0, maxCoin = 0;
        for(int []fountain: fountains) {
            if(fountain[2] == 2) {
                if(fountain[1] <= D){
                    diamondItems.add(new Item(fountain[0], fountain[1]));
                    maxDiamond = Math.max(maxDiamond, fountain[1]);
                }
            }
            else {
                if(fountain[1] <= C) {
                    coinItems.add(new Item(fountain[0], fountain[1]));
                    maxCoin = Math.max(maxCoin, fountain[1]);

                }
            }
        }
        Collections.sort(coinItems);
        Collections.sort(diamondItems);

        int maxBeauty = (maxCoin==0||maxDiamond==0)?0:(maxCoin+maxDiamond);

        for(int i=0; i<coinItems.size(); i++){
            Item item = coinItems.get(i);
            int idx = binarySearch(coinItems, C-item.cost, i+1, coinItems.size()-1);
            if(idx != -1){
                maxBeauty = Math.max(maxBeauty, item.beauty+coinItems.get(idx).beauty);
            }
        }
        for(int i=0; i<diamondItems.size(); i++){
            Item item = diamondItems.get(i);
            int idx = binarySearch(diamondItems, D-item.cost, i+1, diamondItems.size()-1);
            if(idx != -1){
                maxBeauty = Math.max(maxBeauty, item.beauty+diamondItems.get(idx).beauty);
            }
        }

        return maxBeauty;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.subsequenceOfAInRepeatedB("contest", "son"));
//        System.out.println(solution.orderSum(50));
        System.out.println(solution.coinsAndDiamonds(new int[][]{
            {1, 18, 2},
            {6, 16, 2},
            {11, 16, 2},
            {7, 23, 2},
            {16, 30, 2},
            {2, 20, 2},
        }, 68, 40));
    }
}

/**
 6 68 40
 {1, 18, 2}
 {6, 16, 2}
 {11, 16, 2}
 {7, 23, 2}
 {16, 30, 2}
 {2, 20, 2}
 */