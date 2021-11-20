package week17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ14620_꽃길 {
	static int N ;
	static int answer = Integer.MAX_VALUE;
	static int [][] map;
	static boolean [][] visited;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {1,0,-1,0};
	static ArrayList<int[]> seedList;
	static int [] selected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		map = new int[N][N];

		selected = new int[3];
		seedList = new ArrayList<>();

		//화단 가격정보 받기
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N; j ++) {
				map[i][j] = sc.nextInt();
			}
		}

		//꽃 심을 장소 리스트담기
		for(int i = 1 ; i < N-1 ; i ++) {
			for(int j = 1 ; j < N-1; j ++) {
				seedList.add(new int[] { i, j });
			}
		}

		//3개 조합으로 뽑기
		seedComb(0,0);

		System.out.println(answer);
	}

	//씨앗 조합 뽑기
	private static void seedComb(int depth, int start) {
		if (depth == 3) {
			//System.out.println(Arrays.toString(selected));

			int cost= findCost();

			//if(cost != Integer.MAX_VALUE)
				//System.out.println("cost : "+cost);
			answer = Math.min(cost, answer);
			return;
		}
		for (int i = start; i < seedList.size(); i++) {
			selected[depth] = i;
			seedComb(depth+1, i+1);
		}
	}

	private static int findCost() {
		int sum = 0;
		//매번 방문배열 초기화
		visited = new boolean[N][N];

		for(int i = 0 ; i < 3 ; i ++) {
			//조합으로 뽑은애 중에서 좌표값 뽑기
			int x = seedList.get(selected[i])[0];
			int y = seedList.get(selected[i])[1];

			//System.out.println(x + " , " + y);

			visited[x][y] = true;
			sum += map[x][y];

			//4방향 탐색
			for(int j  = 0 ; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				//방문한적이 없으면 감
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					sum += map[nx][ny];
					//System.out.println("AAAA");
				}
				else { //방문한곳이 존재하면 꽃이 겹침 이러면 패스
					//System.out.println("무야호");
					return Integer.MAX_VALUE;

				}
			}

		}

		return sum;
	}


}
