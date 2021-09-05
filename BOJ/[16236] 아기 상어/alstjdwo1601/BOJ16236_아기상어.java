package week6;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ16236_아기상어 {

	static int N, M;
	static int cnt = 0;
	static int [][] map;
	static boolean [][] visited;
	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,-1,0,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0 ; i  <N ; i++) {
			for(int j = 0 ; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for(int i = 0 ; i  <N ; i++) {
			for(int j = 0 ; j < N; j++) {
				if(map[i][j] == 9) {
					map[i][j] = 0; // 9로 잇으면 거슬려서 좌표만받고 값은 지움
					start(i,j,2,0);
				}
			}
		}

	}
	private static void start(int x, int y, int weight, int eat) {
		visited[x][y] =true;
		//만약 더이상 먹을 물고기 없다면 그때의 시간을 출력
		if(!findFish(weight)) {
			int min = Integer.MAX_VALUE;
			
			min = Math.min(min, cnt);
			System.out.println(min);
			return;
		}

		
		//물고기가 있으니 가장 가까운거 찾아감 (북 서 남 동)
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx>=0 && ny >=0 && nx<N && ny<N &&!visited[nx][ny]) {
				//빈칸이거나 무게가 나보다 크면 넘어가고 시간 추가
				if(map[nx][ny] ==0 || map[nx][ny] >= weight) {
					start(nx,ny,weight,eat);
					cnt++;
				}
				//무게가 적다면 먹어
				if(map[nx][ny] >0 && map[nx][ny] < weight) {
					eat++; //먹음
					
					//먹은 횟수가 weight 만큼되면 무게 늘리고 eat초기화
					if(eat == weight) {
						System.out.println("냠2");
						cnt++;   //이동카운트
						weight++;  //무게 증가
						eat = 0;    //먹은 횟수 초기화
						map[nx][ny] = 0;  //먹은곳은 0

						
						//더 먹을거 없으면 종료
						if(!findFish(weight)) {
							System.out.println(cnt);
							return;
						}
						
						//다시 먹을거 찾으러 가야되니까 방문배열 초기화
						for(boolean [] b : visited)
							Arrays.fill(b, false);
						visited[nx][ny] = true;
						start(nx,ny,weight,eat);
					}

					//무게증가 안하고 그냥 먹는 경우
					else {
						System.out.println("냠1");
						cnt++;   //이동카운트 증가
						map[nx][ny] = 0;  //먹은 곳은 0
						
						
						//더 먹을거 없으면 종료
						if(!findFish(weight)) {
							System.out.println(cnt);
							return;
						}
						//다시 먹을거 찾으러 가야되니까 방문배열 초기화
						for(boolean [] b : visited)
							Arrays.fill(b, false);
						visited[nx][ny] = true;
						start(nx,ny,weight,eat);
					}
				}
			}
		}
	}
	
	private static boolean findFish(int weight) {
		boolean flag = false;

		//먹을 물고기 있나 체크(무게 나보다 작은거)
		for(int i = 0 ; i <N ; i++) {
			for(int j = 0 ; j < N; j++) {
				if(map[i][j]<weight && map[i][j] >=1)
					flag = true;
			}
		}

		if(flag) 
			return true; //물고기가 있단뜻
		else 
			return false; //물고기 없음
	}

}
