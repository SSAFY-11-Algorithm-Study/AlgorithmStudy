class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for (int i = 0; i < number.length() - k; i++) {
            int max = 0;
            for (int j = idx; j <= i + k; j++) {
                if ((number.charAt(j) - '0') > max) {
                    max = number.charAt(j) - '0';
                    idx = j + 1;
                    if (max == 9) { // 9라면 무조건 max
                        break; // 시간 줄이기
                    }
                }
            }
            sb.append(String.valueOf(max));
        }
        answer = sb.toString();

        int cntZero = 0;
        for (int i = 0; i < answer.length(); i++) { // 앞자리가 0인지 확인하고 있다면 없애기
            if (answer.charAt(i) == '0') {
                cntZero++;
            } else {
                break;
            }
        }
        answer = (answer.length() == cntZero) ? "0" : answer.substring(cntZero); // 0으로만 이루어졌다면 0 하나 출력

        return answer;
    }
}
