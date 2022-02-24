// ... 시간초과?
// 바이너리 서치로 했는데ㅠㅠ..

package time28;

import java.util.Arrays;
import java.util.Scanner;

public class BJ10815_숫자카드 {
	
	private static int N,M;
	private static int[] arr;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int find = BS(sc.nextInt());
			if(find != -1)
				System.out.print(1 + " ");
			else
				System.out.print(0 + " ");
		}
		
	}

	private static int BS(int target) {
		int start = 0;
		int end = arr.length - 1;
		int mid;
		
		while(start <= end) {
			mid = (start + end) / 2;
			if(arr[mid] < target)
				start = mid + 1;
			else if(arr[mid] > target)
				end = mid - 1;
			else
				return mid;
		}
		return -1;
	}

}
