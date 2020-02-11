这道题有两种解法，一种是BFS，写起来比较简单，但是时间和空间复杂度要高；另一种是Union Find + DFS，写起来比较麻烦，但是运行效率要高。这题的难度应该属于hard级别，属于hard里面的easy的，感觉难度系数标错了
#### BFS解法
首先创建graph，单词对单词的连接。bfs是搜索连接后的字符串。在每一个循环中又套另外一个子循环，子循环是找该字符串中的单词是否有同义词，如果有则替换，然后合成新的句子放入queue中。
```
class Solution {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> s : synonyms) {
            String w1 = s.get(0), w2 = s.get(1);
            connect(graph, w1, w2);
            connect(graph, w2, w1);
        }
        // 这里也可以用HashSet，但是最后要再转化成List进行排序，时间复杂度一样，但是实际运行的结果是用HashSet然后转化List排序要快一点。
        Set<String> visited = new TreeSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(text);

        while (!queue.isEmpty()) {
            String out = queue.poll();
            visited.add(out);
            String[] words = out.split(" ");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (!graph.containsKey(word)) continue;
                for (String neighbor : graph.get(word)) {
                    words[i] = neighbor;
                    String newText = String.join(" ", words);
                    if (!visited.contains(newText)) {
                        queue.offer(newText);
                    }
                }
            }
        }
        return new ArrayList<>(visited);
    }
    private void connect(Map<String, List<String>> graph, String v1, String v2) {
        if (!graph.containsKey(v1))
            graph.put(v1, new LinkedList<>());
        graph.get(v1).add(v2);
    }
}
```

#### UnionFind + DFS解法
首先先用union find方法把所有的近义词连接成连通分量，然后用dfs方法去搜索合成新的text。
```
class Solution {
    private Map<String, String> root = new HashMap<>();
    private List<String> res = new ArrayList<>();

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        for (List<String> sy : synonyms) {
            String r1 = find(sy.get(0)), r2 = find(sy.get(1));
            root.put(r1, r2);
        }
        dfs(0, text);
        Collections.sort(res);
        return res;
    }
    private void dfs(int pos, String text) {
        String[] parts = text.split(" ");
        if (pos == parts.length) {
            res.add(text);
            return;
        }
        if (!root.containsKey(parts[pos])) {
            dfs(pos+1, text);
        } else {
            String r = find(root.get(parts[pos]));
            for (String key : root.keySet()) {
                if (find(key).equals(r)) {
                    parts[pos] = key;
                    String ns = String.join(" ", parts);
                    dfs(pos+1, ns);
                }
            }
        }
    }
    private String find(String id) {
        if (!root.containsKey(id)) {
            root.put(id, id);
            return id;
        }
        while (!id.equals(root.get(id))) {
            root.put(id, root.get(id));
            id = root.get(id);
        }
        return id;
    }
}
```