这题看起来是一道BFS题目，实际上是一道DP题目，因为这题是没有边界的，不能用传统的BFS进行搜索，那样会导致时间和空间复杂度都很高。这题利用了马跳跃的镜像特点，也就是说在四个象限里面都是镜像的。所以只需要考虑第一象限的情况就够了，这样会剪掉很多的分支。
```java
class Solution {
    private int MOD = 301;
    private Map<Integer, Integer> cache = new HashMap<>();

    public int minKnightMoves(int x, int y) {
        return helper(Math.abs(x), Math.abs(y));
    }

    private int helper(int x, int y) {
        int key = Math.abs(x) * MOD + Math.abs(y);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int res = Integer.MAX_VALUE;
        if (x == 0 && y == 0) 
            res = 0;
        else if (Math.abs(x) + Math.abs(y) == 2) 
            res = 2;
        else {
            res = 1 + Math.min(helper(Math.abs(x-1), Math.abs(y-2)),
                                helper(Math.abs(x-2), Math.abs(y-1)));
        }
        cache.put(key, res);
        return res;
    }
}
```