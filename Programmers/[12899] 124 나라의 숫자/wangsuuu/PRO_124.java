//인터넷 참고.
//일반적인 진법 계산 원리 사용. 총 세개의 숫자만 쓰므로 3진법. (단, 나머지가 0일때는 0이 아니라 4를 사용함)
//몫이 0이 될때까지 계속 나누고, 나머지를 앞에 붙여나감. 단, 3으로 나누어 떨어지면 몫을 -1 해 줘야 함.

class Solution {
    public String solution(int n) {
        String answer = "";
        
        int[] rest = {4, 1, 2}; //나머지 차례대로 저장
        
        while(true){
            
            if(n/3==0){
                if(n!=0){
                   answer = Integer.toString(n%3) + answer;   
                }
                break;
            }
            
            int tmp = rest[n%3];
            
            if(n%3==0){
                n = n/3-1;
            } else{
                n = n/3;
            }
            
            answer =  Integer.toString(tmp) + answer;
        }
        
        return answer;
    }
}
