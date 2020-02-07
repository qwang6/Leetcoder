class Solution {
    // 解法一
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;
        
        int n = s.length();
        boolean[][] dp = new boolean[n+1][n+1];
        dp[0][0] = true;
        int start = 0, end = 1, maxLen = 1;
        for (int i = 0; i < n; i++) dp[i+1][i+1] = true;
        
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n-l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i+1 == j || dp[i+1][j-1]) {
                        dp[i][j] = true;
                        int len = j - i + 1;
                        if (len > maxLen) {
                            maxLen = len;
                            start = i;
                            end = j+1;
                        }
                    } 
                } 
            }
        }
        return s.substring(start, end);
    }
    
    // 解法二
    private int max = 1, start = 0, end = 1;
    
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;
        
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i+1);
        }
        return s.substring(start, end);
    }
    private void helper(String s, int l, int r) {
        int res = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            res = r - l + 1;
            if (res > max) {
                max = res;
                start = l;
                end = r + 1;
            }
            l--;
            r++;
        }
    }
}