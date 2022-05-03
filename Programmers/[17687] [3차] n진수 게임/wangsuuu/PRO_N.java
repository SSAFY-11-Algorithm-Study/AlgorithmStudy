//Integer.toString(정수, radix) -> 정수를 radix진법의 문자열로 변환해 줌 

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        String list = "";
        for(int i=0; i<=t*m; i++){
            list += Integer.toString(i, n);
        }
        list = list.toUpperCase(); // String이므로, list에 다시 할당해 주어야 함!
        int cnt=0;
        int turn = p-1;
        while(cnt<t){
            answer += list.substring(turn, turn+1);
            turn += m;
            cnt++;
        }
        return answer;
    }
}
