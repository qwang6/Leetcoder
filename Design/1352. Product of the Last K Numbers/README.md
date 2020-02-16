这个题的brute force的解法就是用一个list把所有的值都加起来，然后从后往前乘。
如果想用prefix sum的思路去做呢，直接拿过来不适用这题，因为当遇到一个值为0后，这个值再往后的连乘的积就全都是0了。所以要调整prefix sum的思路，当遇到num为0的时候就要重新开始记录一个新的list。
```java
class ProductOfNumbers {
    private List<Integer> prod;
    private int p;
    
    public ProductOfNumbers() {
        prod = new ArrayList<>();
        p = 1;
    }
    public void add(int num) {
        if(num == 0) {
            prod = new ArrayList<>();
            p = 1;
            return;
        }
        p *= num;
        prod.add(p);
    }
    public int getProduct(int k) {
        if(prod.size() < k) return 0;
        int ans = prod.get(prod.size() - 1);
        if(prod.size() == k) return ans;
        return (ans / prod.get(prod.size() - 1 - k));
    }
}
```