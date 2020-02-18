这道题是要使用Map<Integer, TreeMap<Integer, Integer>> 的结构，在map的key里面记录每一个index，map的value是一个个的TreeMap，记录的是该index的每次更新的历史。
```java
class SnapshotArray {
    private int snapId = 0;
    private Map<Integer, TreeMap<Integer, Integer>> map;
    
    public SnapshotArray(int length) {
        map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(i, new TreeMap<>());
        }
    }
    
    public void set(int index, int val) {
        map.get(index).put(snapId, val);
    }
    
    public int snap() {
        int res = snapId;
        snapId++;
        return res;
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> treeMap = map.get(index);
        Map.Entry<Integer, Integer> entry = treeMap.floorEntry(snap_id);
        if (entry != null) {
            return entry.getValue();
        }
        return 0;
    }
}
```