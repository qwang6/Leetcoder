经典的背包问题

### dp solution
```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] >= amount+1 ? -1 : dp[amount];
    }
}
```

### greedy + search
```java
class Solution {
    private int res = Integer.MAX_VALUE;
    
    public int coinChange(int[] coins, int amount) {
        Integer[] c = new Integer[coins.length];
        for (int i = 0; i < coins.length; i++) c[i] = coins[i];
        Arrays.sort(c, Collections.reverseOrder());
        
        dfs(c, 0, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private void dfs(Integer[] coins, int i, int amount, int count) {
        if (i >= coins.length || amount < 0) return;
        
        int coin = coins[i];
        if (amount % coin == 0) {
            res = Math.min(res, count + amount/coin);
        } else {
            for (int k = amount/coin; k>=0 && k+count < res; k--) {
                dfs(coins, i+1, amount-k*coin, count + k);
            }
        }
    }
}
```