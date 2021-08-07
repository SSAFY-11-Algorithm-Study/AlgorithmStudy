package time2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10158_ant {// 시간 초과...

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int time = Integer.parseInt(st.nextToken());
		
		int dx = 1;
		int dy = 1;
		
		for(int i = 0; i < time; i ++) {
			if(x == w) {
				dx = -1;
			}
			else if(x == 0) {
				dx = 1;
			}
			x += dx;
			if(y == h) {
				dy = -1;
			}
			else if(y == 0) {
				dy = 1;
			}
			y += dy;
		}
		System.out.println(x+" "+y);
	}

}
