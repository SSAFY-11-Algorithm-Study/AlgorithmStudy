// 분할정복 떠올라서 분할정복 블로그 참조..
// Z와 비슷.

package time21;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2448_별찍기11 {

	private static String[][] map;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		map = new String[N][N*2-1];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2*N-1; j++) {
				map[i][j] = " ";
			}
		}
		
		star(0, N-1, N); // 
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N * 2-1; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	private static void star(int row, int col, int n) {
		//가장 작은 단위이면 찍어주기.
		if(n==3) {
			map[row][col] = "*";
			
			map[row+1][col+1] = map[row+1][col-1] = "*";
			
			map[row+2][col-2] = map[row+2][col-1] = map[row+2][col] = map[row+2][col+1] = map[row+2][col+2] = "*";
		} else {
			int half = n/2;
			
			star(row,col,half);
			star(row + (n/2), col + (n/2) , half);
			star(row + (n/2), col - (n/2) , half);
		}
		
	}

}
