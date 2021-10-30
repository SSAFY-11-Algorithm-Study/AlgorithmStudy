// 테스트 케이스 12-17 계속 틀렸었는데 캐스팅 문제였음(long)
package time14;

public class PM_멀쩡한사각형 {

	static int W = 8;
	static int H = 12;

	public static void main(String[] args) {
		long answer = 0;
		
		long num = gcd(W,H);
		answer = (long)W*H-num*((long)(W/num)+(long)(H/num)-1);
		
		
		System.out.println(answer);
	}

	public static long gcd(long x, long y) {
		long temp;
		long a = x > y ? x : y;
		long b = x > y ? y : x;

		while (a % b != 0) {
			temp = b;
			b = a % b;
			a = temp;
		}

		return b;

	}

}
