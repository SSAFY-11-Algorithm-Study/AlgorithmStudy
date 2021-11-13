package week16;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ20056_마법사상어파이어볼 {
	static class Fireball{
		int x;
		int y;
		int weight;
		int speed;
		int dir;
		
		public Fireball(int x, int y, int weight, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
			
		}
	}
	
	static int N,M,K;
	static ArrayList<Fireball> [][] map;
	static int [] dx = {-1,-1,0,1,1,1,0,-1};
	static int [] dy = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Fireball> fireList = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		//맵 한 지점에 여러 파이어볼이 있을 수 있으므로 맵 자체를 어레이리스트로 생성해야함
		map = new ArrayList[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j++)
				map[i][j] = new ArrayList<Fireball>();
		}
		
		//초기 파이어볼 위치 리스트 추가
		for(int i = 0 ; i < M ; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			
			fireList.add(new Fireball(r,c,m,s,d));
		}
		
		for(int i = 0 ; i <K ;i++) {
			//1. 파이어볼을 이동한다.
			moveFire();
			
			//2. 2개 이상 파이어볼 있는곳에서 이벤트발생
			splitFire();
		}
		int answer = 0;
		for(Fireball fb : fireList)
			answer += fb.weight;
		System.out.println(answer);
		
	}

	private static void splitFire() {
		//배열 돌면서 2개이상 있는 지점 탐색
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j].size() >=2) {
					
					//System.out.println("무야호");
					//2-1 파이어볼은 모두 합쳐지며 질량과 속력을 더한다
					int weightSum = 0;
					int spdSum = 0;
					for(Fireball fb : map[i][j]) {
						weightSum += fb.weight;
						spdSum += fb.speed;
						fireList.remove(fb);
					}
					
					//질량 나눈게 0이면 소멸하고 넘어감
					if(weightSum/5 ==0) {
						map[i][j].clear();
						continue;
					}
					
					//3-1 무게는 5로 나누고, 스피드는 파이어볼개수로 나눈다
					weightSum /=5;
					spdSum /=map[i][j].size();
					
					//3-2 방향이 모두 짝수,홀수인지 아닌지 체크후 조절
					boolean dirFlag = false;
					int beforeDir = -1;
					int curDir = -1;
					for(Fireball fb : map[i][j]) {
						if(fb.dir/2 ==0) 
							curDir = 0; //현재 방향이 짝수면 0
						else curDir = 1;//현재 방향이 홀수면 1
						
						if(beforeDir == curDir) {
							beforeDir = curDir;
							dirFlag = true;
						}
						
						//이전 방향과 다른게 나오면(홀,짝) 
						else {
							dirFlag = false;
							break;
						}
					}
					
					if(dirFlag) { //모두 홀이거나 짝
						fireList.add(new Fireball(i,j,weightSum, spdSum, 0));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 2));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 4));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 6));
					}
					else {
						fireList.add(new Fireball(i,j,weightSum, spdSum, 1));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 3));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 5));
						fireList.add(new Fireball(i,j,weightSum, spdSum, 7));
					}
				}
				else map[i][j].clear();
			}
		}
		
	}

	private static void moveFire() {
		for(Fireball fb : fireList) {
			//배열 범위를 나가도 연결되어서 다시 무한 반복되므로  N으로 나눈 나머지 로 계산해야함
			//https://namhandong.tistory.com/214 참고
			int nx = (fb.x + N + dx[fb.dir] * (fb.speed % N)) %N; 
			int ny = (fb.y + N + dy[fb.dir] * (fb.speed % N)) %N;
			
			fb.x = nx;
			fb.y = ny;
			map[nx][ny].add(fb);
		}
		
	}
	

}






