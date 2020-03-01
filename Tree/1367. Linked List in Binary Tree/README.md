这道tree的题目比较有代表性，当需要从tree中任意一个节点作为开始的时候，常常使用下面的这个模版。

```java
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        if (head.val == root.val) {
            if (helper(head.next, root.left)) return true;
            if (helper(head.next, root.right)) return true;
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    private boolean helper(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        if (head.val != root.val) return false;
        return helper(head.next, root.left) || helper(head.next, root.right);
    }
}
```
