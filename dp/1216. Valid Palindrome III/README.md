这是一道很常规的DP题，求给定的字符串改变几个字符就可以把原字符串变为回文字符串。

dp[i][j]: 从i到j最少改变的个数

```java
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        Integer[][] cache = new Integer[s.length()][s.length()];
        return aux(s, 0, s.length()-1, cache) <= k;
    }
    
    private int aux(String s, int left, int right, Integer[][] cache) {
        if (right - left < 1) return 0;
        if (cache[left][right] != null) return cache[left][right];
        
        int step = 0;
        if (s.charAt(left) == s.charAt(right)) {
            step = aux(s, left+1, right-1, cache);
        } else {
            step = 1 + Math.min(aux(s, left+1, right, cache), aux(s, left, right-1, cache));
        }
        cache[left][right] = step;
        return step;
    }
}
```