// 여러 블로그 참고했습니다

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
		int max = 0;
		int idx = 0;
		for (int i = 0; i < number.length() - k; i++) {
			max = 0;
			for (int j = idx; j <= i + k; j++) {
				if ((number.charAt(j) - '0') > max) {
					max = number.charAt(j) - '0';
					idx = j + 1;
				}
			}
			sb.append(String.valueOf(max));
		}
		answer = sb.toString();
		if(answer.charAt(0)=='0') {
			answer = answer.substring(1);
		}
        return answer;
    }
}
