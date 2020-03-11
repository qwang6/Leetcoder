这题不能用暴力的方法两两pair建立graph再用dfs的方法搜索。需要利用Union Find方法对每一个数和其公约数进行union。这里注意循环到该数的平方即可，因为再往后都是前面公约数的组合了。

如果不明白，看花花的视频：https://www.youtube.com/watch?v=GTX0kw63Tn0&list=PLLuMmzMTgVK6qfhuW2tarjHgHh3G8XpUF&index=30

```java
class Solution {
    class UF {
        int count;
        int[] roots;
        UF(int n) {
            this.count = n;
            roots = new int[n+1];
            for (int i = 0; i <= n; i++) roots[i] = i;
        }
        int find(int p) {
            while (roots[p] != p) {
                roots[p] = roots[roots[p]];
                p = roots[p];
            }
            return p;
        }
        void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return;
            roots[pa] = pb;
            count--;
        }
    }
    public int largestComponentSize(int[] A) {
        int max = 0;
        for (int a : A) max = Math.max(max, a);
        UF uf = new UF(max);
        for (int a : A) {
            for (int b = 2; b <= (int)Math.sqrt(a); b++) {
                if (a % b == 0) {
                    uf.union(a, b);
                    uf.union(a, a / b);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int a : A) {
            int p = uf.find(a);
            map.put(p, map.getOrDefault(p, 0) + 1);
            res = Math.max(res, map.get(p));
        }
        return res;
    }
}
```