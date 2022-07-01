# [2321. Maximum Score Of Spliced Array](https://leetcode.com/problems/maximum-score-of-spliced-array/)

Basically, we have to pick a range of elements in first and swap it with second. And via this op, _the difference in the sum of 
those two ranges is swapped_. So, I built an array(prefix sum) of these differences. Then I used Kadane's algorithm to find 
the sum subtracted(if range sum>0) or added(if range sum<0) from/to the first range(vice versa for the other).

Cheers mate. After very long an optimized trick clicked me at a competition. Happy... yay!!!!