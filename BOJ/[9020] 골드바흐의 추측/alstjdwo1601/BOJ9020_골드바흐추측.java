package week17;

import java.util.Scanner;

public class BOJ9020_골드바흐추측 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc = 1 ; tc <=TC ; tc++) {
			int num = sc.nextInt();
			
			//정수를 일단 절반씩 나눔
			int half1 = num/2;
			int half2 = num/2;
			
			//System.out.println(half1 + " " + half2);
			while(true) {
				if(isPrime(half1) && isPrime(half2))
					break;
				
				else {
					half1--;
					half2++;
				}
			}
			
			System.out.println(half1 + " " + half2);
		}
	}

	private static boolean isPrime(int n) {
		boolean primeFlag = true;
		for(int i = 2 ; i <= n/2 ; i++) {
			if(n%i ==0) {
				primeFlag = false;
				break;
			}
		}
		
		if(primeFlag) return true;
		else return false;
	}
}
