这道题就是tree的题目的变形，利用dfs的tree的后序遍历算法。
就是从当前节点，对每个子孩子都做递归，取出其中第一长和第二长的路径加起来和result比较，递归函数返回第一再加一（因为还要加上当前节点）。要注意不要算回头路，通过给递归函数传递一个parent，如果取得的next节点是parent则跳过。

```Java
class Solution {
    int diameter = 0;

    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        LinkedList<Integer>[] adjacencyList = new LinkedList[n];
        for (int i = 0; i < n; ++i) adjacencyList[i] = new LinkedList<>();
        for (int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }
        diameter = 0;
        depth(0, -1, adjacencyList);
        return diameter;
    }

    private int depth(int root, int parent, LinkedList<Integer>[] adjacencyList) {
        int maxDepth1st = 0, maxDepth2nd = 0;
        for (int child : adjacencyList[root]) {
            if (child != parent) { // Only one way from root node to child node, don't allow child node go to root node again!
                int childDepth = depth(child, root, adjacencyList);
                if (childDepth > maxDepth1st) {
                    maxDepth2nd = maxDepth1st;
                    maxDepth1st = childDepth;
                } else if (childDepth > maxDepth2nd) {
                    maxDepth2nd = childDepth;
                }
            }
        }
        int longestPathThroughRoot = maxDepth1st + maxDepth2nd; // Sum of the top 2 highest depths is the longest path through this root
        diameter = Math.max(diameter, longestPathThroughRoot);
        return maxDepth1st + 1;
    }
}
```
