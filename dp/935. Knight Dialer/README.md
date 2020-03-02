同576和688一样，都用一个滚动数组来记录当前格子的次数。这里不同之处是在于只有特定的格子才能跳，要记录每个格子可以到达的位置。
```java
class Solution {
    public int knightDialer(int N) {
        int M = 1000000007;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 格子1可以跳到格子6和8.以下都一样的原则
        map.put(1, Arrays.asList(6, 8));
        map.put(2, Arrays.asList(7, 9));
        map.put(3, Arrays.asList(4, 8));
        map.put(4, Arrays.asList(3, 9, 0));
        map.put(5, new ArrayList<>());
        map.put(6, Arrays.asList(1, 7, 0));
        map.put(7, Arrays.asList(2, 6));
        map.put(8, Arrays.asList(1, 3));
        map.put(9, Arrays.asList(2, 4));
        map.put(0, Arrays.asList(4, 6));
        int[] dp = new int[10];
        Arrays.fill(dp, 1);

        for (int k = 1; k < N; k++) {
            int[] next = new int[10];
            for (int j = 0; j < 10; j++) {
                for (int i : map.get(j)) {
                    next[j] = (next[j] + dp[i]) % M;
                }
            }
            dp = next;
        }
        int res = 0;
        for (int n : dp) {
            res = (res + n) % M;
        }
        return res;
    }
}
```