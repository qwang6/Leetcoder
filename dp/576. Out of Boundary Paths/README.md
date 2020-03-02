同688.Knight Probability in Chessboard一样
```java
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) return 0;
        
        int MOD = 1000000007;
        int[][] count = new int[m][n];
        count[i][j] = 1;
        int result = 0;
        
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] dir : dirs) {
                        int x = r + dir[0];
                        int y = c + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n)
                        //唯一不同之处是只有当越界的时候记录result
                            result = (result + count[r][c]) % MOD;
                        else 
                            temp[x][y] = (temp[x][y] + count[r][c]) % MOD;
                    }
                }
            }
            count = temp;
        }
        return result;
    }
}
```