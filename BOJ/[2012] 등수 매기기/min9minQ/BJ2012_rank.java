// 순열로 모든 경우 -> 시간 초과...
// 어차피 최솟값 찾는 것 이므로 정렬한후 12345 차례대로 비교해준게 최소
// 왜 sum을 int로 하면 틀렸습니다가 나오는지...
// -> 50만 이라서 1~50만 더하면 범위가 넘어가게 되는것..?
package time7;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2012_rank {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] expectedrank = new int[N];
		int[] rank = new int[N];
		for(int i = 0; i < N; i++) {
			rank[i]=i+1;
		}
		for(int i = 0; i < N; i++) {
			expectedrank[i]=sc.nextInt();
		}
		Arrays.sort(expectedrank);
		long sum = 0;// sum을 하면 틀렸습니다... 
		for(int i = 0; i < N; i++) {
			sum += Math.abs(rank[i]-expectedrank[i]);
		}
		System.out.println(sum);
	
	}
}
/* 순열로 풀었을 때
public class BJ2012_rank {
	
	private static int N;
	private static int[] rank;
	private static int[] nums;
	private static int[] expectedrank;
	private static boolean[] isSelected;
	private static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		rank = new int[N];
		nums = new int[N];
		expectedrank = new int[N];
		isSelected = new boolean[N];
		for(int i = 0; i < N; i++) {
			nums[i]=i+1;
		}
		
		for(int i = 0; i < N; i++) {
			expectedrank[i] = sc.nextInt();
		}
		
		difference(0);
		System.out.println(min);
	}

	private static void difference(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				sum += Math.abs(expectedrank[i]-rank[i]); 
			}
			min = Math.min(min, sum);
			
			return;
		}
		for(int i = 0; i < N; i++) {
			if(isSelected[i]==true) {
				continue;
			}
			rank[cnt] = nums[i];
			isSelected[i] = true;
			difference(cnt+1);
			isSelected[i] = false;
		}
	}
}
*/