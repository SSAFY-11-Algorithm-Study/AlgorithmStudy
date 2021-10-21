package week13;
import java.util.*;

class Solution {
    static int size;
    static int answer = 0;
    static Queue<String> q;
    public int solution(String s) {
        
        //스트링의 길이를 받아둠
        size = s.length();

        rotateStr(s,0);
        
        return answer;
    }
    
    public static void rotateStr(String s, int depth){
        if(depth == size){
            return;
        }
        
        //큐에 하나씩 잘라서 넣음
        q = new LinkedList<>();
        for(int i = 0 ; i < size ; i++)
            q.add(s.substring(i,i+1));
        
        //회전
        String temp = q.poll();
        q.add(temp);
        
        //회전된 스트링 다시 저장
        String str = "";
        for(int i = 0 ; i < size ; i++)
            str += q.poll();
        
       // System.out.println(str);
        
        //올바른 괄호인지 체크하는 함수 호출
        chkRight(str);
        
        rotateStr(str, depth+1);
    }
    
    
    public static void chkRight(String str){
        //스택 선언
        Stack<String> stack = new Stack<>();
        String [] arr = str.split("");
            
        for(int i = 0 ; i < arr.length ; i++){
            //스택 비어있으면 넣음
            if(stack.size() ==0)
                stack.push(arr[i]);
            else{
                String left = stack.peek();
                if(left.equals("(") && arr[i].equals(")"))
                    stack.pop();
                else if(left.equals("[") && arr[i].equals("]"))
                    stack.pop();
                else if(left.equals("{") && arr[i].equals("}"))
                    stack.pop();
                else stack.push(arr[i]);
            }
        }
        //System.out.println(stack.size());
        //큐가 비어있으면 올바른 괄호임
        if(stack.empty())
            answer++;
        
            
    }
}





