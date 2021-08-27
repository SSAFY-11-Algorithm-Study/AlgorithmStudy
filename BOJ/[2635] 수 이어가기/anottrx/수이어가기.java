// 출처: 백준저지 2635번 수 이어가기  https://www.acmicpc.net/problem/2635

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2635 {
	static int N, answer;
	static ArrayList<Integer> nums, temp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 첫번째 수
		nums = new ArrayList<>(); // 답. 최대 개수의 수 모음
		temp = new ArrayList<>(); // 임시로 수를 저장할 것
		answer = 0; // 답. 최대 개수

		for (int i = 0; i <= N; i++) { // 두번째 수를 0부터 N까지 모두 시도하기
			int cnt = 1; // 처음에 개수는 1로 초기화
			temp.clear(); // 매번 초기화
			temp.add(N); // 첫번째 수 N과 두번째 수 i를 저장
			temp.add(i);
			findMax(N, i, cnt); // 첫번째 수, 두번째 수, 횟수
		}

		System.out.println(answer); // 답 출력
		for (int n : nums) {
			System.out.print(n + " ");
		}
	}

	private static void findMax(int first, int second, int cnt) {
		// 첫번째 수, 두번째 수, 횟수
		if (second < 0) { // 두번째수가 음의 정수라면 그만두기
			if (answer <= cnt) { // 횟수가 크다면
				answer = cnt;
				nums.clear(); // 답이 될 nums를 비우기
				for (int n : temp) { // 답이 여러개인 경우가 있기 때문에 값을 복사
					if (n >= 0) { // 음수인 second값은 제외
						nums.add(n);
					}
				}
			}
			return;
		}

		int next = first - second; // 다음 수는 첫번째수-두번째수
		temp.add(next); // 해당 수를 temp에 저장
		findMax(second, next, cnt + 1);
	}
}
