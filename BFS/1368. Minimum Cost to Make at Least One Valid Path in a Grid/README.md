#### Approach 1: Dijkstra's algorithm ~ 20ms

Complexity:

Time: O(ElogV) ~ O(mn * log(mn)), E = 4mn, V = mn
Space: O(m*n)
Similar problems:

* 778. Swim in Rising Water
* 1102. Path With Maximum Minimum Value

```java
class Solution {
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int INF = (int) 1e9;
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // minHeap by cost
        q.offer(new int[]{0, 0, 0});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int cost = top[0], r = top[1], c = top[2];
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i][0], nc = c + DIR[i][1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int ncost = cost;
                    if (i != (grid[r][c] - 1)) ncost += 1; // change direction -> ncost = cost + 1
                    if (dist[nr][nc] > ncost) {
                        dist[nr][nc] = ncost;
                        q.offer(new int[]{ncost, nr, nc});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
```