import java.util.*;
//테케 다 맞는데 돌려보면 28점ㅠ
class Solution {
    public int solution(String s) {
        int answer = 100000;


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
                    num++; idx++;
                }

                if(num==1){
                    ans += st;
                    idx += i;
                } else{
                    ans += num + st;
                    idx = idx+2*i-1;
                }
            }
            answer = Math.min(answer, ans.length());
        }

        return answer;
    }
}
