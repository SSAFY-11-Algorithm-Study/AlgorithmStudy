package week10;

import java.util.Arrays;
import java.util.Scanner;


//dp로 풀려고했는데 자꾸 에러나서 고치는중..
public class BOJ1697_숨바꼭질 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int subin = sc.nextInt();
		int brother =sc.nextInt();

		int [] dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[subin] = 0;
		dp[subin+1] = 1;
		dp[subin-1] = 1;

		if(subin == brother) {
			System.out.println(0);
			return;
		}
		else if(subin < brother) {
			for(int i = subin+2 ; i <100000 ; i ++) {
				//짝수인경우에는 절반인 곳에서 순간이동해올수 있음
				if(i%2 ==0) {
					//앞,뒤,순간이동해오는 지점중 최소값 +1 
					int temp = Math.min(dp[i-1], dp[i+1]);
					dp[i] = Math.min(temp, dp[i/2]) +1;
				}
				else {
					dp[i]= Math.min(dp[i-1], dp[i+1]) +1;
				}
			}

			//최신화가 안된 뒤쪽배열값을 가져다쓴경우엔 제대로 안나와서
			//한번더돌리면 됨
			for(int i = subin+2 ; i <100000 ; i ++) {
				//짝수인경우
				if(i%2 ==0) {
					int temp = Math.min(dp[i-1], dp[i+1]);
					dp[i] = Math.min(temp, dp[i/2]) +1;
				}
				else {
					dp[i]= Math.min(dp[i-1], dp[i+1]) +1;
				}
			}

			dp[100000] = Math.min(dp[99999], dp[50000]);

			for(int i = subin-1 ; i <=20; i ++) {
				//System.out.printf("%d : %d\n",i,dp[i]);
			}
			System.out.println(dp[brother]);
		}
		//동생이 더 앞에있으면 뒤로 한칸씩갈수밖에없음
		else {
			System.out.println(Math.abs(brother - subin));
		}
	}
}
