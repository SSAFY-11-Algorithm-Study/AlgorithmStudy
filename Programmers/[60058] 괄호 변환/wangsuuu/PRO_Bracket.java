import java.util.*;

class Solution {
    public String solution(String p) {
        
        if(isCorrect(p)) //이미 올바른 괄호 문자열이라면
            return p;
        
        return solve(p);
    }
    
    public boolean isCorrect(String s){ //올바른 괄호 문자열인지 체크
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c=='(')
                stack.push('(');
            else {
                if(!stack.isEmpty()){
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String solve(String p){
        
        if("".equals(p))
            return p;
        
        //2번. w를 u, v로 분리
        String u = "", v = "";
        int num1=0, num2=0; //각 괄호의 갯수
        for(int i=0; i<p.length(); i++){ //균형 잡힌 것의 최소 단위 구하기
            if(p.charAt(i) == '(')
                num1++;
            else
                num2++;
            
            if(num1==num2){
                u = p.substring(0, i+1);
                v = p.substring(i+1);
                break;
            }
        }
            
        String tmp_v = solve(v); //flat하게 생각하기!!!
        
        if(isCorrect(u)) {
            return u + tmp_v;
        } else {
            String answer = "(" + tmp_v + ")";
            String tmp_u = reverse(u.substring(1, u.length()-1));
            return answer + tmp_u;
        }
    }
    
    public String reverse(String r){
        String ans = "";
        for(int i=0; i<r.length(); i++){
            if(r.charAt(i) == '(')
                ans += ")";
            else
                ans += "(";
        }
        return ans;
    }
}
