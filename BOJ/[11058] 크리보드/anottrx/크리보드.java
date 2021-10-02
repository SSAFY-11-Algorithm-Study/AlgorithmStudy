// 블로그 참고했습니다

import java.util.Scanner;

public class BOJ11058 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		long[] d = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			d[i] = i; // 1~6을 위해서 우선 해당 수를 저장
		}

		for (int i = 7; i <= N; i++) {
			d[i] = Math.max(Math.max(d[i - 3] * 2, d[i - 4] * 3), d[i - 5] * 4); // 복사붙여넣기한 횟수가 1번, 2번, 3번일 때를 비교
		}

		System.out.println(d[N]);
	}
}
