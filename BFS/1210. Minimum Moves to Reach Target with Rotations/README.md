当遇到类似的grid题目求最少移动步数的时候，首先要想到的就是BFS。这题用BFS进行解题的思路比较巧妙，因为snake占用两个格子，所以单纯用一个点的x，y坐标是无法确定snake的状态。所以要用三个值来定义snake的状态，分别是tail的x和y，以及head的x值和tailx值的差。然后再定义target的状态为(n-2, n-2, 0)和(n-1, n-2, 0)。

在做循环的时候，要分别处理水平和垂直的两种情况。

```java
/**
1. Use the coordinate of the snake tail (up/left part), r, c, and the row difference between head and tail, dr, to describe the position of the snake. Obviously, dr = 0 and dr = 1 indicate that the snake is in horizontal and vertical positions, respectively;
2. Use a Queue and a HashSet to perform BFS traversal and prune duplicates;
3. In order to create hash for HashSet, use r + "," + c + "," + dr to encode snake position.
*/
public int minimumMoves(int[][] g) {
        int n = g.length;
        int[] start = {0, 0, 0, 0}, target = {n - 1, n - 2, 0};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        Set<String> seen = new HashSet<>();
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0], c = pos[1], dr = pos[2], steps = pos[3];        // snake tail row, column, row difference, steps.
            if (Arrays.equals(Arrays.copyOf(pos, 3), target))               // reach target.
                return steps;
            if (seen.add(r + "," + c + "," + dr)) {                         // prune duplicates.
                if (dr == 0) {                                              // horizontal position.
                    if (r + 1 < n && g[r + 1][c] + g[r + 1][c + 1] == 0)    // the two cells below are empty: down and colock-wise rotation.
                        q.addAll(Arrays.asList(new int[]{r + 1, c, 0, steps + 1}, new int[]{r, c, 1, steps + 1}));
                    if (c + 2 < n && g[r][c + 2] == 0)                      // the right cell is empty.
                        q.offer(new int[]{r, c + 1, 0, steps + 1});         // right.
                }else {                                                     // vertical position.
                    if (c + 1 < n && g[r][c + 1] + g[r + 1][c + 1] == 0)    // the two cells right are empty: right and counterclock-wise rotation.
                        q.addAll(Arrays.asList(new int[]{r, c + 1, 1, steps + 1}, new int[]{r, c, 0, steps + 1}));
                    if (r + 2 < n && g[r + 2][c] == 0)                      // the below cell is empty.
                        q.offer(new int[]{r + 1, c, 1, steps + 1});         // down.
                }                    
            }
        }
        return -1;
    }
```