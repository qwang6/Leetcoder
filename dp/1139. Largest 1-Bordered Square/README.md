先预处理每一个cell的上下左右连续1的个数，然后再长度从大到小去看square的情况。
```java
class Solution {
    public int largest1BorderedSquare(int[][] A) {
        // 先预处理，计算每个点上面和左边都有多少连续的1
        int m = A.length, n = A[0].length;
        int[][] left = new int[m][n], top = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] > 0) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0  ? top[i - 1][j] + 1 : 1;
                }
            }
        }
        // 针对每个长度值，从右下角那个点找往上有多少个连续的1，往左有多少个连续的1
        // 再从右上角那个点看往左有多少连续的1，从左下角那个点看往上有多少个连续的1
        for (int l = Math.min(m, n); l > 0; --l)
            for (int i = 0; i < m - l + 1; ++i)
                for (int j = 0; j < n - l + 1; ++j)
                    if (top[i + l - 1][j] >= l &&
                            top[i + l - 1][j + l - 1] >= l &&
                            left[i][j + l - 1] >= l &&
                            left[i + l - 1][j + l - 1] >= l)
                        return l * l;
        return 0;
    }
}
```
