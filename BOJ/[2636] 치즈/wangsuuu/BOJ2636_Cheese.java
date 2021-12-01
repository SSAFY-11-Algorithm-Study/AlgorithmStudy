//너무 어렵게 생각했다..ㅠㅠ

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636_Cheese {
	
	static int N, M, cheeseCnt;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					cheeseCnt++;
				}
			}
		}//입력 완료
		
		int time=0, answer=0;
		while(cheeseCnt!=0) {
			answer=cheeseCnt;
			meltCheese();
			time++;
		}
		
		System.out.println(time);
		System.out.println(answer);
	}
	private static void meltCheese() { //bfs
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][M];
		
		q.add(new int[] {0, 0});// 0,0 부터 시작
		visited[0][0]=true;
		while(!q.isEmpty()) {
			
			int[] tmp = q.poll();
			int x= tmp[0], y=tmp[1];
			
			for(int i=0; i<4; i++) {
				int nx= x+dx[i];
				int ny=y+dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny])
					continue;
				
				if(map[nx][ny]==0) {
					q.add(new int[] {nx, ny});
				} else { //1이라면(가장자리 치즈라면)
					map[nx][ny]=0; //녹인다
					cheeseCnt--;
				}
				visited[nx][ny] = true; //방문 체크는 무조건 해준다.
			}
		}
	}
}
