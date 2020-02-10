这道题虽然貌似是tree的题目，但是实际上是一道Math的题目。因为是完全二叉树，所以每一层的节点的范围在2^d -> 2^(d+1) -1之间。
```
例如， 如果label是14的话，

depth = 3, values in this depth lie from 8 to 15 (since it is a complete binary tree)
offset = 15 - 14 = 1
real parent of 14 = parent of ( 8 + offset ) = parent (9) = 9/2 = 4
```

```
class Solution {
    /**
        Calculate current depth of the label
        Calculate offset (for each depth, values lie from 2^d -> 2^(d+1) -1
        Find the real parent based on offset
        Repeat until we reach 1
        e.g. parent of 14 is 4

        depth = 3, values in this depth lie from 8 to 15 (since it is a complete binary tree)
        offset = 15 - 14 = 1
        real parent of 14 = parent of ( 8 + offset ) = parent (9) = 9/2 = 4
    */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new LinkedList<>();
        int parent = label;
        result.add(0, parent);
        
        while(parent != 1) {
            int d = (int) (Math.log(parent)/Math.log(2));
            int offset = (int) Math.pow(2, d+1) - 1 - parent;
            parent = ((int) Math.pow(2, d) + offset) / 2;
            result.add(0, parent);   
        }
        return result;
    }
}
```