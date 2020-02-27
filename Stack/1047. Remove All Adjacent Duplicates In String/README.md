这题是一个典型的stack题，只要能想到stack就解出来了。
```java
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
                // 这里c字符在stack里面肯定没有重复的 所以就继续下一轮循环。
                continue;
            }
            while (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            }
        }
        if (stack.isEmpty()) return "";
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
```