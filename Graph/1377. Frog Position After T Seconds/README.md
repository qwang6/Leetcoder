这题要先转换成graph，再利用bfs或者dfs解题。这里要注意的一点是题目要求是在第t时刻并且是node等于target的时候，或者node等于target并且是叶子节点，但是还没到t时刻。这题在计算下一层的概率的时候需要取得其孩子节点的个数，需要过滤掉已经访问过的节点，因为那些节点是父亲节点，图是双向的。
```java
class Solution {
    // public double frogPosition(int n, int[][] edges, int t, int target) {
    //     List<Integer>[] graph = new List[n];
    //     for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    //     for (int[] e : edges) {
    //         graph[e[0] - 1].add(e[1] - 1);
    //         graph[e[1] - 1].add(e[0] - 1);
    //     }
    //     boolean[] visited = new boolean[n]; 
    //     visited[0] = true;
    //     double[] prob = new double[n]; 
    //     prob[0] = 1f;
    //     Queue<Integer> q = new LinkedList<>(); 
    //     q.offer(0);
    //     while (!q.isEmpty() && t-- > 0) {
    //         for (int i = 0, size = q.size(); i < size; i++) {
    //             int u = q.poll();
    //             int nextVerticesCount = 0;
    //             for (int v : graph[u]) if (!visited[v]) nextVerticesCount++;
    //             for (int v : graph[u]) {
    //                 if (!visited[v]) {
    //                     visited[v] = true;
    //                     q.offer(v);
    //                     prob[v] = prob[u] / nextVerticesCount;
    //                 }
    //             }
    //             if (nextVerticesCount > 0) prob[u] = 0; // still have next vertices to jump, frog keep going to the next vertex
    //         }
    //     }
    //     return prob[target - 1];
    // }
    
    private LinkedList<Integer> adjListArray[];
    public double frogPosition(int n, int[][] edges, int t, int target) {
        adjListArray = new LinkedList[n + 1];
        for(int i = 0; i <= n ; i++) adjListArray[i] = new LinkedList<>(); 
        for (int[] edge : edges) {
            adjListArray[edge[0]].add(edge[1]);
            adjListArray[edge[1]].add(edge[0]);
        }

        return Math.max(0, dfs(1, 0, t, target, new boolean[n + 1]));
    }

    private double dfs(int node, int currTime, int t, int target, boolean[] visited) {
        if (currTime > t) return -1; // time is out
        if (node == target && currTime == t) return 1; // reached the target on time
        visited[node] = true;
        int childrenCount = 0; // count of children that still were not visited
        for (int child : adjListArray[node]) {
            if (visited[child]) continue;
            childrenCount++;
        }
        if (node == target && childrenCount == 0) return 1; // reached the target and there are no more moves
        for (int child : adjListArray[node]) {
            if (visited[child]) continue; // skip visited children
            double res = dfs(child, currTime + 1, t, target, visited);
            if (res == -1) continue;
            return res * 1.0 / childrenCount; // probability calculation
        }
        return -1;
    }
}
```