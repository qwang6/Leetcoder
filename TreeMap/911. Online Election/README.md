- 这道题用TreeMap来解，key是Time，value是在该时间的时候的候选人。
- 这题还可以用二分法来做，就是找到t时间的下边界。

```
class TopVotedCandidate {

    private TreeMap<Integer, Integer> tm = new TreeMap<>();
    
    public TopVotedCandidate(int[] persons, int[] times) {
        int[] counts = new int[persons.length+1];
        
        int max = 0, maxP = -1;
        for (int i = 0; i < times.length; i++) {
            int p = persons[i], t = times[i];
            counts[p]++;
            if (max <= counts[p]) {
                max = counts[p];
                maxP = p;
            }
            tm.put(t, maxP);
        }
    }
    
    public int q(int t) {
        int key = tm.floorKey(t);
        return tm.get(key);
    }
}

/**
 class TopVotedCandidate {
    List<Vote> A;
    public TopVotedCandidate(int[] persons, int[] times) {
        A = new ArrayList();
        Map<Integer, Integer> count = new HashMap();
        int leader = -1;  // current leader
        int m = 0;  // current number of votes for leader

        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i], t = times[i];
            int c = count.getOrDefault(p, 0) + 1;
            count.put(p, c);

            if (c >= m) {
                if (p != leader) {  // lead change
                    leader = p;
                    A.add(new Vote(leader, t));
                }

                if (c > m) m = c;
            }
        }
    }

    public int q(int t) {
        int lo = 1, hi = A.size();
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A.get(mi).time <= t)
                lo = mi + 1;
            else
                hi = mi;
        }

        return A.get(hi - 1).person;
    }
}

class Vote {
    int person, time;
    Vote(int p, int t) {
        person = p;
        time = t;
    }
}
 */
```