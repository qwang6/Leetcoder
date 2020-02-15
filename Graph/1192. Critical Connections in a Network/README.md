这道题目是找一个无向连通图里面的critical edges，利用Tarjan算法
```java
class Solution {
    private int depth = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //dist[i] is the node's depth in a DFS tree
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        //low[i] is the node's all reachable node's smallest depth in a DFS tree
        int[] low = new int[n];
        //Create the graph;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        //Create the result;
        List<List<Integer>> result = new ArrayList<>();
        //DFS for each node
        for (int i = 0; i < n; i++) {
            if(dist[i] == -1) {
                dfs(dist, low, graph, result, i, -1);
            }
        }
        return result;
    }
    private void dfs(int[] dist, int[] low, 
                     ArrayList<Integer>[] graph, List<List<Integer>> result,
                     int cur, int parent) {
        depth++;
        dist[cur] = depth;
        low[cur] = depth;
        for (int next : graph[cur]) {
            if (next == parent) {
                continue;
            } else {
                if (dist[next] == -1) {// 未访问过的neighbor
                    dfs(dist, low, graph, result, next, cur);
                    // 当neighbor next被完全访问完后，更新当前的最小depth
                    low[cur] = Math.min(low[cur], low[next]);
                    // 如果当前的depth小于next节点的最小值depth，说明cur和next之间没有环
                    // 则该edge就是一个critical edge
                    if (low[next] > dist[cur]) {
                        result.add(Arrays.asList(cur, next));
                    }
                } else {
                    // next节点已经被访问过，用next节点的最小depth更新当前节点的最小depth值
                    low[cur] = Math.min(low[cur], low[next]);
                }
            }
        }
    }
}
```
