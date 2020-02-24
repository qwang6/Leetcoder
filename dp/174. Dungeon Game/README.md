参考花花的视频：https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-174-dungeon-game/

```java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] d = dungeon;
        int[][] dp = new int[m+1][n+1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        
        dp[m][n-1] = 1;
        dp[m-1][n] = 1;
        
        for (int i = m-1; i>=0; i--) {
            for (int j = n-1; j>=0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1])-d[i][j]);
            }
        }
        return dp[0][0];
    }
}
```