package week7;

import java.util.Scanner;

public class BOJ3085_사탕게임 {
	
	public static char[][] map;	
	public static int N;			
	public static int max = 0;		

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	
		map = new char[N][N];


		for(int i=0; i<N; i++) {
			String str = sc.next();
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = str.charAt(j);
			}
		}


		//완탐으로 하나씩 다 바꿔보면서 그때마다 체크한다
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				//가로로 바꾸고
				char temp = map[i][j];
				map[i][j] = map[i][j+1];
				map[i][j+1] = temp;
			
				findMax();

				//복구
				temp = map[i][j];
				map[i][j] = map[i][j+1];
				map[i][j+1] = temp;
			}
		}

		for(int i=0; i < N; i++) {
			for(int j= 0; j < N-1; j++) {
				//세로로 바꿔보고
				char temp = map[j][i];
				map[j][i] = map[j+1][i];
				map[j+1][i] = temp;

				
				findMax(); //체크

				//복구
				temp = map[j][i];
				map[j][i] = map[j+1][i];
				map[j+1][i] = temp;
			}
		}

		System.out.println(max);
	}

	public static void findMax() {
		
		//가로로 연속된게 있는지 체크하고 그때의 최대값
		for(int i=0; i<N; i++) {
			int count = 1;
			for(int j=0; j<N-1; j++) {
				if(map[i][j] == map[i][j+1])
					count ++;
				else count = 1;
				
				max = Math.max(max, count);
			}
		}
		
		//세로로 체크 , 최대값찾기
		for(int i=0; i<N; i++) {
			int count2 = 1;
			for(int j=0; j<N-1; j++) {
				if(map[j][i] == map[j+1][i])
					count2 ++;
				else 
					count2 = 1;
				max = Math.max(max, count2);
			}
		}
	}


}