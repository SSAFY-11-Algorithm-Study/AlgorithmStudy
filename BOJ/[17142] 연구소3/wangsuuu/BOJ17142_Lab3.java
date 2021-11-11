import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142_Lab3 {
	
	static int N, M;
	static int[][] lab, copy; //copy가지고 시도해야 함!
	static ArrayList<int[]> virus = new ArrayList<>();
	static int[] result;
	static Queue<int[]> q;
	static int answer = Integer.MAX_VALUE;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //선택하는 바이러스 갯수
		result= new int[M];
		lab = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				lab[i][j]=Integer.parseInt(st.nextToken());
				if(lab[i][j]==2) //바이러스 위치 저장
					virus.add(new int[] {i, j});
			}
		}//입력 완료
		
		if(allInfected(lab)) { //이 경우 주의할 것! 이미 처음부터 바이러스가 다 퍼져있는 상태라면 답은 0
			System.out.println(0);
			return;
		}
		
		comb(0,0); //활성화시킬 바이러스 조합을 뽑음
		System.out.println(answer==Integer.MAX_VALUE ? -1 : answer);
	}
	private static void comb(int cnt, int start) {
		
		if(cnt==M) { //다 뽑았으면
			q = new LinkedList<>();
			visited = new boolean[N][N];
			copy= new int[N][N];
			Copy();
			for(int i=0; i<M; i++) {
				int x = virus.get(result[i])[0];
				int y = virus.get(result[i])[1];
				q.add(new int[] {x,y}); //바이러스의 위치, 경과 시간
				visited[x][y]=true;
			}
			bfs();
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			result[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	private static void Copy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = lab[i][j];
			}
		}
	}
	private static void bfs() {
		
		int time=1; // allInfected검사를 매번 하므로, time은 1부터 시작.
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for(int i=0; i<size; i++) { //바이러스는 동시에 퍼지므로 q의 사이즈만큼만 퍼지는 단계 수행
				int[] v = q.poll();
				int x= v[0], y=v[1];
				
				for(int j=0; j<4; j++) {
					int nx = x+dx[j];
					int ny = y + dy[j];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny] || copy[nx][ny]==1)
						continue;
					
					if(copy[nx][ny]==0) {
						copy[nx][ny]=2;
						q.add(new int[] {nx, ny});
						visited[nx][ny]=true;
					} else if(copy[nx][ny]==2) { //방문하지 않았는데 바이러스라면, 비활성->활성
						q.add(new int[] {nx, ny});
						visited[nx][ny]=true;
					}	
				}//for
			}//for
			
			if(allInfected(copy)) { //lab이 모두 1 또는 2로 이루어져 있다면 그 순간 종료
				answer = Math.min(answer, time);
				break;
			}
			
			time++;
		}//while
	}
	private static boolean allInfected(int[][] arr) { 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]==0)
					return false;
			}
		}
		return true;
	}
}
