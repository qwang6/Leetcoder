Jump Game IV是Jump Game系列里面的另外一种类型。前面的几个题目基本都可以用Greedy的方法解，但是这个题目是需要用BFS的方法解，而且是要从后往前(从n-1到0)进行广度优先搜索。在做bfs的时候要注意把已经走过的list清空，要不就会陷入死循环。
在比赛的时候第一感觉就想用dfs+memo，但是没有成功。

```
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i])) map.put(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        dp[n-1] = 0;
        queue.offer(n-1);
        
        while (!queue.isEmpty()) {
            int i = queue.poll();
            List<Integer> next = map.get(arr[i]);
            next.add(i-1);
            next.add(i+1);
            for (int j : next) {
                if (j >= 0 && j < n && dp[j] == -1) {
                    dp[j] = dp[i] + 1;
                    queue.offer(j);
                }
            }
            next.clear(); // avoid later lookup map arr[i]
        }
        return dp[0];
    }
}
```