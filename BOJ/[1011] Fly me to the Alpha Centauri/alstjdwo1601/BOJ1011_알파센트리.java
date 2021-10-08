package week11;

import java.util.Scanner;

//도저히 점화식은 못세우겠고,, 블로그보고 간신히 이해만했음 ㅠ
public class BOJ1011_알파센트리 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for(int tc = 1; tc <= TC; tc++) {

			int start = sc.nextInt();
			int end = sc.nextInt();

			int dist = end - start ; 

			int max = (int)Math.sqrt(dist);	// 소수점 버림

			if(max == Math.sqrt(dist)) {
				System.out.println(max * 2 - 1);
			}
			else if(dist <= max * max + max) {
				System.out.println(max * 2);
			}
			else {
				System.out.println(max * 2 + 1);
			}
		}
	}
}
