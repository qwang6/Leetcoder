```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int ans = 0;
        for (int lo = 0, hi = 0; hi < s.length(); ++hi) {
            ++count[s.charAt(hi) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // number of valid substrings all start from lo + 1
                // but end at hi, hi + 1, ..., s.length() - 1, respectively.
                ans += s.length() - hi; 
                --count[s.charAt(lo++) - 'a'];
            }
        } 
        return ans;        
    }
}
```