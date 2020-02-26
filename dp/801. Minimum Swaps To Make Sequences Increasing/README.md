首先是用DFS backtracking来做，这个solution会超时。
```java

4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
// Author: Huahua
// Running time: TLE 84/102 test cases passed.
class Solution {
public:
  int minSwap(vector<int>& A, vector<int>& B) {
    int ans = INT_MAX;
    dfs(A, B, 0, 0, ans);
    return ans;
  }
private:
  void dfs(vector<int>& A, vector<int>& B, int i, int c, int& ans) {
    if (c >= ans) return;
    
    if (i == A.size()) {
      ans = min(ans, c);
      return;
    }
    
    if (i == 0 || A[i] > A[i - 1] && B[i] > B[i - 1])
      dfs(A, B, i + 1, c, ans);
    
    if (i == 0 || A[i] > B[i - 1] && B[i] > A[i - 1]) {
      swap(A[i], B[i]);
      dfs(A, B, i + 1, c + 1, ans);
      swap(A[i], B[i]);
    }
  }
};
```

![花花讲义](./801-ep183.png)

```java
class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n], notswap = new int[n];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(notswap, Integer.MAX_VALUE);
        
        notswap[0] = 0;
        swap[0] = 1;
        
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i-1] && B[i] > B[i-1]) {
                swap[i] = swap[i-1] + 1;
                notswap[i] = notswap[i-1];
            }
            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                // A[i - 1] / B[i - 1] weren't swapped.
                swap[i] = Math.min(swap[i], notswap[i-1]+1);
                // Swapped A[i - 1] / B[i - 1], no swap needed for A[i] / B[i]      
                notswap[i] = Math.min(notswap[i], swap[i-1]);
            }
        }
        return Math.min(swap[n-1], notswap[n-1]);
    }
}
```