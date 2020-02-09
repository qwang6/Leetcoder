https://leetcode.com/problems/remove-interval/
求两个区间是否相交a[]和b[]
Math.max(a[0],b[0]) <= Math.min(a[1], b[1]) 则相交.

这个题目要找相交的区间，对与一个区间和removed的区间相交，会有两种可能一种是左边相交，一种是右边相交，这两个不是if else的关系，要用两个if来判断。还有一种就是被包含在removed区间内，这种需要被排除。
```
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        // 这个解法都是用排除的思路，所以会省了很多的判断，这个思路需要掌握
        for (int[] i : intervals) {
            if (i[1] <= toBeRemoved[0] || i[0] >= toBeRemoved[1]) { // no overlap.
                ans.add(Arrays.asList(i[0], i[1]));
            }else { // i[1] > toBeRemoved[0] && i[0] < toBeRemoved[1].
                // 只保留相交的部分，被包含的那种情况自然就被排除了
                if(i[0] < toBeRemoved[0]) // left end no overlap.
                    ans.add(Arrays.asList(i[0], toBeRemoved[0]));
                if (i[1] > toBeRemoved[1]) // right end no overlap.
                    ans.add(Arrays.asList(toBeRemoved[1], i[1]));
            }
        }
        return ans;
    }
}
```
