同Word Break，只不过要注意递归函数返回List<String>，代表的是从pos到n的字符串返回的word列表。

这里要注意的是如果递归时没有找到，只需要返回一个空List。如果pos到头了，则需要加入一个空的字符串在list中，以和没有找到的case进行区分。

```java
class Solution {
    private Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return helper(s, dict, 0);
    }
    private List<String> helper(String s, Set<String> dict, int pos) {
        List<String> res = new ArrayList<>();

        // 如果pos到最后了，说明成功分割了，这时候要加入一个空字符串以区分没有找到的case
        if (pos == s.length()) {
            res.add("");
            return res;
        }
        if (cache.containsKey(pos)) return cache.get(pos);

        for (int i = pos+1; i <= s.length(); i++) {
            String sub = s.substring(pos, i);
            if (dict.contains(sub)) {
                List<String> list = helper(s, dict, i);
                for (String str : list) {
                    if (str.isEmpty()) {
                        res.add(sub);
                    } else {
                        res.add(sub + " " + str);
                    }
                }
            }
        }
        cache.put(pos, res);
        return res;
    }
}
```