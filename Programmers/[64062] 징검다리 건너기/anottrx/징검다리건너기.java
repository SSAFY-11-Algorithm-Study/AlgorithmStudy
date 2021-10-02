// 블로그 참고했습니다

import java.util.Arrays;

class Solution {

	public int solution(int[] stones, int k) {
		int answer = 0;

		int start = 1;
		int end = Arrays.stream(stones).max().getAsInt();
		int mid = (start + end) / 2 - 1;

		while (start <= end) {
			mid = (start + end) / 2;
			int cnt = 0;
			for (int i = 0; i < stones.length; i++) {
				if (stones[i] - mid <= 0) {
					cnt++;
				} else {
					cnt = 0;
				}
				if (cnt >= k) {
					break;
				}
			}
			if (cnt >= k) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		answer = start;

		return answer;
	}
}
