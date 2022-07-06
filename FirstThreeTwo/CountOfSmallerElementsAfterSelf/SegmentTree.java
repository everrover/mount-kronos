package FirstThreeTwo.CountOfSmallerElementsAfterSelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentTree {
    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000;
        int size = 2 * 10000 + 1;
        int[] tree = new int[size * 2];
        List<Integer> result = new ArrayList<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller_count = query(0, nums[i] + offset, tree, size);
            result.add(smaller_count);
            update(nums[i] + offset, 1, tree, size);
        }
        Collections.reverse(result);
        return result;
    }

    private void update(int index, int value, int[] tree, int size) {
        index += size;
        tree[index] += value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    private int query(int left, int right, int[] tree, int size) {
        // return sum of [left, right)
        int result = 0;
        left += size;
        right += size;
        while (left < right) {
            if (left % 2 == 1) {
                result += tree[left];
                left++;
            }
            left /= 2;
            if (right % 2 == 1) {
                right--;
                result += tree[right];
            }
            right /= 2;
        }
        return result;
    }
}