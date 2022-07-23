package SixtyFour.NumberContainerSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class NumberContainers {
    /**
     * https://leetcode.com/problems/design-a-number-container-system/
     * Have used a BST(using treeset) for storing all indices for a given number. These details are stored within a HashMap.`Have used HashMap for storing index->number pairs.
     * Rest of implementation is just for combining these two.
     */

    private Map<Integer, TreeSet<Integer>> numIndexMap;
    private Map<Integer, Integer> indexNumMap;
    public NumberContainers() {
        this.numIndexMap = new HashMap<>();
        this.indexNumMap = new HashMap<>();
    }

    public void change(int index, int number) {
        // op for num->index map
        if(indexNumMap.containsKey(index)){
            int prevNumber = indexNumMap.get(index);

            numIndexMap.get(prevNumber).remove(index);
            if(numIndexMap.get(prevNumber).size() == 0) numIndexMap.remove(prevNumber);
        }
        indexNumMap.put(index, number);

        if(!numIndexMap.containsKey(number)) numIndexMap.put(number, new TreeSet<>());
        numIndexMap.get(number).add(index);
    }

    public int find(int number) {
        if(numIndexMap.containsKey(number)){
            return numIndexMap.get(number).first();
        }
        return -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */