这是一个类01背包问题，这题采用滚动数组，并采用pull的模式，也就是说当前这一轮的值是取自上一轮的值.
* pull的方式: 当前是i+1轮，dp[i+1] += dp[i]
* push的方式: 当前是i轮，dp[i+1] += dp[i]
是pull还是push主要看当前操作是哪一轮。

```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (S > sum || S < -sum) return 0;
        
        int[] dp = new int[2*sum+1];
        int offset = sum;
        dp[offset] = 1;
        for (int num : nums) {
            int[] next = new int[2*sum+1];
            for (int i = 0; i< 2*sum+1; i++) {
                if (i+num>=0 && i+num<2*sum+1) next[i+num] += dp[i];
                if (i-num>=0 && i-num<2*sum+1) next[i-num] += dp[i];
            }
            dp = next;
        }
        return dp[offset+S];
    }
}
```