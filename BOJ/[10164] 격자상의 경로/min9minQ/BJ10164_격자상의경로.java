package time25;

import java.util.Arrays;
import java.util.Scanner;

public class BJ10164_격자상의경로 {
	
	private static int N,M,K;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		int answer = 0;
		
		if(K == 0) {
			answer = way(1,N*M);
		} else {
			answer = way(1,K) * way(K,N*M);
		}
		System.out.println(answer);
	}
	
	private static int[] Point (int num) {
		int[] point = new int[2];
		point[0] = (num-1)/M;
		point[1] = (num-1)%M;
		return point;
	}
	
	private static int way(int start, int end) {
		int temp = end -start + 1;
		int[] des = Point(temp);
		int[][] map = new int[des[0]+1][des[1]+1];
		
		//맨 좌측 맨 위 1로 채워주기
		Arrays.fill(map[0], 1);
		for(int i = 0; i < map.length; i++) {
			map[i][0] = 1;
		}
		
		for(int i = 1; i < map.length; i++) {
			for(int j = 1; j < map[0].length; j ++) {
				map[i][j] = map[i-1][j] + map[i][j-1];
			}
		}
		
		return map[des[0]][des[1]];
		
	}

}
