package time17;

import java.util.Scanner;

public class BJ9020_골드바흐의추측 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			int n = sc.nextInt();

			//에라토스의 체로 소수 체크 (소수가 아니면 true)
			boolean[] nums = new boolean[n + 1];
			for (int i = 2; i < Math.sqrt(n + 1); i++) {
				if (!(nums[i])) {
					for (int j = 2; i * j < n + 1; j++) {
						nums[i * j] = true;
					}
				}
			}
			
			int half = n/2;
			int cnt = 0;
			
			while(true) {
				if((nums[half-cnt]) == false && (nums[half+cnt]) ==false){
					
					System.out.println((half-cnt)+" "+ (half+cnt));
					break;
				}
				cnt++;
			}

		}

	}

}
