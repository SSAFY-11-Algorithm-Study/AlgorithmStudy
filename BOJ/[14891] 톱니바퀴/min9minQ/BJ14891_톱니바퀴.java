//미완성..
package time18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14891_톱니바퀴 {
	
	static int[][] gears = new int[4][8];
	static int k;
	static int[][] rotations;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 4; i ++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++) {
				gears[i][j] = str.charAt(j) - '0';
			}
		}
		
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		rotations = new int[k][2];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			rotations[i][0] = Integer.parseInt(st.nextToken());
			rotations[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < rotations.length; i++) {
			int num = rotations[i][0] - 1;
			int dir = rotations[i][1];
			
			if(dir == 1) {
				//
				go(num);
				//num이 몇인지보고 그것보다 작으면 7번쨰(index 6) 톱니바퀴랑 작은쪽의 3번째(index 3) 톱니바퀴 비교
				// 같으면 그 바퀴는 다르면 돌린다.
			} else {
				//num이 몇인지보고 그것보다 작으면 7번쨰(index 6) 톱니바퀴랑 작은쪽의 3번째(index 3) 톱니바퀴 비교
				// 같으면 그 바퀴는 다르면 돌린다.
				back(num);
			}
			
		}
	
	
	}
	private static void go(int n) {
		int temp = gears[n][0];
		for (int i = 0; i <= 6; i++)
			gears[n][i] = gears[n][i + 1];
		gears[n][7] = temp;
	}
 
	private static void back(int n) {
		int temp = gears[n][7];
		for (int i = 6; i >= 0; i--)
			gears[n][i + 1] = gears[n][i];
		gears[n][0] = temp;
	}

}
