import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++) {
            String newstring = rotate(s, i);
            if(chk(newstring)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public String rotate(String s, int idx) {
        String str = s.substring(idx);
        for(int i = 0; i < idx; i++) {
            str += s.charAt(i);
        }
        return str;
    }
    
    public boolean chk(String str) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(stack.size() == 0 && (c == ']' || c == '}' || c == ')')) {
                return false;
            }
            
            if(c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if(c == ']') {
                if(stack.peek() == '[') {
                    stack.pop();
                } else return false;
            } else if(c == '}') {
                if(stack.peek() == '{') {
                    stack.pop();
                } else return false;
            } else if(c == ')') {
                if(stack.peek() == '(') {
                    stack.pop();
                } else return false;
            }
        }
        if(stack.size() != 0) return false;
        return true;
    }
}
