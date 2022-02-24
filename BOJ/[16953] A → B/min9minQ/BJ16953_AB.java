package time28;

import java.util.Scanner;

public class BJ16953_AB {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		int answer = 1;

		while (A != B) {
			
			if (B < A) {
				System.out.println(-1);
				System.exit(0);
			}
			
			if (B % 2 == 0)
				B /= 2;
			else if (B % 10 == 1)
				B /= 10;
			else {
				System.out.println(-1);
				System.exit(0);
			}
			answer++;
		}

		System.out.println(answer);

	}

}
