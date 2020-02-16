#### Think the problem reversely !!!
Now we can consider it from the other side :

We first find the max one, then we subtract the other nums. Then we will get the last round num in the current position. Then we change it to the last round's num, and recursively call the function.

eg. [8,5] ->[3,5]->[3,2]->[1,2]->[1,1]
```java
class Solution {
    public boolean isPossible(int[] target) {
        int max = 0;
        int index = 0;
        for(int i= 0 ;i < target.length ;i++){
            if(max < target[i]){
                max = target[i];
                index = i;
            }
        }
        if(max == 1)return true; // means we finally get an array full of 1, then we return true;
        for(int i= 0;i<target.length;i++){
            if(i == index)continue; // skip the max one's index
            if(target[i] > max)return false; // max must be equal or bigger to any num in the array.
            max-=target[i];// subtract the other num in the array.
        }
        target[index] = max; // change the current one to the new max.
        return isPossible(target); //recursively call the function
    }
}
```