// 여러 블로그를 참고했습니다
/*
  * 아직 해결 못한 테스트케이스 :
  * ABBAA -> answer=4 wrong=6
  * ABABAAAAAB -> answer=8 wrong=10
*/

class Solution {
	public int solution(String name) {
		int answer = 0;
		int[] nameCnt = new int[name.length()]; // 각 알파벳 당 ABC 또는 AZY 순서 중 최소인 방향 개수 저장
		for (int i = 0; i < name.length(); i++) {
			nameCnt[i] = Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
		}

		int n = name.length() - 1;
		for (int i = 0; i < name.length(); i++) {
			answer = answer + nameCnt[i];

			int j = i + 1; // 현재 위치 다음부터
			while (j < name.length() && nameCnt[j] == 0) {
				j++; // 연속된 A 개수
			}
			n = Math.min(n, i + i + name.length() - j); // 방향 정하기
		}
		answer = answer + n;
		return answer;
	}
}
