import java.util.Scanner;

// 블로그 참고해서 품(https://st-lab.tistory.com/79)
// 수학 규칙 찾아서 푸는 유형!

public class BOJ1011_AlphaCentauri {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int x=0, y=0;
		for (int i = 0; i < T; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			
			int dist = y-x;
			int max = (int)Math.sqrt(dist);
			int cnt = max*2 - 1;
			
			if(max*max == dist) { //dist가 제곱수이면
				cnt = cnt;
			} else {
				
				if(dist - max*max <= max) // cnt가 1만 증가
					cnt += 1;
				else //cnt가 2 증가
					cnt += 2;
			}
			System.out.println(cnt);
		}
	}
}
