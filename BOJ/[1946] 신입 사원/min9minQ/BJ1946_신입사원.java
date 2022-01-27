package time25;

import java.util.Scanner;

public class BJ1946_신입사원 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int result = N; // 전체인원
			int[] arr = new int[N+1];
			for(int i = 0; i < N; i++) {
				int document = sc.nextInt();
				int interview = sc.nextInt();
				arr[document] = interview;
			}
			for(int i = 2; i <= N; i++) { // 서류 1등의 면접 순위보다 더 높으면(숫자가 작으면 합격)
				
				if(arr[i] > arr[1]) result--;
				if(arr[i] < arr[1]) arr[1] = arr[i];
			}
			System.out.println(result);
		}

	}

}
