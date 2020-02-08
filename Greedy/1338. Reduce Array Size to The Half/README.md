这道题明显Greedy的算法，先移除出现次数最多的，没啥好说的。唯一要注意的是很多时候为了方便要建立一个类来存储更多的信息。
```
class Solution {
    class Node {
        int num, count;
        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> b.count - a.count);
        for (int key : map.keySet()) {
            pq.offer(new Node(key, map.get(key)));
        }
        int count = 0, n = arr.length;
        int res = 0;
        while (!pq.isEmpty()) {            
            if (n % 2 == 0) {
                if (count >= n/2) break;
            } else {
                if (count >= n/2+1) break;
            }
            Node node = pq.poll();
            count += node.count;
            res++;
        } 
        return res;
    }
}
```