import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502_Lab {
	
	static int N, M, lab[][] , copy[][];
	static ArrayList<int[]> wall, virus;
	static Queue<int[]> q;
	static int[][] result = new int[3][2];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer = -1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); //세로 크기
		M = sc.nextInt(); // 가로 크기
		wall = new ArrayList<>();
		lab = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for (int j = 0; j < M; j++) {
				lab[i][j] = sc.nextInt();
				if(lab[i][j]==0) { //벽을 세울 수 있는 곳의 위치 저장
					wall.add(new int[] {i, j}); 
				}
			}
		}//입력 완료
		
		selectWalls(0,0);
		
		System.out.println(answer);
		
	}

	private static void selectWalls(int cnt, int start) {

		if(cnt==3) {
			
			//매 경우마다 큐를 초기화 해주고, 다시 넣어줘야 함. 이것 때문에 헤멤.
			q = new LinkedList<>();
			
			copy = new int[N][M]; //lab의 복사본
			for(int i=0; i<N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = lab[i][j]; 
					if(copy[i][j]==2) { //바이러스 위치를 enqueue
						q.add(new int[] {i, j}); 
					}
				}
			}
			
			for (int i = 0; i < 3; i++) { //벽 표시
				int x = result[i][0];
				int y = result[i][1];
				copy[x][y] = 1;
			}
			
			spreadVirus(); //copy본으로 바이러스 확산 -> bfs 활용
			
			//안정 영역 구하기
			int area = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copy[i][j]==0)
						area++;
				}
			}
			
			answer = Math.max(area, answer);
			return;
		}
		
		for(int i=start; i<wall.size(); i++) {
			
			result[cnt] = wall.get(i);
			selectWalls(cnt+1, i+1);
		}
	}

	private static void spreadVirus() {
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0]; int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M && copy[nx][ny]==0) {
					//범위 안에 있고 빈 벽이면
					copy[nx][ny]=2;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
}
