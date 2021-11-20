// 방향 설정 및 풀이 https://geunzrial.tistory.com/m/159 참조..
// 그런데 답이 이상하게 나옴...ㅜㅠ
package time17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PM_빛의경로사이클 {

	static String[] grid = { "SL", "LR" };
	static int R, C;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][][] check;

	public static void main(String[] args) {
		int[] answer;
		ArrayList<Integer> list = new ArrayList<>();

		R = grid.length;
		C = grid[0].length();

		check = new boolean[R][C][4];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int d = 0; d < 4; d++) {
					if (!check[i][j][d]) {
						list.add(light(grid, i, j, d));
					}
				}
			}
		}
		
		Collections.sort(list);
		answer = new int[list.size()];
		for(int i = 0; i < list.size(); i ++) {
			answer[i] = list.get(i);
		}
		
		System.out.println(Arrays.toString(answer));
		//return answer;

	}

	static public int light(String[] grid, int r, int c, int d) {
		int cnt = 0;

		while (true) {
			if (check[r][c][d])
				break;

			cnt++;
			check[r][c][d] = true;
			
			
			if (grid[r].charAt(c) == 'L') {
				if(d == 0) {
					d = 3;
				} else {
					d = d-1;
				}
			}
			else if (grid[r].charAt(c) == 'R')
				if(d == 3) {
					d = 0;
				} else {
					d = d+1;
				}
			
			
			r = (r + dr[d] + R) % R;
			c = (c + dc[d] + C) % C;
		}

		return cnt;
	}
}
