```java

class Solution {
    private int res = 0;
    
    public int minCameraCover(TreeNode root) {
        /**
        Apply a recusion function dfs.
        Return 0 if it's a leaf.
        Return 1 if it's a parent of a leaf, with a camera on this node.
        Return 2 if it's coverd, without a camera on this node.

        For each node,
        if it has a child, which is leaf (node 0), then it needs camera.
        if it has a child, which is the parent of a leaf (node 1), then it's covered.

        If it needs camera, then res++ and we return 1.
        If it's covered, we return 2.
        Otherwise, we return 0.
        */
        return (dfs(root) == 0 ? 1 : 0) + res;
    }
    private int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }
}

```