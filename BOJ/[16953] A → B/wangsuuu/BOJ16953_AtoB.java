import java.util.Scanner;

public class BOJ16953_AtoB {
	
	static long A, B, answer;
	static final int max = 1000000000;
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		
		dfs(A, 0);
		System.out.println(-1);
	}

	private static void dfs(long n, int cnt) {
		
		if(n==B) {
			System.out.println(cnt+1);
			System.exit(0);
		}
		
		if(n > max)
			return;
		
		dfs(n*2, cnt+1); //++cnt랑 cnt+1이랑 결과가 달라지는 이유..?
		dfs(10*n+1, cnt+1);
	}
}
