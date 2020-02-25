这题是House Rob的变种，可以考虑把nums[i] 和 nums[i] - 1 or nums[i] + 1 的关系转化为nums[i]和nums[i-1]和nums[i+1].
* 花花视频讲的很清楚:https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-740-delete-and-earn/
利用桶排序，把原始数组整理一下。

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] arr = new int[100001];
        for (int n : nums) {
            arr[n] += n;
        }
        return rob(arr);
    }
    
    private int rob(int[] arr) {
        int rob = 0, notRob = 0;
        for (int a : arr) {
            int temp = rob;
            rob = Math.max(notRob + a, rob);
            notRob = Math.max(temp, notRob);
        }
        return Math.max(rob, notRob);
    }
}
```
