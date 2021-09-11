package time7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ3085_candygame {

	static int N;
	static char[][] map;
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		search();

	}
	private static void search() {
		// 가로 바꿔보고 체크  반복
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N-1; j++) {
				//바꾸기
				char temp = map[i][j];
				map[i][j] = map[i][j+1];
				map[i][j+1] = temp;
				
				//
				check();
				// 원래대로
				char temp2 = map[i][j];
				map[i][j] = map[i][j+1];
				map[i][j+1] = temp2;
			}
		}
		// 세로 바꿔보고 체크 반복
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < N; j++) {
				//바꾸기
				char temp = map[i][j];
				map[i][j] = map[i+1][j];
				map[i+1][j] = temp;
				
				//
				check();
				// 원래대로
				char temp2 = map[i][j];
				map[i][j] = map[i+1][j];
				map[i+1][j] = temp2;
			}
		}
		
		System.out.println(result);
	}

	private static void check() {
		
		for(int i = 0; i < N; i++) { //가로방향 최대 갯수
			int count = 1;
			for(int j = 0; j < N-1; j++) {
				if(map[i][j] == map[i][j+1]) {
					count ++;
				}
				else count = 1;
				result = Math.max(result,count);
			}
		}
		
		for(int i = 0; i < N; i++) { //세로방향 최대갯수 : 여기서 세로 방향으로 진행하면서 비교했어야 했는데 세로로 비교하되 가로방향으로 진행해서 오류났었음
			int count = 1;
			for(int j = 0; j < N-1; j++) {
				if(map[j][i] == map[j+1][i]) {
					count ++;
				}
				else count = 1;
				result = Math.max(result,count);
			}
		}
	}

}
