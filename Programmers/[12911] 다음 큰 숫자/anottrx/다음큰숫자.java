// 시간 초과 => 푸는 방법 바꿔야 함
class Solution {
    public int solution(int n) {
        int answer = 0;
        int cnt = change(n);
        while(true) {
            n++;
            int cnt2 = change(n);
            if(cnt==cnt2) {
                answer = n;
                break;
            }
        }
        return answer;
    }
    
    public static int change(int n){ // 이진법으로 변환 후 1의 개수 세기
        int cnt = 1;
        StringBuilder sb =  new StringBuilder();
        while(true) {
            if(n/2>0) {
                sb.insert(0, String.valueOf(n%2));
                n = n / 2;
            } else {
                // sb.insert(0, "1");
                break;
            }
        }
        for(int i=0; i<sb.toString().length(); i++) {
            if(sb.toString().charAt(i)=='1') {
                cnt++;
            }
        }
        return cnt;
    }
}
