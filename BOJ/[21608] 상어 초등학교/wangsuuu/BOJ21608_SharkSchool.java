import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//완전 빡구현 문제..

public class BOJ21608 {
	
	static int N;
	static int[][] map;
	static int[][] info;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		info = new int[N*N][5];
		StringTokenizer st = null;
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		for(int i=0; i<N*N; i++) { //학생 차례대로 자리에 앉힘
			int[] cur = info[i];
			
			// 우선순위 1 체크
			ArrayList<int[]> list1 = new ArrayList<>(); // { x, y }
			int max1 = -1;
			for(int x=0; x<N; x++) { //각 칸에 대하여
				for(int y=0; y<N; y++) {
					
					if(map[x][y]!=0) //이미 자리가 차 있으면 다른 자리 봐야 함. 빼먹지 않게 주의!!!
						continue;
					
					int cnt=0;
					for(int a=0; a<4; a++) {						
						int nx = x + dx[a];
						int ny = y + dy[a];
						if(nx<0 || nx>=N || ny<0 || ny>=N) {
							continue;
						}
						for(int k=1; k<5; k++) {
							if(map[nx][ny] == cur[k]) { //인접한 칸에 좋아하는 학생이 있으면
								cnt++;
							}
						}
					}
					
					if(cnt>max1) {
						max1 = cnt;
						list1 = new ArrayList<>(); //max update되면 리스트를 새로 초기화
						list1.add(new int[] {x, y});
					} else if(cnt==max1) {
						list1.add(new int[] {x, y});
					}
				}
			}
			
			if(list1.size()==1) { //우선순위 1 만족하면 자리 앉힘
				map[list1.get(0)[0]][list1.get(0)[1]] = cur[0];
				continue;
			}
			
			
			//우선순위 2 체크
			ArrayList<int[]> list2 = new ArrayList<>(); // {x, y}
			int max2 = -1;
			for(int[] l : list1) {
				
				int x = l[0], y = l[1];
				int cnt=0;
				for(int k=0; k<4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if(nx<0 || nx>=N || ny<0 || ny>=N) {
						continue;
					}
					if(map[nx][ny]==0) {
						cnt++;
					}
				}
				if(max2<cnt) {
					max2 = cnt;
					list2 = new ArrayList<>();
					list2.add(new int[] {x, y});
				} else if(max2==cnt) {
					list2.add(new int[] {x, y});
				}
			}
			
			if(list2.size()==1) { //우선순위 2 만족하면 자리 앉힘
				map[list2.get(0)[0]][list2.get(0)[1]] = cur[0];
				continue;
			}
			
			//우선순위 3 체크
			int[] min = {30, 30}; //선택될 하나
			for(int[] l : list2) {
				if(min[0] > l[0]) { //열이 더 작으면
					min = l;
				} else if(min[0] == l[0]) {
					if(min[1] > l[1]) //행이 더 작으면
						min = l;
				}
			}
			
			map[min[0]][min[1]] = cur[0];
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		//자리배치 완료 후 만족도 계산
		int answer = 0;
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				int c = map[x][y];
				int[] cur = new int[5];
				for(int i=0; i<N*N; i++) { // 배치 완료된 한 칸에 대해 좋아하는 학생 번호 정보 구하기
					if(info[i][0] == c) {
						cur = info[i];
						break;
					}
				}
				int point=0;
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx<0 || nx>=N || ny<0 || ny>=N) {
						continue;
					}
					for(int k=1; k<5; k++) {
						if(map[nx][ny] == cur[k]) { //인접한 칸에 좋아하는 학생이 있으면
							point++;
						}
					}
				}
				if(point==1) {
					answer++;
				} else if(point==2) {
					answer += 10;
				} else if(point==3) {
					answer += 100;
				} else if(point==4) {
					answer += 1000;
				}
			}
		}
		
		System.out.println(answer);
	}

}
