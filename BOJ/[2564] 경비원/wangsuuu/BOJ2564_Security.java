import java.util.Scanner;

/* 아이디어 : 
 * 동근이와 상점의 방향이 같거나, 남서, 남동, 서북, 북동이라면 일반 거리계산이 최소 거리이다.
 * 하지만 남북, 서동일 경우 시계 또는 반시계방향을 모두 구해 둘 중 최소인 것을 골라야 한다. 
 * 시계방향일 경우, 입력된 거리를 더하면 되고 반시계방향일 경우 가로/세로 위치에서 거리 뺀 것을 더하면 된다.
 * (완전한 두 변은 동일하게 더해지므로 계산에서 제외)
 */

public class BOJ2564_Security {

	static int W, H;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		W = sc.nextInt(); //가로 길이
		H = sc.nextInt(); //세로 길이
		//블록을 R+1 by C+1의 이차원 배열이라 생각
		int sum=0;
		int N = sc.nextInt();//상점의 갯수
		Position[] shops = new Position[N];
		
		int dir, dist=0;
		for(int i=0; i<N; i++) {
			dir = sc.nextInt();
			dist = sc.nextInt();
			shops[i] = SetPos(dir, dist);
		} //상점의 정보 저장
		
		dir = sc.nextInt();
		dist = sc.nextInt();
		Position dg = SetPos(dir, dist); //동근의 정보 저장
		//입력 완료
		
		for(int i=0; i<N; i++) {
			Position shop = shops[i];
			if(dg.dir == shop.dir || ((2<dg.dir*shop.dir) && (10>dg.dir*shop.dir))) {
				//동근이와 상점이 방향이 같거나, 남서/남동/서북/북동이라면 -> 일반 거리계산
				sum +=(Math.abs(dg.x - shop.x) + Math.abs(dg.y-shop.y));
			} else { // 남북/서동일 경우 -> 시계, 반시계 해봐야 함
				int dist1=0, dist2=0;
				
				if(dg.dir * shop.dir==2) { //남북일 경우
					dist1 = dg.dist + shop.dist + H;
					dist2 = (W-dg.dist) + (W-shop.dist) + H;
				} else { //서동일 경우
					dist1 = dg.dist + shop.dist + W;
					dist2 = (H-dg.dist) + (H-shop.dist) + W;
				}
				sum += Math.min(dist1, dist2);
			}
		}
		System.out.println(sum);
	}
	
	
	private static Position SetPos(int dir, int dist) { //방향에 따른 위치 좌표 설정
		
		if(dir==1) { //북쪽이면
			return new Position(0, dist, dir, dist);
		} else if (dir==2) { //남쪽이면
			return new Position(H, dist, dir, dist);
		} else if(dir==3) { //서쪽이면
			return new Position(dist, 0, dir, dist);
		} else { //동쪽이면
			return new Position(dist, W, dir, dist);
		}
	}

	static class Position{ //x, y 위치좌표, 방향, 거리
		int x, y;
		int dir, dist;
		public Position(int x, int y, int dir, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.dist = dist;
		}
	}
}
