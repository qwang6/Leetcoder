这道题目和基本的DFS没有什么不同，唯一需要注意的是这题You can't visit the same cell more than once.也就是说是从一点出发，然后尽可能的走到头，看这一条路径能收集多少金币。但是不能有分叉，也就是说你不能走到头收集完这个分支再回来走下一个分支，把两个分支的金币加起来，你只能选择能收集到最多的那条分支。
```java
class Solution {
    private int res = 0;
    
    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return res;
    }
    private void dfs(int[][] grid, int i, int j, int sum) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            res = Math.max(res, sum);
            return;
        }
        int val = grid[i][j];
        sum += val;
        grid[i][j] = 0;
        dfs(grid, i+1, j, sum);
        dfs(grid, i-1, j, sum);
        dfs(grid, i, j+1, sum);
        dfs(grid, i, j-1, sum);
        grid[i][j] = val;
    } 
}
```
