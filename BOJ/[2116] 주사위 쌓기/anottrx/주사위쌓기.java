//출처: 백준저지 2116번 주사위 쌓기 https://www.acmicpc.net/problem/2116

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2116 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, answer;
	static int[][] dice;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dice = new int[N][7]; // N개의 주사위. 1~6까지 해당 주사위 면의 반대편 면 저장
		int[] input = new int[6];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) { // 우선 주사위 하나 입력 받기
				input[j] = Integer.parseInt(st.nextToken());
			}
			dice[i] = setDiceNum(input); // 해당 주사위의 윗면 아랫면 정리하기
		}

		answer = 0; // 답 0으로 초기화
		for (int i = 1; i < 7; i++) {
			findMax(0, dice[0][i], 0); // 0번째 주사위, 0번째 주사위의 6면 모두 확인, 옆면 합
		}

		System.out.println(answer); // 답 출력
	}

	private static int[] setDiceNum(int[] input) {
		int[] dInput = new int[7];
		// 입력 순서: A B C D E F
		// 윗면과 해당 아랫면: AF(0,5) BD(1,3) CE(2,4)
		dInput[input[0]] = input[5];
		dInput[input[5]] = input[0];
		dInput[input[1]] = input[3];
		dInput[input[3]] = input[1];
		dInput[input[2]] = input[4];
		dInput[input[4]] = input[2];

		return dInput; // 배열 정리 후 리턴
	}

	private static void findMax(int diceNum, int under, int sum) {
		// 주사위 번호(0 ~ N-1), 아랫면, 옆면 합
		if (diceNum == N) { // 마지막 주사위까지 올렸다면
			answer = Math.max(answer, sum); // 더 큰 수 저장한 후 리턴
			return;
		}

		int front = dice[diceNum][under]; // 윗면 구하기
		if (under != 6 && front != 6) { // 윗면 아랫면 모두 6이 아니라면
			findMax(diceNum + 1, front, sum + 6); // 가장 큰 옆면은 6
		} else if (under != 5 && front != 5) { // 윗면 아랫면 모두 5도 아니라면
			findMax(diceNum + 1, front, sum + 5); // 가장 큰 옆면은 5
		} else { // 그 외의 경우는 무조건 4
			findMax(diceNum + 1, front, sum + 4);
		}
	}

}
