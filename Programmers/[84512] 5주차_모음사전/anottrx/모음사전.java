// https://bangu4.tistory.com/241 참고했습니다

import java.util.Arrays;

class Solution {
	public int solution(String word) {
		int answer = 0;
		char[] chars = { 'A', 'E', 'I', 'O', 'U' };
		for (int i = 0; i < word.length(); i++) {
			if (i == 0) {
				answer = answer + Arrays.binarySearch(chars, word.charAt(0)) * 781 + 1;
			} else if (i == 1) {
				answer = answer + Arrays.binarySearch(chars, word.charAt(1)) * 156 + 1;
			} else if (i == 2) {
				answer = answer + Arrays.binarySearch(chars, word.charAt(2)) * 31 + 1;
			} else if (i == 3) {
				answer = answer + Arrays.binarySearch(chars, word.charAt(3)) * 6 + 1;
			} else if (i == 4) {
				answer = answer + Arrays.binarySearch(chars, word.charAt(4)) + 1;
			}
		}
		return answer;
	}
}
