跳到当前位置的次数是能跳到这里的那些位置的次数的之和。通过滚动数组，只保留上一次的结果。最后结果除以在一个格子总的次数是8的k次方。
```java
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        // 可以跳的8个方向
        int[][] moves = {{1,2}, {1,-2}, {2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1}};
        
        double[][] dp0 = new double[N][N];
        // 初始化每一个格子的初始值都是1次
        for (double[] row : dp0) Arrays.fill(row, 1.0);
        
        for (int k = 0; k < K; k++) {
            double[][] dp1 = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] m : moves) {
                        int x = i + m[0];
                        int y = j + m[1];
                        if (x >= 0 && x < N && y >= 0 && y < N) {
                            // 能从x, y跳到当前的位置
                            dp1[i][j] += dp0[x][y];
                        }
                    }
                }
            }
            // 滚动数组只保留上一次的结果
            dp0 = dp1;
        }
        return dp0[r][c] / Math.pow(8, K);
    }
    
}
```