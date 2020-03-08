这是一个类01背包问题，这题采用滚动数组，并采用pull的模式，也就是说当前这一轮的值是取自上一轮的值.
* pull的方式: 当前是i+1轮，dp[i+1] += dp[i]
* push的方式: 当前是i轮，dp[i+1] += dp[i]
是pull还是push主要看当前操作是哪一轮。

### 1. dp solution
```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (S > sum || S < -sum) return 0;
        
        int[] dp = new int[2*sum+1];
        int offset = sum;
        dp[offset] = 1;
        for (int num : nums) {
            int[] next = new int[2*sum+1];
            for (int i = 0; i< 2*sum+1; i++) {
                // 这里采用push的方式
                if (i+num>=0 && i+num<2*sum+1) next[i+num] += dp[i];
                if (i-num>=0 && i-num<2*sum+1) next[i-num] += dp[i];
            }
            dp = next;
        }
        return dp[offset+S];
    }
}
```

### 2. recursion + memorization
```java
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        return helper(nums, 0, 0, S, new HashMap<>());
    }
    private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map){
        String encodeString = index + "->" + sum;
        if (map.containsKey(encodeString)){
            return map.get(encodeString);
        }
        if (index == nums.length){
            if (sum == S){
                return 1;
            }else {
                return 0;
            }
        }
        int curNum = nums[index];
        int add = helper(nums, index + 1, sum - curNum, S, map);
        int minus = helper(nums, index + 1, sum + curNum, S, map);
        map.put(encodeString, add + minus);
        return add + minus;
    }
}
```