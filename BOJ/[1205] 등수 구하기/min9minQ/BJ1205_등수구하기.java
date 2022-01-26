package time25;

import java.util.Scanner;

public class BJ1205_등수구하기 {

	private static int N, score, P;
	private static int[] list;
	private static int answer = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		score = sc.nextInt();
		P = sc.nextInt();
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		}

		if (N == P && score <= list[list.length - 1])
			answer = -1;
		else if (N == 0) answer = 1;
		else {
			int rank = 1;
			for (int i = 0; i < N; i++) {
				if (list[i] <= score) {
					answer = rank;
					break;
				} else {
					rank++;
				}
			}
		}
		System.out.println(answer);
	}

}
