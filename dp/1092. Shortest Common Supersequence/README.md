利用Longest Common Sequence，先求出最长相同子序列的长度，再从后往前反推回去。反推的时候需要注意的一点是，去斜对角线的条件是s1.charAt(i)==s2.charAt(j)，而不能用dp[i+1][j+1]==dp[i][j]+1，这是因为当后者满足时不一定前者满足，但是前者满足的时候后者肯定满足。

```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // first compute the longest common subsequence
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = str1.charAt(i);
                char c2 = str2.charAt(j);
                if (c1 == c2) {
                    dp[i+1][j+1] = 1 + dp[i][j];
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        // second reconstruct the string using dp array
        StringBuilder sb = new StringBuilder();
        int i = m-1, j = n-1;
        while (i >= 0 && j >= 0) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(j);
            if (c1 == c2) {
                sb.append(c1);
                i--;
                j--;
            } else {
                if (dp[i+1][j+1] == dp[i][j+1]) {
                    sb.append(c1);
                    i--;
                } else if (dp[i+1][j+1] == dp[i+1][j]) {
                    sb.append(c2);
                    j--;
                }
            }
        }
        while (i >= 0) {
            sb.append(str1.charAt(i--));
        }
        while (j >= 0) {
            sb.append(str2.charAt(j--));
        }
        return sb.reverse().toString();
    }
}
```