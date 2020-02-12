这道题目明显是要用后序，记录每个节点作为root的这个子树的sum值，剩余部分就是全部sum再减去当前子树的sum值。
```Java
class Solution {
    private Map<TreeNode, Long> map = new HashMap<>();
    private int M = 1000000007;
    
    public int maxProduct(TreeNode root) {
        if (root == null) return 0;
        
        long total = helper(root);
        long res = 0;
        for (TreeNode node : map.keySet()) {
            long cur = map.get(node);
            long count = total - map.get(node);
            res = Math.max(res, cur * count);
        }
        return (int) (res % M);
    }
    private long helper(TreeNode node) {
        if (node == null) return 0;
            
        long left = helper(node.left), right = helper(node.right);
        long sum = left+right+node.val;
        map.put(node, sum);
        return sum;
    }
}
```
