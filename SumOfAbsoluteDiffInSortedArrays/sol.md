# [1685. Sum of Absolute Differences in a Sorted Array](https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/)

For every element at `i`,
```
res[i] = sum(abs(nums[i]-nums[j])), 0<=j<nums.length
       = sum(max(nums[i],nums[j])-min(nums[i], nums[j])), 0<=j<nums.length
       = (count of elements smaller than `i`)*nums[i] - (sum of elements smaller than nums[i])
        + (sum of elements larger than nums[i]) - (count of elements larger than `i`)*nums[i]

For 0-indexed array,
count of elements smaller than `i` = i-1
count of elements larger than `i` = nums.length-i-1
```