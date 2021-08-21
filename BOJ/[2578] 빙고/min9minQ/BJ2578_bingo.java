package time4;

import java.util.Scanner;

public class BJ2578_bingo {
	
	static int bingo = 0;
	static int count = 0;
	static int[][] map = new int[5][5];

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 5; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int[] say = new int[25];
		for(int i = 0; i < 25; i++) {
			say[i] = sc.nextInt();
		}
		
		for(int k = 0; k < 25; k ++) {
			int temp = say[k];
			count ++;
			for(int i = 0; i < 5; i ++) {
				for(int j = 0; j < 5; j ++) {
					if(map[i][j]==temp) {
						map[i][j] = 0;
					}
				}
			}
			rowCheck();
			colCheck();
			rCrossCheck();
			lCrossCheck();
			
			if(bingo >= 3) {
				System.out.println(count);
				break;
			}
			bingo = 0;
			
		}
		

	}
	static void rowCheck() {
		for(int i = 0; i < 5; i ++) {
		if(map[i][0]==0 && map[i][1]==0 && map[i][2]==0 && map[i][3]==0 && map[i][4]==0)
			bingo ++;
		}
	}
	
	static void colCheck() {
		for(int j = 0; j < 5; j ++) {
		if(map[0][j]==0 && map[1][j]==0 && map[2][j]==0 && map[3][j]==0 && map[4][j]==0)
			bingo ++;
		}
	}
	
	static void rCrossCheck() {
		if(map[0][0]==0 && map[1][1]==0 && map[2][2]==0 && map[3][3]==0 && map[4][4]==0)
			bingo ++;
	}
	
	static void lCrossCheck() {
		if(map[0][4]==0 && map[1][3]==0 && map[2][2]==0 && map[3][1]==0 && map[4][0]==0)
			bingo ++;
	}

}
