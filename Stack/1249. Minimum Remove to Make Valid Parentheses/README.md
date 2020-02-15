这题是利用栈来计算不valid的括号的位置。走一遍string后，栈里面剩下的就是所有的不valid的括号的index。然后从string的最后往前走，把不在栈里面的index的字符记录下来并最终返回。
```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            else if (c == ')') {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(')
                    stack.pop();
                else
                    stack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i>=0; i--) {
            if (!stack.isEmpty()) {
                if (stack.peek() == i) stack.pop();
                else sb.append(s.charAt(i));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.reverse().toString();
    }
}
```

如果只是要知道不符合条件的括号的个数，可以用下面的解法：
```java
class Solution {
    public int notValidParentheses(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else {
                if (left > 0) left--;
                else right++;
            }
        }
        return left + right;
    }
}
```