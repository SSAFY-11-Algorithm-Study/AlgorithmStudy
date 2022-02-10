package time27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18111_마인크래프트 {
	
	private static int N,M,B;
	private static int[][] map;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
 
        map = new int[N][M];
        int total = B;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }
        int height = total / (N * M);
        if (height > 256) height = 256; //256보다 크면 어차피 256이 끝
        int fheight = height;
 
        while (height >= 0) {
        	// 이 높이로 만드는데 걸리는 시간
            int currentTime = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] <= height)
                        currentTime += (height - map[i][j]);
                    else
                        currentTime += (2 * (map[i][j] - height));
                }
            }
            if (currentTime < min) {
                min = currentTime;
                fheight = height;
            }
            height--;
 
        }
        System.out.println(min + " " + fheight);
	}

}
