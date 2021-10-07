import java.util.Scanner;

//에라토스테네스의 체 활용 : 소수 판별하기 위해서 "그 수의 제곱근"까지만 나누어 떨어지는지 봄

public class BOJ1747_PrimeAndPalin {

	public static void main(String[] args) {
		  
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		while(true) {
			
			if(isPrime(N) && isPalindrome(Integer.toString(N))) {
				break;
			}
			N++;
		}
		System.out.println(N);
	}

	private static boolean isPalindrome(String string) {
		
		int len = string.length();
		for (int i = 0; i < len/2; i++) {
			if(string.charAt(i) != string.charAt(len-1-i))
				return false;
		}
		return true;
	}

	private static boolean isPrime(int n) {
		
		if(n==1)
			return false;
		
		for (int i = 2; i <=Math.sqrt(n); i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
}
