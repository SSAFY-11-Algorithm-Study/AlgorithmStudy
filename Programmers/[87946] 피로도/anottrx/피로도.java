class Solution {

	static int max, selected[];
	static boolean[] visited;

	public int solution(int k, int[][] dungeons) {
		int answer = -1;

		selected = new int[dungeons.length]; // 순열 위함
		visited = new boolean[dungeons.length]; // 순열 위함
		max = 0; // 답

		getDungeonsOrder(0, k, dungeons);
		answer = max;

		return answer;
	}

	public static void getDungeonsOrder(int cnt, int k, int[][] dungeons) { // 던전 탐험 순서를 순열로 구하기
		if (cnt == dungeons.length) {
			max = Math.max(max, getCnt(k, dungeons)); // 최댓값
			return;
		}

		for (int i = 0; i < dungeons.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[cnt] = i;
				getDungeonsOrder(cnt + 1, k, dungeons);
				visited[i] = false;
			}
		}
	}

	public static int getCnt(int k, int[][] dungeons) { // 던전 탐험 횟수를 구하기
		int cnt = 0, cur, leastFatigue, lose;

		for (int i = 0; i < dungeons.length; i++) {
			cur = selected[i];
			leastFatigue = dungeons[cur][0];
			lose = dungeons[cur][1];
			if (k >= leastFatigue) { // 최소한의 피로도
				k = k - lose; // 소모되는 피로도
				cnt++;
			} else {
				break;
			}
		}
		return cnt; // 횟수 리턴
	}
}
