这种parse字符串的题目基本都是用递归，这个题用了一个全局变量pos指向当前处理的字符的位置，时间复杂度是线性的。
```java
// https://www.youtube.com/watch?v=y2kFBqj_J08&list=PLLuMmzMTgVK6qfhuW2tarjHgHh3G8XpUF&index=22
class Solution {
    private int pos = 0;
    
    public boolean parseBoolExpr(String s) {
        char c = s.charAt(pos++); // 现在pos指向下一个字符
        if (c == 't') return true;
        if (c == 'f') return false;
        if (c == '!') {
            pos++; //跳过 '('
            boolean ans = !parseBoolExpr(s);
            pos++; //跳过 ')'
            return ans;
        }
        boolean isAnd = c == '&';
        boolean res = isAnd;
        pos++; //跳过 '('
        while(true) {
            if (isAnd) res &= parseBoolExpr(s);
            else res |= parseBoolExpr(s);
            if (s.charAt(pos++) == ')') break; // 跳过 ')' 或者跳过 ','
        }
        return res;
    }
}
```