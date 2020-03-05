* dp[i]: 从0到i之间的max sum
当新加入一个数字，从右往左看k个数，在i-k到k之间找一个最大的数字nums[j]
* dp[i] = max(dp[j] + nums[j])

```java
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        /**
        dp[i] from 0 to i the max sum
        */
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int curMax = 0;
            for (int k = 1; k <= Math.min(i, K); k++) {
                curMax = Math.max(curMax, A[i-k]);
                dp[i] = Math.max(dp[i], dp[i-k] + curMax*k);
            }
        }
        return dp[n];
    }
}

// class Solution {  
//     private Map<String, Integer> map = new HashMap<>();
    
//     public int maxSumAfterPartitioning(int[] A, int K) {
//         return helper(A, 0, K, 0);
//     }
//     private int helper(int[] A, int pos, int k, int sum) {
//         if (pos == A.length) {
//             return sum;
//         }
//         String key = pos + ">" + sum;
//         if (map.containsKey(key)) return map.get(key);
        
//         int submax = A[pos];
//         int res = 0;
//         for (int i = pos; i<pos+k && i<A.length; i++) {
//             submax = Math.max(submax, A[i]);
//             int count = i - pos + 1;
//             res = Math.max(res, helper(A, i+1, k, sum + submax*count));
//         }
//         map.put(key, res);
//         return res;
//     }
// }
```