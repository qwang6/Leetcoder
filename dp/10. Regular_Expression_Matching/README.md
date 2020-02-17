这道题和44题Wildcard Matching很类似，但是这道题目比44题要难。


```
dp[i][j]: 代表source前i个字符是否与pattern的前j个字符匹配。
dp的长度一般是n+1，原因是要padding一位这样可以是的下标从1开始。而且经常dp[0]要设置一个初始值，代表在什么都没有的情况的值为多少。
返回值是dp[m][n]，其中m为souce的长度，n为pattern的长度。
/**
    1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
    2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
    3, If p.charAt(j) == '*': 
       here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    */
```

```java
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                // assume the '*' and the previous character as empty
                dp[0][i+1] = dp[0][i-1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = s.charAt(i);
                char c2 = p.charAt(j);
                if (c1 == c2 || c2 == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }  else if (c2 == '*') {
                    if (s.charAt(i) != p.charAt(j-1) && p.charAt(j-1) != '.') {
                        // // assume the '*' and the previous character as empty
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = dp[i][j+1]   // a* counts as multiple a
                                    || dp[i+1][j-1] // a* counts as empty
                                    || dp[i+1][j];  // a* counts as single a
                    }
                }
            }
        }
        return dp[m][n];
    }
```
