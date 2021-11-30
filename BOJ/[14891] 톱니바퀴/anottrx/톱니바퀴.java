// https://bcp0109.tistory.com/33 참고했습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {

	static String[] gear;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new String[4];
		for (int i = 0; i < 4; i++) {
			gear[i] = br.readLine();
		}
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken()) - 1;
			int moveDir = Integer.parseInt(st.nextToken());

			// 움직여야하는 톱니바퀴에 맞닿은 톱니바퀴도 이동해야하는지 확인
			checkBefGear(gearNum, moveDir * (-1)); // 이전
			checkNextGear(gearNum, moveDir * (-1)); // 다음

			// 톱니바퀴를 해당 방향으로 이동시킨다
			moveGear(gearNum, moveDir);
		}

		int answer = 0, score = 1;
		for (int i = 0; i < 4; i++) { // 12시 방향
			if (gear[i].charAt(0) == '1') {
				answer = answer + score;
			}
			score = score * 2;
		}
		System.out.println(answer);
	}

	private static void checkBefGear(int gearNum, int moveDir) {
		if (gearNum <= 0) {
			return;
		}
		int befGearNum = gearNum - 1;
		if (befGearNum >= 0 && gear[befGearNum].charAt(2) != gear[gearNum].charAt(6)) {
			checkBefGear(befGearNum, moveDir * (-1));
			moveGear(befGearNum, moveDir);
		}
	}

	private static void checkNextGear(int gearNum, int moveDir) {
		if (gearNum >= 3) {
			return;
		}
		int nextGearNum = gearNum + 1;
		if (nextGearNum <= 3 && gear[nextGearNum].charAt(6) != gear[gearNum].charAt(2)) {
			checkNextGear(nextGearNum, moveDir * (-1));
			moveGear(nextGearNum, moveDir);
		}
	}

	private static void moveGear(int gearNum, int moveDir) {
		if (moveDir == 1) {
			gear[gearNum] = gear[gearNum].charAt(7) + gear[gearNum].substring(0, 7);
		} else {
			char firstChar = gear[gearNum].charAt(0);
			gear[gearNum] = gear[gearNum].substring(1, 8) + firstChar;
		}
	}
}
