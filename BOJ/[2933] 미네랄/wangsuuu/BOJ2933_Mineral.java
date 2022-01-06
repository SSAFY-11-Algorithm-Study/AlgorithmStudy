import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933_Mineral {
	
	static int R, C, N;
	static int[] heights; //각 턴마다 던지는 높이 저장
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<int[]> up_cluster;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			heights[i] = Integer.parseInt(st.nextToken()) - 1; //height는 0부터 시작하므로
		}//입력 완료
		
		for(int turn=1; turn<=N; turn++) {
			//1. 막대기 던지기
			Throw(turn);
			//2. 떠 있는 클러스터를 구해서, 옮기기
			solve();
			Reclustering();
		}
		
		//정답 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void solve() { //bfs 사용
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[R][C];
		
		for(int i=0; i<C; i++) { //맨 밑바닥에 있는 미네랄들만 enqueue
			if(map[R-1][i]=='x') {
				q.add(new int[] {R-1, i});
				visited[R-1][i]=true;
			}
		}
		
		while(!q.isEmpty()) { //바닥에 있는 미네랄들을 중심으로 cluster들 파악
			int[] cur = q.poll();
			int x = cur[0], y=cur[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && map[nx][ny]=='x') {
					q.add(new int[] {nx, ny});
					visited[nx][ny]=true;
				}
			}
		}
		up_cluster = new ArrayList<>(); //공중에 떠 있는 클러스터를 저장할 리스트
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='x' && !visited[i][j]) { //공중에 떠 있는 클러스터라면
					up_cluster.add(new int[] {i, j});
				}
			}
		}
	}

	private static void Reclustering() { //미네랄을 밑으로 이동시킴
		
		//떠 있는 미네랄들을 모두 .로 바꿈(몇 칸 내려가야 하는지 계산하는 과정에서 떠 있는 미네랄들끼리 영향을 주지 않게 하기 위해)
		for(int[] cluster : up_cluster) {
			map[cluster[0]][cluster[1]] = '.';
		}
		
		int cnt=0; //몇 칸 내려가야 하는지 계산
		
		here : while(up_cluster.size()!=0) { //while(true)로 두면, up_cluster가 없는 경우에는 무한루프에 빠짐..
			for(int[] cluster : up_cluster) {
				int x = cluster[0];
				int y = cluster[1];
				if(map[x+cnt][y]=='x') { //내려간 칸이 미네랄이라면 -> 그 전까지만 내려감
					cnt--;
					break here;
				}
				if(x+cnt==R-1) { //바닥에 닿았으면 -> 그만 내려감
					break here;
				}
			}
			cnt++;
		}
		
		for(int[] cluster : up_cluster) {
			map[cluster[0] + cnt][cluster[1]]='x';
		}
	}
	
	private static void Throw(int turn) {
		int height = (R-1) - heights[turn-1];
		if(turn % 2 == 1) { //왼쪽에서 던진다면
			for(int i=0; i<C; i++) {
				if(map[height][i]=='x') { //미네랄을 만나면
					map[height][i]='.';
					return;
				}
			}
		} else { //오른쪽에서 던진다면
			for(int i=C-1; i>=0; i--) {
				if(map[height][i]=='x') { //미네랄을 만나면
					map[height][i]='.';
					return;
				}
			}
		}
	}
}
