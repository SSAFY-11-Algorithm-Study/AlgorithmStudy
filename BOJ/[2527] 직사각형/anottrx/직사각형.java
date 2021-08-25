// 출처: 백준저지 2527번 직사각형 https://www.acmicpc.net/problem/2527

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2527 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] rect1X = new int[2]; // 첫번째 직사각형의 x
			int[] rect1Y = new int[2]; // 첫번째 직사각형의 y
			int[] rect2X = new int[2]; // 두번째 직사각형의 x
			int[] rect2Y = new int[2]; // 두번째 직사각형의 y
			for (int i = 0; i < 2; i++) { // 첫번째 직사각형의 x좌표와 y좌표 따로 입력받기
				rect1X[i] = Integer.parseInt(st.nextToken());
				rect1Y[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < 2; i++) { // 두번째 직사각형의 x좌표와 y좌표 따로 입력받기
				rect2X[i] = Integer.parseInt(st.nextToken());
				rect2Y[i] = Integer.parseInt(st.nextToken());
			}

			if ((rect1X[0] > rect2X[1]) || (rect1X[1] < rect2X[0]) || (rect1Y[0] > rect2Y[1]) || (rect1Y[1] < rect2Y[0])) {
				// 1. 공통부분이 없음 - d
				// x축 또는 y축이 무조건 작거나 큰 경우
				System.out.println('d');
			} else if ((rect1X[0] == rect2X[1] && rect1Y[0] == rect2Y[1])
					|| (rect1X[1] == rect2X[0] && rect1Y[1] == rect2Y[0])
					|| (rect1X[0] == rect2X[1] && rect1Y[1] == rect2Y[0])
					|| (rect1X[1] == rect2X[0] && rect1Y[0] == rect2Y[1])) {
				// 2. 점 - c
				// 점 1개만 일치할 경우
				System.out.println('c');
			} else if ((rect1X[0] == rect2X[1]) || (rect1X[1] == rect2X[0]) || (rect1Y[0] == rect2Y[1]) || (rect1Y[1] == rect2Y[0])) {
				// 3. 선분 - b
				// 아래 또는 위 x축 또는 y축이 각각 위 또는 아래 x축 또는 y축과 겹칠 때
				System.out.println('b');
			} else { // 그외는 겹치는 부분이 존재한다
				// 4. 직사각형 - a
				System.out.println('a');
			}
		}
	}
}
