下面使用的是dfs+memo,这里需要注意的是只能往两边更低的地方走，不能跨过高的再到低的地方。
Jump Game系列是很好的一类题型，里面包括Greedy，DP，BFS，该题型变换很多，需要好好掌握。
```
class Solution {
    private Map<Integer,Integer> memo = new HashMap<>();
    
    public int maxJumps(int[] arr, int d) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, helper(arr, d, i));
        }
        return max+1;
    }
    private int helper(int[] arr, int d, int pos) {
        if (memo.containsKey(pos)) return memo.get(pos);
        
        int res = 0;
        for (int i = pos-1; i >= Math.max(0, pos-d); i--) {
            int count = 1;
            if (arr[i] < arr[pos]) {
                count += helper(arr, d, i);
                res = Math.max(res, count);
            } else break;
        }
        
        for (int i = pos+1; i<=Math.min(arr.length-1, pos+d); i++) {
            int count = 1;
            if (arr[i] < arr[pos]) {
                count += helper(arr, d, i);
                res = Math.max(res, count);
            } else break;
        }
        memo.put(pos, res);
        
        return res;
    }
}
```
