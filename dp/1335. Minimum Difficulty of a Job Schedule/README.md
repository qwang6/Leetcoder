这个题目是一道典型的二维DP。
dp[i][j]代表ith天，jth工作所需要的最少的difficulty
找一个中间点k，求dp[i-1][k-1]+min(max(k,j))
注意这题的初始化条件，当d只有一天的时候，dp[1][j]代表的是从0到j之间的最大的数。
getMax(int[] arr, int i, int j)这个函数，代表取得从j到i之间，从右到左的最大的值。
```java
class Solution {
    /**
    In the code, i represents i-th day, j represents j-th work, k represents k-th work.
    dp[i][j] means the minimum difficulty on i-th day who takes j-th work as its end.
    The critical part is, there are so many different schedules of work even if you know the j-th work will be the end of today's job.
    Let's try to make it clear. On i-th day, the minimum index of job we can take is i, because there should be at least one job done per previous day, which is i-1 in total.
    So k will traverse any work we can start on i-th day while we ends at j-th work. The range of k should be [i, j]. Each (k, j) pair means -- at i-th day, we start from k-th work and ends at j-th work. The difficulty will be the max value within these works. And we take that schedule with a minimum difficulty.
    */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n) return -1;
        
        int[] jobs = jobDifficulty;
        int[][] dp = new int[d+1][n+1];
        // if d is only one day, then the result should be the max in the jobDifficulty
        for (int i = 1; i <= n; i++) {
            dp[1][i] = Math.max(dp[1][i-1], jobs[i-1]);
        }
        for (int i = 2; i <= d; i++) { // start from day two
            for (int j = i; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                int[] max = getMax(jobs, i-1, j-1);
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1]+max[k-i]);
                }
            }
        }
        return dp[d][n];
    }
    // the max values from i to j, from right to left
    private int[] getMax(int[] jobs, int i, int j) {
        int n = j - i + 1;
        int[] max = new int[n];
        max[n-1] = jobs[j--];
        int k = n-2;
        while (k >= 0) {
            max[k] = Math.max(max[k+1], jobs[j--]);
            k--;
        }
        return max;
    }
}
```
