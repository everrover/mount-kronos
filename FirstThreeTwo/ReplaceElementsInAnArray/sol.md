# [2295. Replace Elements in an Array](https://leetcode.com/problems/replace-elements-in-an-array/)

Because of the two provided conditions, a simple map is enough for us to trace transitions in numbers. Basically the map is 
between transitioned numbers and actual numbers within the array. 

If I were to uncomment the commented line (and remove the hash set traversal) time required for all ops to finish would 
increase. This would cause `O(ops.length)` updates in `nums` array. Otherwise, using the existing code, there would be `O(nums.length)` 
updates.