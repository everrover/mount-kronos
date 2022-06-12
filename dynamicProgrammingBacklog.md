## Chill baby with DP - [Source](https://leetcode.com/discuss/study-guide/662866/DP-for-Beginners-Problems-or-Patterns-or-Sample-Solutions)

As some folks requested to list down good Dynamic Programming problems to start practice with. So, I am listing down them below and dividing them into different DP problem pattern. Problem listed in group follow a particular pattern and similar approach to solve them. I have tried to list those which till now I have solved, might have missed a few as well. Mostly are LC Medium problems and few LC Hard ones.
Beginner folks can wish to look at solutions listed below of particular pattern and can try to solve the other problem themselves.

[Full problem list](https://leetcode.com/list/x1k8lxi5)

**Longest Increasing Subsequence variants:**
https://leetcode.com/problems/longest-increasing-subsequence/
https://leetcode.com/problems/largest-divisible-subset/
https://leetcode.com/problems/russian-doll-envelopes/
https://leetcode.com/problems/maximum-length-of-pair-chain/
https://leetcode.com/problems/number-of-longest-increasing-subsequence/
https://leetcode.com/problems/delete-and-earn/
https://leetcode.com/problems/longest-string-chain/

**Partition Subset:**
https://leetcode.com/problems/partition-equal-subset-sum/
https://leetcode.com/problems/last-stone-weight-ii/

**BitMasking:**
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

**Longest Common Subsequence Variant:**
https://leetcode.com/problems/longest-common-subsequence/
https://leetcode.com/problems/edit-distance/
https://leetcode.com/problems/distinct-subsequences/
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/

**Palindrome:**
https://leetcode.com/problems/palindrome-partitioning-ii/
https://leetcode.com/problems/palindromic-substrings/

**Coin Change variant:**
https://leetcode.com/problems/coin-change/
https://leetcode.com/problems/coin-change-2/
https://leetcode.com/problems/combination-sum-iv/
https://leetcode.com/problems/perfect-squares/
https://leetcode.com/problems/minimum-cost-for-tickets/

**Matrix multiplication variant:**
https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
https://leetcode.com/problems/burst-balloons/

**Matrix/2D Array:**
https://leetcode.com/problems/matrix-block-sum/
https://leetcode.com/problems/range-sum-query-2d-immutable/
https://leetcode.com/problems/dungeon-game/
https://leetcode.com/problems/triangle/
https://leetcode.com/problems/maximal-square/
https://leetcode.com/problems/minimum-falling-path-sum/

**Hash + DP:**
https://leetcode.com/problems/target-sum/
https://leetcode.com/problems/longest-arithmetic-sequence/
https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

**State machine:**
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

**Depth First Search + DP:**
https://leetcode.com/problems/out-of-boundary-paths/
https://leetcode.com/problems/knight-probability-in-chessboard/

**Minimax DP:**
https://leetcode.com/problems/predict-the-winner/
https://leetcode.com/problems/stone-game/

**Misc:**
https://leetcode.com/problems/greatest-sum-divisible-by-three/
https://leetcode.com/problems/decode-ways/
https://leetcode.com/problems/perfect-squares/
https://leetcode.com/problems/count-numbers-with-unique-digits/
https://leetcode.com/problems/longest-turbulent-subarray/
https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

---

Sample solutions for each of above problem type:

---

**Longest Increasing Subsequence**
https://leetcode.com/problems/longest-increasing-subsequence/
https://leetcode.com/problems/largest-divisible-subset/
https://leetcode.com/problems/russian-doll-envelopes/
https://leetcode.com/problems/maximum-length-of-pair-chain/
https://leetcode.com/problems/number-of-longest-increasing-subsequence/
https://leetcode.com/problems/delete-and-earn/
https://leetcode.com/problems/longest-string-chain/

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {

		int n = nums.size();

		vector<int>LIS(n+1, 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j])
					LIS[i] = max(LIS[i], 1 + LIS[j]);
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans = max(ans, LIS[i]);
		}

		return ans;
	}
};
```

**Partition Subset Sum:**
https://leetcode.com/problems/partition-equal-subset-sum/
https://leetcode.com/problems/last-stone-weight-ii/

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {

		int n = nums.size();

		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += nums[i];

		if (sum % 2 != 0) return false;

		int target = sum/2;
		vector<bool>dp(target+1, false);

		dp[0] = true;
		for (int i = 0; i < n; i++) {
			for (int j = target; j >= nums[i]; j--) {
				dp[j] = dp[j] | dp[j - nums[i]];
			}
		}

		return dp[target];
	}
};
```

**BitMasking in DP:**
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

```
class Solution {
int dp[(1<<16) + 2];
public:
bool canPartitionKSubsets(vector<int>& nums, int k) {

		int n = nums.size();

		fill(dp, dp+(1<<16)+2, -1);

		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += nums[i];

		if (sum % k != 0) return false;

		int target = sum/k;

		dp[0] = 0;
		for (int mask = 0; mask < (1<<n); mask++) {
			if (dp[mask] == -1) continue;
			for (int i = 0; i < n; i++) {
				if (!(mask & (1 << i)) && dp[mask] + nums[i] <= target)
					dp[mask | (1 << i)] = (dp[mask] + nums[i]) % target;
			}
		}

		return dp[(1<<n)-1] == 0;
	}
};
```

**Longest Common Subsequence**
https://leetcode.com/problems/longest-common-subsequence/
https://leetcode.com/problems/edit-distance/
https://leetcode.com/problems/distinct-subsequences/
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/

```cpp
class Solution {
    int longestCommonSubsequenceUtil(string text1, string text2, int n, int m) {
        if (n == 0 || m == 0)
            return 0;

		vector<vector<int>>L(n+1, vector<int>(m+1, 0));

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 || j == 0)
					L[i][j] = 0;
				else if (text1[i-1] == text2[j-1])
					L[i][j] = 1 + L[i-1][j-1];
				else
					L[i][j] = max(L[i][j-1], L[i-1][j]);
			}
		}

		return L[n][m];
	}

public:
    int longestCommonSubsequence(string text1, string text2) {
        int n = text1.size();
        int m = text2.size();

		return longestCommonSubsequenceUtil(text1, text2, n, m);
	}
};
```

**Palindrome:**
https://leetcode.com/problems/palindrome-partitioning-ii/
https://leetcode.com/problems/palindromic-substrings/

```cpp
class Solution {
public:
    int minCut(string s) {

		int n = s.length();

		int res[n];
		bool P[n][n];

		for (int i = 0; i < n; i++)
			P[i][i] = true;


		for (int L = 2; L <= n; L++) {
			for (int i = 0; i < n-L+1; i++) {
				int j = i+L-1;

				if (L == 2) {
					P[i][j] = (s[i] == s[j]);
				} else {
					P[i][j] = (s[i] == s[j]) && P[i+1][j-1];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (P[0][i])
				res[i] = 0;
			else {
				res[i] = INT_MAX;
				for (int j = 0; j < i; j++) {
					if (P[j+1][i] && res[i] > 1 + res[j])
						res[i] = 1+res[j];
				}
			}
		}

		return res[n-1] == INT_MAX ? 1 : res[n-1];
	}
};
```

**Coin Change:**
https://leetcode.com/problems/coin-change/
https://leetcode.com/problems/coin-change-2/
https://leetcode.com/problems/combination-sum-iv/
https://leetcode.com/problems/perfect-squares/
https://leetcode.com/problems/minimum-cost-for-tickets/

```cpp
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {

		int n = coins.size();
		if (n == 0) return 0;

		vector<int>res(amount+1, INT_MAX);

		res[0] = 0;

		for (int i = 0; i < n; i++) {
			for (int j =  coins[i]; j <= amount; j++) {
				if (res[j-coins[i]] != INT_MAX)
					res[j] = min(res[j], 1+res[j-coins[i]]);
			}
		}

		return res[amount] != INT_MAX ? res[amount] : -1;
	}
};
```

**Matrix multiplication:**
https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
https://leetcode.com/problems/burst-balloons/

```cpp
class Solution {
public:
int minScoreTriangulation(vector<int>& A) {

		int n = A.size();        
		vector<vector<int>>dp(n, vector<int>(n, 0));

		for (int L = 2; L <= n; L++) {
			for (int i = 0; i+L < n; i++) {
				int j = i+L;
				dp[i][j] = INT_MAX;
				for (int k = i+1; k < j; k++) {
					dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + A[i]*A[k]*A[j]);
				}
			}
		}

		return dp[0][n-1];
	}
};
```

**Matrix/2D Array:**
https://leetcode.com/problems/matrix-block-sum/
https://leetcode.com/problems/range-sum-query-2d-immutable/
https://leetcode.com/problems/dungeon-game/
https://leetcode.com/problems/triangle/
https://leetcode.com/problems/maximal-square/
https://leetcode.com/problems/minimum-falling-path-sum/

```cpp
class Solution {
public:
    vector<vector<int>> matrixBlockSum(vector<vector<int>>& mat, int K) {


		int m = mat.size();
		int n = mat[0].size();

		vector<vector<int>>sum(m+1, vector<int>(n+1, 0));
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
			}
		}

		vector<vector<int>>res(m, vector<int>(n, 0));
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int r1 = max(0, i-K); int c1 = max(0, j-K);
				int r2 = min(m-1, i+K); int c2 = min(n-1, j+K);
				r1++; r2++;
				c1++; c2++;
				res[i][j] = sum[r2][c2] - (sum[r2][c1-1] + sum[r1-1][c2]- sum[r1-1][c1-1]);
			}
		}

		return res;
	}
};
```

**Hash + DP:**
https://leetcode.com/problems/target-sum/
https://leetcode.com/problems/longest-arithmetic-sequence/
https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int S) {
        int n = nums.size();
        unordered_map<int, int>hm;

		hm[0] = 1;
		for (int i = 0; i < n; i++) {
			auto mp = hm;
			hm.clear();

			for (auto it = mp.begin(); it != mp.end(); it++) {
				hm[it->first + nums[i]] += it->second;
				hm[it->first - nums[i]] += it->second;

			}
		}

		return hm[S];
	}
};
```

**State machine:**
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {

		int n = prices.size();
		vector<int>buy(n, 0);
		vector<int>sell(n, 0);

		buy[0] = -prices[0], sell[0] = 0;
		for (int i = 1; i < n; i++) {
			buy[i] = max(buy[i-1], sell[i-1]-prices[i]);
			sell[i] = max(sell[i-1], buy[i-1]+prices[i]-fee);
		}

		return sell[n-1];
	}
};
```
**Depth First Search +DP:**
https://leetcode.com/problems/out-of-boundary-paths/
https://leetcode.com/problems/knight-probability-in-chessboard/

```cpp
class Solution {
    int mod = 1000000007;
    int dfs(int m, int n, int N, int r, int c,  vector<vector<vector<int>>>& dp) {
        if (r < 0 || c < 0 || r >= m || c >= n) return 1;
        if (N == 0) return 0;
        if (dp[N][r][c] != -1) return dp[N][r][c]%mod;

		int moves = 0;
		moves =  (moves + dfs(m, n, N-1, r, c+1, dp))%mod;
		moves =  (moves + dfs(m, n, N-1, r, c-1, dp))%mod;
		moves =  (moves + dfs(m, n, N-1, r+1, c, dp))%mod;
		moves =  (moves + dfs(m, n, N-1, r-1, c, dp))%mod;

		dp[N][r][c] = moves%mod;
		return dp[N][r][c];
	}
public:
    int findPaths(int m, int n, int N, int i, int j) {
        vector<vector<vector<int>>>dp(N+1, vector<vector<int>>(m+1, vector<int>(n+1, -1)));
        return dfs(m, n, N, i, j, dp);
    }
};
```

**Minimax DP:**
https://leetcode.com/problems/predict-the-winner/
https://leetcode.com/problems/stone-game/

```cpp
class Solution {
public:
    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();

		int res[n][n];

		for (int i = 0; i < n; i++)
			res[i][i] = nums[i];

		for (int l = 2; l <= n; l++) {
			for (int i = 0; i+l-1 < n; i++) {
				int j = i+l-1;
				int a = (i+1 <= j-1) ? res[i+1][j-1] : 0;
				int b = (i+2 <= j) ? res[i+2][j] : 0;
				int c = (i <= j-2) ? res[i][j-2] : 0;

				res[i][j] = max(nums[i] + min(a,b), nums[j] + min(a, c));
			}
		}

		int total = 0;
		for (int i = 0; i < n; i++)
			total += nums[i];

		return res[0][n-1] >= total - res[0][n-1];
	}
};
```

**Miscellaneous:**
https://leetcode.com/problems/greatest-sum-divisible-by-three/
https://leetcode.com/problems/decode-ways/
https://leetcode.com/problems/count-numbers-with-unique-digits/
https://leetcode.com/problems/longest-turbulent-subarray/
https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

Please point out issues in solutions if you find any. There might be better approaches in few.

## The approach of one guy... same as mine

I used to solve DP problems after DP problems without really it clicking in my head. I would see a solution and wonder "how the heck did they come with that solution..."

Here is my practical guide to solving DP problems.

The order of solving a dp problem should be 
1) come up with a recurrence relation first 
2) code it up. The first part only needs a pen and paper.
3) When coming up with a recurrence relation, separately come up with a general case and base cases.
   - 2.1 when coming up with a general case, assign well defined English meaning to your dp term, dp[...][...][...]... e.g. for https://leetcode.com/problems/longest-increasing-subsequence/, dp[i] = the length of the longest increasing subsequence that ENDS on element i.
   - 2.2 once you define your meaning, express the answer to the original problem in terms of your dp terms. If you can't do this, then it means your dp definition likely lacks certain necessary information. e.g. for longest increasing subsequence, ans == max(dp[i]) for 0 <= i < n, n == length of an input array (because you don't know where an optimal solution ends)
   - 2.3 base cases are usually straightforwardly defined if you follow the top-down approach. i.e. the conditions in which your recursive function ends. If your recursive function is stuck in an infinite loop, you are probably missing a base case (though it is possible your general case is messed up and creates a cycle. see my point 4)
4) People usually prefer either the top-down or the bottom-up style. Stick with one or the other and always code in some template. Then, writing code is a matter of translating your recurrence relation. This way, you avoid mistakes.
5) At its core, DP problems are really graph problems. Here each dp[...][...][...] terms are nodes and edges are drawn based on recurrence relations. For your problem to have a DP solution, this graph MUST BE a DAG. This is usually what textbooks mean "optimal substructure". For me, this connection of DP to its graph representation greatly helped.
6) Once you "get" the solid understanding, then solve problems to find and internalize common patterns.

To me, the practice of 2.1 and understanding of 4 were the tipping point. After that, DP became really easy for me and a weapon to solve many problems, that even have other solutions (usually greedy, which is harder to come up with)