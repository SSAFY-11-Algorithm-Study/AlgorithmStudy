//오랜만의 선녀문제 감사합니다,,^^,,,
class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        for(int i=0; i<arr.length; i++){
            answer = Math.max(answer, arr[i]);
        }
        
        while(true){
            int cnt=0;
            for(int i=0; i<arr.length; i++){
                if(answer % arr[i] == 0){ //나누어 떨어지면
                    cnt++;
                } else{
                    break;
                }
            }
            if(cnt==arr.length){ //모든 수가 다 나누어 떨어졌으면 -> 그 수가 최소공배수
                break;
            }
            answer++;
        }
        return answer;
    }
}
