import java.util.*;
//처음에 너무 어렵게 생각했었음...ㅠㅜ
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book); //문자열 정렬
        
        for(int i=0; i<phone_book.length-1; i++){
            if(phone_book[i+1].startsWith(phone_book[i])){
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}
