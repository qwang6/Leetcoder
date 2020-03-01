这道题的sort的方式比较特殊，值得留意。sort的本质就是提供一个比较的函数，这个比较函数的输入可能来自与list的元素，也可能不来自list的元素。

```java
class Solution {
    class Node {
        char ch;
        int[] rank;
        Node(char ch, int n) {
            this.ch = ch;
            this.rank = new int[n];
        }
    }
    
    public String rankTeams(String[] votes) {
        Map<Character, Node> map = new HashMap<>();
        for (String v : votes) {
            int n = v.length();
            for (int i = 0; i < n; i++) {
                char ch = v.charAt(i);
                if (!map.containsKey(ch)) {
                    map.put(ch, new Node(ch, n));
                }
                map.get(ch).rank[i]++;
            }
        }
        List<Node> list = new ArrayList<>();
        for (char ch : map.keySet()) {
            Node node = map.get(ch);
            list.add(node);
        }
        Collections.sort(list, (a,b)->{
            for (int i = 0; i < a.rank.length; i++) {
                if (a.rank[i] != b.rank[i]) return b.rank[i] - a.rank[i];
            }
            return a.ch-b.ch;
        });
        StringBuilder sb = new StringBuilder();
        for (Node node : list) {
            sb.append(node.ch);
        }
        return sb.toString();
    }
}
```