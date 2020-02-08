这是一道基本的Trie的题目，唯一变化就是加了一个PriorityQueue

```
class Solution {
    class Trie {
            Trie[] sub = new Trie[26];
            PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());
        }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) {
            Trie t = root;
            for (char c : p.toCharArray()) {
                if (t.sub[c - 'a'] == null) {
                    t.sub[c - 'a'] = new Trie();
                }
                t = t.sub[c - 'a'];
                t.pq.offer(p);
                if(t.pq.size() > 3) {
                    t.pq.poll();
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        Trie t = root;
        for (char c : searchWord.toCharArray()) {
            LinkedList<String> l = new LinkedList<>();
            if (t != null)
                t = t.sub[c - 'a'];
            if (t != null) {
                l.addAll(t.pq);
                Collections.sort(l);
            }
            ans.add(l);
        }
        return ans;
    }   
}

```