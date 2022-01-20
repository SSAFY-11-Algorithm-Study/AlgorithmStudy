import java.util.Scanner;


public class BOJ2661_GoodNumber {
	
	static int N;
	static String answer;
	static boolean flag=false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dfs("");
		System.out.println(answer);
	}
	private static void dfs(String string) {
		if(flag)
			return;
		if(string.length() == N) { //종료 조건
			if(GoodNumber(string)) {
				flag=true;
				answer = string;
				return;
			}
		}
		for(int i=1; i<=3; i++) {
			if(GoodNumber(string+i))
				dfs(string + i);
		}
	}
	private static boolean GoodNumber(String string) {
		int n = string.length();
		for(int i=1; i<=n/2; i++) {
			if(string.substring(n-2*i, n-i).equals(string.substring(n-i)))
				return false;
		}
		return true;
	}
}
