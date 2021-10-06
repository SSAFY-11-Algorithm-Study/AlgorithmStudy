package week11;

import java.util.Scanner;

public class BOJ1747_소수팰린드롬 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		if(N==1)
			System.out.println(2);
		else
			findPrime(N);
	}


	private static void findPrime(int n) {

		int target = n-1;
		while(true) {
			target++;
			boolean flag = true;
			for(int i = 2 ; i*i <= target ; i++) {
				if(target % i == 0) {
					flag = false;
					break;
				}	
			}

			if(flag) { //소수라면 펠린드롬 검사
				if(isPalindrome(target)) {
					System.out.println(target);
					return;
				}
			}
			else continue;


		}
	}


	private static boolean isPalindrome(int target) {
		String str = Integer.toString(target);
		int size = str.length();
		
		boolean flag = true;
		for(int i = 0 ; i < str.length()/2  ; i++) {
			if(str.charAt(i) != str.charAt(size-i-1)) {
				flag = false;
				break;
			}
		}
		if(flag) 
			return true;

		else 
			return false;

	}
}
