import java.util.*;

//처음 코드는 abcabcabcc -> 2abcabcc로 답이 나오는걸 확인해서, 인덱싱 고쳐줌
//통과

class Solution {
    public int solution(String s) {
        int answer = 100000;
       
        if(s.length()==1) //테케 5번 : 문자열 길이가 1인 경우, 이 예외 처리 안 해주면 답이 10000이 나와버림
            return 1;

        for(int i=1; i<=s.length()/2; i++){

            int idx = 0;
            String ans = "";

            while(idx<=s.length()){

                if(idx+i > s.length()) {
                    ans += s.substring(idx);
                    break;
                }

                String st = s.substring(idx, idx+i); //비교가 되는 기준
                int num=1;
                while(idx+2*i<=s.length() && s.substring(idx+i, idx+2*i).equals(st)){
                    num++;
                    idx += i;
                }

                if(num==1){
                    ans += st;
                } else{
                    ans += num + st;
                }
                idx += i; //반복이 끝난 그 다음 위치부터 살펴봐야 하므로
            }
            answer = Math.min(answer, ans.length());
        }

        return answer;
    }
}
