package week15;

import java.util.Scanner;

//주사위 굴리는거 참고했음 https://velog.io/@hammii/%EB%B0%B1%EC%A4%80-14499-%EC%A3%BC%EC%82%AC%EC%9C%84-%EA%B5%B4%EB%A6%AC%EA%B8%B0-java
public class BOJ14499_주사위굴리기 {

	static int N,M,startX,startY,operSize;
	static int map[][];
	static int operArr[];
	static int[] dx = {0, 0, 0, -1, 1}; //동 서 북 남 인덱스 맞추려고 0,0 하나 넣음
	static int[] dy = {0, 1, -1, 0, 0};
	static int [] dice = new int[7];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		startX = sc.nextInt();
		startY = sc.nextInt();
		operSize = sc.nextInt();
		

		//맵 정보
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j++)
				map[i][j] = sc.nextInt();
		}
		
		operArr = new int [operSize];
		for(int i = 0 ; i < operSize; i++)
			operArr[i] = sc.nextInt();
		
		moveDice(startX, startY);
		sc.close();
	}
	private static void moveDice(int x, int y) {
		for(int i = 0 ; i < operSize ; i ++) {
			int oper = operArr[i];
			int nx = x + dx[oper];
			int ny = y + dy[oper];

			//맵 밖으로 나가는 명령일경우 원위치하고 출력은 x
			if(nx<0 || nx>=N || ny<0 || ny>=M) { 
				nx -= dx[oper];
				ny -= dy[oper];
				continue;  //break가 아니라 continue로 해줘야함
			}
			
			//주사위 돌리기
			rollDice(oper);
			
			//다음 지점이 0이면 바닥값을 복사
			if(map[nx][ny]==0)
				map[nx][ny] = dice[6];
			//0아니면 맵값을 바닥에 복사 후 맵은 0
			else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}
			
			x = nx;
			y = ny;
			System.out.println(dice[1]);
		}
		
		
	}
	
	//주사위를 굴린다고 생각하지말고 전개도에서 위치만 바뀐다고 생각
	public static void rollDice(int oper) {
        int[] cloneDice = dice.clone();

        //oper 1: 동 , 2: 서 , 3: 남, 4: 북
        switch (oper) {
            case 1: 
                dice[1] = cloneDice[4];
                dice[3] = cloneDice[1];
                dice[4] = cloneDice[6];
                dice[6] = cloneDice[3];
                break;
            case 2:
                dice[1] = cloneDice[3];
                dice[3] = cloneDice[6];
                dice[4] = cloneDice[1];
                dice[6] = cloneDice[4];
                break;
            case 3:
                dice[1] = cloneDice[5];
                dice[2] = cloneDice[1];
                dice[5] = cloneDice[6];
                dice[6] = cloneDice[2];
                break;
            case 4:
                dice[1] = cloneDice[2];
                dice[2] = cloneDice[6];
                dice[5] = cloneDice[1];
                dice[6] = cloneDice[5];
                break;
        }
    }
	
}