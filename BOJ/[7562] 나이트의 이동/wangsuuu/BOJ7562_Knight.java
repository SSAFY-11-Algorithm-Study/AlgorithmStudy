package week35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562_Knight {
	static int N, s_x, s_y, e_x, e_y;
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			N = Integer.parseInt(br.readLine()); //한 변의 길이
			StringTokenizer st = new StringTokenizer(br.readLine());
			s_x = Integer.parseInt(st.nextToken());
			s_y = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			e_x = Integer.parseInt(st.nextToken());
			e_y = Integer.parseInt(st.nextToken());
			int answer = bfs();
			System.out.println(answer);
		}
		
	}

	private static int bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {s_x, s_y, 0}); //위치, 이동한 횟수
		visited[s_x][s_y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			int cnt = cur[2];
			if(x == e_x && y == e_y) {
				return cnt;
			}
			for(int i=0; i<8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny])
					continue;
				q.add(new int[] {nx, ny, cnt+1});
				visited[nx][ny] = true;
			}
		}
		return -1;
	}
}
