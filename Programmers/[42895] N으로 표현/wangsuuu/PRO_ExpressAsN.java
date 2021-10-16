import java.util.*;

//블로그 참고함. (https://zin0-0.tistory.com/82)
//Point : N의 조합과 사칙연산을 통해 만들 수 있는 모든 수를 가지고 사칙연산을 진행해서 number를 만들어 봄. 결국 완탐느낌.

class Solution {
    
    static int N1, number1;
    static int answer=9;
    
    public int solution(int N, int number) {
        
        N1 = N; 
        number1 = number;
        
        dfs(0, 0);
        
        return answer>8 ? -1 : answer;
    }

    public void dfs(int cnt, int result){ //cnt : 여태껏 N을 사용한 횟수 result : 사칙연산의 결과
        
        if(cnt>8){
            return;
        }
        
        if(result==number1){
            answer = Math.min(answer, cnt);
            return;
        }
        
        for(int i=1; i<=8-cnt; i++){ // i : N, NN, NNN...의 수들의 자릿수
            //N, NN, NNN...수 만들기
            int tmp = 0;
            for(int j=0; j<i; j++){
                tmp += Math.pow(10, j) * N1;
            }
            //사칙연산 수행
            dfs(cnt+i, result+tmp);
            dfs(cnt+i, result-tmp);
            dfs(cnt+i, result*tmp);
            dfs(cnt+i, result/tmp);
            
        }
    }
    
}
