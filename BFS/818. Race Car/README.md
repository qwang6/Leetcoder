```java
class Solution {
    public int racecar(int target) {
        Queue<CarInfo> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new CarInfo(0, 1));
        visited.add(0 + "," + 1);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                CarInfo ci = queue.poll();
                if (ci.pos == target) return step;

                String s1 = ci.pos + "," + (ci.speed*2);
                String s2 = ci.pos + "," + (ci.speed > 0 ? -1 : 1);
                if (Math.abs(ci.pos+ci.speed-target)<target && !visited.contains(s1)) {
                    visited.add(s1);
                    queue.offer(new CarInfo(ci.pos+ci.speed, ci.speed*2));
                }
                if (Math.abs(ci.pos-target)<target && !visited.contains(s2)) {
                    visited.add(s2);
                    queue.offer(new CarInfo(ci.pos, ci.speed>0?-1:1));
                }
            }
            step++;
        }
        return -1;
    }
}

class CarInfo {
    int pos, speed;
    CarInfo(int pos, int speed) {
        this.pos = pos;
        this.speed = speed;
    }
}
```