```java
class Solution {
    /**
    dp[c][k] is the prob of tossing c first coins and get k faced up.
    dp[c][k] = dp[c - 1][k] * (1 - p) + dp[c - 1][k - 1] * p)
    where p is the prob for c-th coin.
    */
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1.0;
        for (int i = 0; i < prob.length; ++i)
            for (int k = Math.min(i + 1, target); k >= 0; --k)
                dp[k] = (k > 0 ? dp[k - 1] : 0) * prob[i] + dp[k] * (1 - prob[i]);
        return dp[target];
    }
}
```