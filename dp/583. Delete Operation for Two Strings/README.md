这个题目是Longest Common Subsequence的变种。找到LCS后，两个字符串的长度分别减去LCS的长度就是需要delete的字符的个数。
```java
class Solution {
    public int minDistance(String word1, String word2) {
        // To make them identical, just find the longest common subsequence.
        // The rest of the characters have to de deleted from the both the strings,
        // which does not belong to longest common subsequence.
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else {
                    dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1)
                            ? dp[i - 1][j - 1] + 1 
                            : Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int val = dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }
}
```