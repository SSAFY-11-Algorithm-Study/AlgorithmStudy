//그냥 이중 반복문 돌릴 수도 있었는데, 인터넷 참고해서 스택으로 품(https://girawhale.tistory.com/7)

//index = 시점
//가격이 올라가면 push, 가격이 떨어지면 더 이상 진행 못하므로 pop해서 index간의 차이를 구함
//다 돌고 난 후 stack에 남아있는 애들 -> 한 번도 가격이 떨어지지 않은 애들이므로, list - (index + 1) 값 넣어주고 pop

//주의할 점 : 스택이 비어있을 때 stack.peek() -> null 반환,  stack.pop() -> exception 발생 해 런타임 오류 남.

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<int[]> stack = new Stack<>(); //값, 인덱스 저장
        
        for(int i=0; i<prices.length; i++){
            
            if(stack.isEmpty()){ //이 조건 없으면 런타임 에러 남. 비어있으면 일단 값 푸시
                stack.push(new int[] {prices[i], i});
                continue;
            }
            
            if(stack.peek()[0] <= prices[i]){ //가격이 올라가면
                stack.push(new int[] {prices[i], i});
            } else { //가격이 떨어지면
                int[] cur = stack.pop();
                int tmp = i - cur[1]; //기간 계산
                answer[cur[1]] = tmp;
                i--;
            }
        }
        
        while(!stack.isEmpty()){ //스택이 남아있다면 비움
            int[] cur = stack.pop();
            answer[cur[1]] = prices.length - (cur[1] + 1);
        }
        
        return answer;
    }
}
