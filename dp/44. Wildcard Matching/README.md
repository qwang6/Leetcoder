* 用DFS + Memorization
* 也可用DP的解法
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        Boolean[][] dp = new Boolean[m+1][n+1];
        dp[0][0] = true;
        return helper(s, p, 0, 0, dp);
    }
    private boolean helper(String s, String p, int i, int j, Boolean[][] dp) {
        int m = s.length(), n = p.length();
        if (i >= m && j >= n) return true;
        if(i < s.length() && j >= p.length()){
               return false;
        } else if(i >= s.length() && j < p.length()){
            if(p.charAt(j) != '*'){
                return false;
            }
            return helper(s, p, i, j+1, dp);
        }

        if (dp[i+1][j+1] != null) {
            return dp[i+1][j+1];
        }
        char c1 = s.charAt(i);
        char c2 = p.charAt(j);
        boolean res = false;
        if (c1 == c2 || c2 == '?') {
            res = helper(s, p, i+1, j+1, dp);
        } else if (c2 == '*') {
            res = helper(s, p, i+1, j, dp) || helper(s, p, i, j+1, dp);
        }
        dp[i+1][j+1] = res;
        return res;
    }
}
```

DP 解法：
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') dp[0][i+1] = true;
            else break;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = s.charAt(i);
                char c2 = p.charAt(j);
                if (c1 == c2 || c2 == '?') {
                    dp[i+1][j+1] = dp[i][j];
                } else if (c2 == '*') {
                    dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }
        return dp[m][n];
    }
}
```