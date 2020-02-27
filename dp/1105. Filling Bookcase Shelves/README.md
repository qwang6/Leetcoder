属于一维的dp题目，从中间找一个点，左边的是已经求解过的，右边一部分是当前步已知的部分。
```java
class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            int[] b = books[j-1];
            int w = 0, h = 0;
            for (int i = j; i > 0; i--) {
                w += books[i-1][0];
                if (w > shelf_width) break;
                h = Math.max(h, books[i-1][1]);
                dp[j] = Math.min(dp[j], dp[i-1] + h);
            }
        }
        return dp[n];
    }
}
```