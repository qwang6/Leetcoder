先把数组按长度从小到大排序。创建一个Map，key是string，value是长度。遍历排序的数组，对每一个string，尝试去掉每一个字符，看看新的字符串是否在map中，如果在map中，取出map的value并加一，然后尝试更新该字符串的最大长度。每个字符串处理完之后，设置该字符串的最大长度并更新全局的最大长度。
```java
class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
```