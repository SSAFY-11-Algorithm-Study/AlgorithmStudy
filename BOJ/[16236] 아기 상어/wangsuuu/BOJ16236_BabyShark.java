import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_BabyShark {

	static class Object {
		int x, y;
		int size;
		public Object(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}	
	}
	
	static int N;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Object> fish = new ArrayList<>();
	static Object shark;
	static int ate_number, time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) {
					if(map[i][j] == 9) {
						shark = new Object(i, j, 2);
						map[i][j] = 0;
					}
					else {
						fish.add(new Object(i, j, map[i][j]));
					}
				}
			}
		}//입력 완료
		
		while(true) {
			ArrayList<int[]> tmp = bfs(shark.x, shark.y);
			if(tmp.size()==0)
				break;
			
			int[] result = findMinDist(tmp);
			time += result[2];
			map[result[0]][result[1]] = 0; //물고기를 먹었으므로 빈칸으로 변함
			shark.x = result[0];
			shark.y = result[1]; //상어 위치 옮겨줌
			ate_number++;
			if(ate_number == shark.size) {
				ate_number = 0;
				shark.size++;
			}
			
		}
		System.out.println(time);
	}
	private static int[] findMinDist(ArrayList<int[]> list) { //가장 가까운 거리에 있는 물고기 찾기
		int min_dist = Integer.MAX_VALUE;
		int min_x = 30, min_y = 30;
		for(int i=0; i<list.size(); i++) {
			int[] arr = list.get(i);
			if(min_dist > arr[2]) { //값 업데이트
				min_dist = arr[2];
				min_x = arr[0]; min_y = arr[1];
			} else if(min_dist == arr[2]) { //최솟값이 같으면, 가장 위에 있는 물고기 먹기
				if(min_x > arr[0]) {
					min_x = arr[0]; min_y = arr[1]; 
				} else if(min_x == arr[0] && min_y > arr[1]) { //똑같이 위에 있으면, 가장 왼쪽에 있는 물고기 먹기
					min_x = arr[0]; min_y = arr[1];
				}
			}
		}
		return new int[] {min_x, min_y, min_dist};
	}
	
	private static ArrayList<int[]> bfs(int x, int y) { //먹을 수 있는 물고기들을 구함
		ArrayList<int[]> tmp = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y, 0}); //위치, 경과 시간
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int pos_x = cur[0]; int pos_y = cur[1];
			int dist = cur[2];
			
			if(map[pos_x][pos_y] != 0 && map[pos_x][pos_y] < shark.size) {
				tmp.add(new int[] {pos_x, pos_y, dist});
			}
			
			for(int i=0; i<4; i++) {
				int nx = pos_x + dx[i];
				int ny = pos_y + dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny])
					continue;
				if(map[nx][ny] > shark.size) //못 지나감
					continue;
				q.add(new int[] {nx, ny, dist+1});
				visited[nx][ny]=true;
			}
		}	
		return tmp;
	}

}
