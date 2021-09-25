// 비슷한 문제(랜선 자르기) -> 시간초과 -> 이분탐색 적용.
package time9;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2805_treeCut {
	
	static int N,M;
	static int[] tree;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		tree = new int[N];
		
		int start = 0;
		int end = 0;
		
		for(int i = 0; i < N; i++ ) {
			tree[i] = sc.nextInt();
			end = Math.max(end, tree[i]);
		}
		
		Arrays.sort(tree);
		
		int result = 0;
		
		while(start <= end) {
			
			long total = 0;
			int mid = (start + end) / 2;
			for(int i = 0; i < N; i++) {
				if(tree[i] > mid) {
					total += tree[i] - mid;
				}
			}
			if(total < M) {
				end = mid -1;
			} else {
				start = mid + 1;
				result = mid;
			}
			
		}
		System.out.println(result);
	}

}
