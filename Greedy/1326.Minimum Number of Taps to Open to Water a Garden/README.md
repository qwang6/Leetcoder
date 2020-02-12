这道题有两种解法，一种是dynamic programming，一种是greedy解法。

### dynamic programming ###
dp的实现就是类似背包问题，下面的实现是1024 Video Stitching的DP解法：

```java
dp[i]: 代表到i时间的位置最少需要几个影片

public int videoStitching(int[][] clips, int T) {
    int[] dp = new int[T+ 1];
    Arrays.fill(dp, T+1);
    dp[0] = 0;
    for(int i = 0; i <= T; i++) {
        for(int[] c : clips) {
            if(i >= c[0] && i <= c[1]) dp[i] = Math.min(dp[i], dp[c[0]] + 1);
        }
        if(dp[i] == T+1) return -1;
    }
    return dp[T];
}
```


### greedy ###
这道题目可以变换一下就成了45. Jump Game II和1024. Video Stitching一样的类型。变成区间后按start进行排序，用贪心的算法去找每一轮的能到达的最远的end。如果每轮结束时start==end，则说明无法再往后走了。如果start!=end,把end赋值给start然后再去下一轮去找能到达的最远距离。在每一轮结束时count++。

```
class Solution {
    // 同1024题, greedy solution
    public int minTaps(int n, int[] ranges) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < ranges.length; i++) {
            int start = i-ranges[i] < 0 ? 0 : i-ranges[i];
            int end = i+ranges[i] >= n ? n : i+ranges[i];
            list.add(new int[]{start, end});
        }
        list.sort((a,b) -> a[0]-b[0]);
        int res = 0;
        int start = 0, i = 0, end = 0;
        while (i <= n && start < n) {
            while (i <= n && list.get(i)[0] <= start) {
                end = Math.max(end, list.get(i)[1]);
                i++;
            }
            if (end <= start) return -1;
            start = end;
            res++;
        }
        return res;
    }
}
```
#### 45. Jump Game II
```
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums == null || nums.length < 2) return 0;
        
        int res = 0, i = 0, curMax = nums[0], nextMax = nums[0];
        while (i < nums.length) {
            while (i < nums.length && i <= curMax) {
                nextMax = Math.max(nextMax, i + nums[i]);
                i++;
            }
            res++;
            curMax = nextMax;
        }
        return res;
    }
}
```
#### 1024. Video Stitching
```java
class Solution {
    public int videoStitching(int[][] clips, int T) {
        int res = 0;
        Arrays.sort(clips, (a,b) ->  a[0] - b[0]);
        int i = 0, st = 0, end = 0;
        while (st < T) {
            for (; i < clips.length && clips[i][0] <= st; i++) {
                end = Math.max(end, clips[i][1]);
            }
            if (st == end) return -1;
            res++;
            st = end;
        }
        return res;
    }
}
```
