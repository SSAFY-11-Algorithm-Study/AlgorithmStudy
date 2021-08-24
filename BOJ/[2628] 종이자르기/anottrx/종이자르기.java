// 출처: 백준저지 2628번 종이자르기 https://www.acmicpc.net/problem/2628

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2628 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int W = sc.nextInt(); // 가로
		int H = sc.nextInt(); // 세로
		int N = sc.nextInt(); // 점선 개수
		ArrayList<Integer> widthCut = new ArrayList<>();
		ArrayList<Integer> heightCut = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int wh = sc.nextInt();
			int len = sc.nextInt();
			if (wh == 0) { // 가로
				widthCut.add(len);
			} else { // 세로
				heightCut.add(len);
			}
		}
		int maxW = getMaxLen(H, widthCut); // 세로 y축 기준으로 자르기
		int maxY = getMaxLen(W, heightCut); // 가로 x축 기준으로 자르기

		System.out.println(maxW * maxY); // 답 출력
	}

	private static int getMaxLen(int N, ArrayList<Integer> cuts) { // N은 길이, cuts는 점선
		int cutCnt = cuts.size();
		if (cutCnt == 0) { // 한번도 잘리지 않은 경우
			return N;
		}
		Collections.sort(cuts); // 정렬

		int max = cuts.get(0); // 처음 잘린 길이가 우선 제일 크다고 초기화
		for (int i = 1; i < cutCnt; i++) { // 첫번째와 두번째 차이, ...
			max = Math.max(max, cuts.get(i) - cuts.get(i - 1));
		}
		max = Math.max(max, N - cuts.get(cutCnt - 1)); // 마지막 잘린 것 길이

		return max;
	}

}
