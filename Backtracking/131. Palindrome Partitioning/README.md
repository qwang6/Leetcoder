直接套backtracking的模版
```java
class Solution {
    public List<List<String>> partition(String s) {
        List<String> tempList = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        backtrack7(list,tempList,s,0);
        return list;
    }
    
    private void backtrack7(List<List<String>> list, List<String> tempList, String s, int start) {
        if (start == s.length()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i=start;i<s.length();i++) {
                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i+1));
                    backtrack7(list, tempList, s, i+1);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
}
```