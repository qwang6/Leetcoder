这道设计题目是设计一个系统可以记录tweet的时间，并根据一个区间返回这个tweet在这段区间的发生的频率，按分钟，小时，或者天来进行bucket。
这种题目一般都要用TreeMap这个数据结构，利用其key是排序的，可以获得一个区间的subMap。
题目在竞赛的时候能跑过test case但是相同的test case提交就不过，我觉得我的代码应该没有问题，下面是我的具体实现：
```
import java.util.*;

class TweetCounts {
    
    private Map<String, TreeMap<Integer, Integer>> map = new HashMap<>();
    private Map<String, Integer> lkp = new HashMap<>();

    public TweetCounts() {
        lkp.put("minute", 60);
        lkp.put("hour", 3600);
        lkp.put("day", 86400);
    }
    
    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) map.put(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> treeMap = map.get(tweetName);
        treeMap.put(time, treeMap.getOrDefault(time, 0) + 1);
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (!map.containsKey(tweetName)) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        SortedMap<Integer,Integer> subMap = map.get(tweetName).subMap(startTime, endTime+1);
        int gap = lkp.get(freq);
        int st = startTime, end = startTime+gap;
        List<int[]> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            list.add(new int[]{key, val});
        }
        int i = 0, sum = 0;
        while (st <= endTime && i < list.size()) {
            int[] p = list.get(i);
            int key = p[0], val = p[1];
            if (key >= st && key < end) {
                sum += val;
                i++;
            } else {
                res.add(sum);
                sum = 0;
                
                int newEnd = end + gap;
                st = end;
                end = newEnd;
            }
        }
        if (sum > 0) res.add(sum);
        return res;
    }
}
```