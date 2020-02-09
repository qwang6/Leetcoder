https://leetcode.com/problems/remove-interval/
求两个区间是否相交a[]和b[]
Math.max(a[0],b[0]) <= Math.min(a[1], b[1]) 则相交.

```
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[1] <= toBeRemoved[0] || i[0] >= toBeRemoved[1]) { // no overlap.
                ans.add(Arrays.asList(i[0], i[1]));
            }else { // i[1] > toBeRemoved[0] && i[0] < toBeRemoved[1].
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