这道题要走两遍，第一遍找到出现最多的次数，第二次找到出现最多次数的值。BST的作用是给你中序遍历是升序的隐含条件，而不是通过二分法。
* 时间复杂度：O(n)
* 空间复杂度：O(1) (这题规定递归调用栈不算占空间)

```java
class Solution {
    private Integer prev = null;
    private int maxCnt = 1;
    private int count = 0;
    
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        
        dfsCount(root);
        
        List<Integer> list = new ArrayList<>();
        prev = null;
        count = 0;
        dfsMode(root, list);
        if (count == maxCnt) list.add(prev);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    private void dfsCount(TreeNode root) {
        if (root == null) return;
        
        dfsCount(root.left);
        if (prev == null) {
            prev = root.val;
            count = 1;
        } else {
            if (prev == root.val) {
                count++;
                maxCnt = Math.max(maxCnt, count);
            } else {
                prev = root.val;
                count = 1;
            }
        }
        dfsCount(root.right);
    }
    private void dfsMode(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        dfsMode(root.left, list);
        if (prev == null) {
            prev = root.val;
            count = 1;
        } else {
            if (prev == root.val) {
                count++;
            } else {
                if (count == maxCnt) {
                    list.add(prev);
                }
                prev = root.val;
                count = 1;
            }
        }
        dfsMode(root.right, list);
    }
}
```