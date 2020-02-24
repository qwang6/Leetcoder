
```java
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        /*
            dp + binarySearch
            类似longest increasing subsequence
            
            1. Sort by end time
            2. dpProfit[i]代表从0到i的时间所获得的最大利润
            3. dpEnd[i]代表从0到i的end time的时间点
        */
        int[][] times = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            times[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(times, (a,b)->a[1]-b[1]);
        
        List<Integer> dpEnd = new ArrayList<>();
        List<Integer> dpProfit = new ArrayList<>();
        
        dpEnd.add(0);
        dpProfit.add(0);
        
        for (int[] t : times) {
            int st = t[0], end = t[1], p = t[2];
            // find previous endTime index
            int preIndex = Collections.binarySearch(dpEnd, st+1);
            if (preIndex < 0) preIndex = -(preIndex+1);
            preIndex--;
            int currProfit = dpProfit.get(preIndex) + p, maxProfit = dpProfit.get(dpProfit.size()-1);
            if (currProfit > maxProfit) {
                dpProfit.add(currProfit);
                dpEnd.add(end);
            }
        }
        return dpProfit.get(dpProfit.size()-1);
    }
}
```