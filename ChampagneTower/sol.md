# [799. Champagne Tower](https://leetcode.com/problems/champagne-tower/)]

_Simulation is the only way._

We could use a 2D matrix which represents the champagne tower. If in any glass there's more 
than one unit of the drink, we pass on `(amount-1)/2` to next layer to glass at `i`th and 
`i+1`th position. Otherwise, we pass on `0` to both glasses. 
i.e. for `i`th  glass in any layer we pour the calculated amount to `i+1`th and `i`th glass in next layer.

Since we only need to pour the liquid to `i+1` and `i`th glass, we can do our work in 1D array 
by performing iterations in reverse order for every layer.