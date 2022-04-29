import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_Escape {

	static int N, M;
	static char[][] map;
	static Queue<int[]> water = new LinkedList<>();
	static Queue<int[]> hedge = new LinkedList<>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy =  {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j]=='S') {
					hedge.add(new int[] {i , j});
				} else if(map[i][j] == '*') {
					water.add(new int[] {i, j});
				}
			}
		}//입력 완료
		
		int n = bfs();
		
		System.out.println(n==-1 ? "KAKTUS" : n);

	}
	private static int bfs() {
		int time = 0;
		while(!hedge.isEmpty()) {//1분 단위로, 물 퍼뜨린 다음 고슴도치 이동
			
			//물 상하좌우 퍼뜨리기
			int w_size = water.size();
			for(int i=0; i<w_size; i++) {
				int[] cur = water.poll();
				for(int j=0; j<4; j++) {
					int nx = cur[0] + dx[j];
					int ny = cur[1] + dy[j];
					if(nx<0 || nx>=N || ny<0 || ny>=M)
						continue;
					if(map[nx][ny] == '.' || map[nx][ny] =='S') { //물이 퍼질 수 있으면. map[nx][ny]=='*' 이걸 조건에 넣으면 메모리 초과가 남..
						map[nx][ny] = '*';
						water.add(new int[] {nx, ny});
					}
				}
			}
			
			
			//고슴도치 이동
			int h_size = hedge.size();
			for(int i=0; i<h_size; i++) {
				int[] cur = hedge.poll();
				for(int j=0; j<4; j++) {
					int nx = cur[0] + dx[j];
					int ny = cur[1] + dy[j];
					if(nx<0 || nx>=N || ny<0 || ny>=M)
						continue;
					if(map[nx][ny]=='.') {//고슴도치는 빈 곳만 갈수 있음
						map[nx][ny] = 'S'; //간 곳 표시(방문배열 대신)
						hedge.add(new int[] {nx, ny});
					} else if(map[nx][ny] == 'D') {
						return ++time;
					}
				}	
			}
			time++;
		}
		return -1;
	}

}
