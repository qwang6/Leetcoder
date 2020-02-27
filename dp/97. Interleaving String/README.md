```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) return true;
        if (s1 == null && s2 != null) return s2.equals(s3);
        if (s1 != null && s2 == null) return s1.equals(s3);
        if (s1 != null && s2 != null && s3 == null) return false;
        if (s1.length() + s2.length() != s3.length()) return false;


        int m = s1.length(), n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        // 初始化
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i-1) ==s3.charAt(i-1)) {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int i = 1; i <= n; i++) {
            if (s2.charAt(i-1) == s3.charAt(i-1)) {
                dp[0][i] = dp[0][i-1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s1.charAt(i-1), c2 = s2.charAt(j-1), c3 = s3.charAt(i+j-1);
                dp[i][j] = (c1 == c3 && dp[i-1][j]) || (c2 == c3 && dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
}
```

Recursion + Memorization

```c++
class Solution {
public:
  bool isInterleave(string s1, string s2, string s3) {
    m_ = vector<vector<int>>(s1.length() + 1, vector<int>(s2.length() + 1, INT_MIN));
    return dp(s1, s1.length(), s2, s2.length(), s3, s3.length());
  }
private:
  // m_[i][j]: whehter s3[0:i+j] is a interleva of s1[0:i] and s2[0:j]
  vector<vector<int>> m_;
  
  bool dp(const string& s1, int l1, const string& s2, int l2, const string& s3, int l3) {
    if (l1 + l2 != l3) return false;
    if (l1 == 0 && l2 == 0) return true;
    if (m_[l1][l2] != INT_MIN) return m_[l1][l2];
    if (l1 > 0 & s3[l3 - 1] == s1[l1 - 1] && dp(s1, l1 - 1, s2, l2, s3, l3 - 1)
      ||l2 > 0 & s3[l3 - 1] == s2[l2 - 1] && dp(s1, l1, s2, l2 - 1, s3, l3 - 1))
      m_[l1][l2] = 1;
    else
      m_[l1][l2] = 0;
    return m_[l1][l2];
  }
};
```