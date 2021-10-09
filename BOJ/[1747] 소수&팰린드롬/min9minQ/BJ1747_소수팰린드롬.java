package time11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1747_소수팰린드롬 {
	static int N;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		if (N == 1)
			System.out.println(2);
		else {
			for (int i = N;; i++) {
				if (isPalind(i) && isPrime(i)) {
					System.out.println(i);
					return;
				}
			}
		}
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPalind(int num) {

		String str = Integer.toString(num);
		
		int cnt = str.length();
		for (int i = 0; i <= cnt / 2; i++) {
			if (str.charAt(i) != str.charAt(cnt - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
