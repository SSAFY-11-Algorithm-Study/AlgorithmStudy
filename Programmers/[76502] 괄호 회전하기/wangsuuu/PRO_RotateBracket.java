import java.util.*;

class Solution {
    static int answer;
    public int solution(String s) {
        int answer = 0;
        
        ArrayList<Character> c = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            c.add(s.charAt(i));
        }//그대로 옮기기
        
        for(int i=0; i<s.length(); i++){
            rotate(c); // 총 s번, 왼쪽으로 한칸씩 옮겨봄
            if(isValid(c)) //올바른 괄호 문자열이라면
                answer++;
        }
        return answer;
    }
    
    public void rotate(ArrayList<Character> c){
        c.add(c.get(0));
        c.remove(0);
    }
    
    public boolean isValid(ArrayList<Character> c){
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<c.size(); i++){
            char ch = c.get(i);
            if(stack.isEmpty()){ //비었으면 일단 넣는다.
                stack.push(ch);
            } else{
                if(ch=='(' || ch=='{' || ch=='['){ //여는 괄호라면
                    stack.push(ch);
                } else{ //닫는 괄호라면
                    if(ch==')'){
                        if(stack.peek() != '(')
                            return false;
                        stack.pop();
                    } else if(ch=='}') {
                         if(stack.peek() != '{')
                            return false;
                        stack.pop();
                    }  else{
                         if(stack.peek() != '[')
                            return false;
                        stack.pop();
                    }
                }                
            }//else
        }//for
        
        return stack.isEmpty() ? true : false;
    }
}
