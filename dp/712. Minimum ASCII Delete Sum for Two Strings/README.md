这题同Longest Common Subsequence和583. Delete Operation for Two Strings一样的解法。但是这题的初始化有所不同
* dp[i][j]代表: S1[i...]和S2[j...]的最小cost是多少
初始化时需要从后往前初始化，因为要求的是从右到左的最小值

```java
class Solution {
    // Let dp[i][j] be the answer to the problem for the strings s1[i:], s2[j:]
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = m-1; i >= 0; i--) {
            dp[i][n] = dp[i+1][n] + s1.codePointAt(i);
        }
        for (int j = n-1; j >= 0; j--) {
            dp[m][j] = dp[m][j+1] + s2.codePointAt(j);
        }
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i),
                                        dp[i][j+1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }
}
```