这个是固定长度的滑动窗口的问题，下面的代码就是模版，遇到fix window的情况就直接套这个模版。注意一点是，窗口是始终对当前index i慢一个的，所以在跳出循环的时候要再单独处理最后一个窗口。

```
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                sum += arr[i];
                continue;
            }
            double avg = (sum*1.0)/k;
            
            if (avg >= threshold) res++;
            sum -= arr[i-k];
            sum += arr[i];
        }
        double avg = (sum*1.0)/k;
        if (avg >= threshold) res++;
        
        return res;
    }
}
```