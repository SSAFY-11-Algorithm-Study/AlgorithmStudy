// 여러 블로그 참고했습니다

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] numStr = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			numStr[i] = String.valueOf(numbers[i]);
		}
		Arrays.sort(numStr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2);
			}
		});
		if (numStr[0].equals("0")) {
			answer = "0";
		} else {
			StringBuilder sb = new StringBuilder();
			for (String str : numStr) {
				sb.append(str);
			}
			answer = sb.toString();
		}
        return answer;
    }
    
}
