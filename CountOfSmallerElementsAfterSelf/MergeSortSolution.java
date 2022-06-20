package CountOfSmallerElementsAfterSelf;

import java.util.ArrayList;
import java.util.List;

public class MergeSortSolution {
    private static int[] numbers;
    private static int[] result;
    private static int[] indexes;
    public List<Integer> countSmaller(int[] nums) {
        result = new int[nums.length];
        numbers = nums;
        indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergeSort(0, nums.length);
        List<Integer> res = new ArrayList<Integer>();
        for (int i : result) {
            res.add(i);
        }
        return res;
    }

    private void mergeSort(int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid, right);
        merge(left, right, mid);
    }

    private void merge(int left, int right, int mid) {
        int i = left;
        int j = mid;
        List<Integer> temp = new ArrayList<Integer>();
        while (i < mid && j < right) {
            if (numbers[indexes[i]] <= numbers[indexes[j]]) {
                result[indexes[i]] += j - mid;
                temp.add(indexes[i]);
                i++;
            } else {
                temp.add(indexes[j]);
                j++;
            }
        }
        while (i < mid) {
            result[indexes[i]] += j - mid;
            temp.add(indexes[i]);
            i++;
        }
        while (j < right) {
            temp.add(indexes[j]);
            j++;
        }
        // restore from temp
        for (int k = left; k < right; k++) {
            indexes[k] = temp.get(k - left);
        }
    }
}
