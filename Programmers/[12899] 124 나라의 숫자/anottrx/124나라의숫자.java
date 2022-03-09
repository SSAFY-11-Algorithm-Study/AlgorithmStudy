// https://programmers.co.kr/questions/19755

class Solution {
    public String solution(int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder(); // 시간 초과 막기 위함

        while (n > 0) {
            int cur = n % 3;
            if (cur == 1 || cur == 2) {
                sb.insert(0, Integer.toString(cur)); // 맨앞에 넣기
            } else {
                n--; // 이 코드가 없으면 9는 44가 된다
                sb.insert(0, '4');
            }
            n = n / 3;
        }
        answer = sb.toString();
        return answer;
    }
}
