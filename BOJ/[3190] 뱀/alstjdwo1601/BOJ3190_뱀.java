package week2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3190_뱀 {
	
	static int N,K,L;
	static int [][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	//해쉬맵으로 저장하는거 참고함
	static HashMap<Integer,String> moveInfo; //<시간, 방향>
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		map = new int [N][N];
		moveInfo = new HashMap<>();
		for(int i = 0 ; i < K ; i ++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1; //사과 위치에 1넣음
		}
		
		L = sc.nextInt();
		
		for(int i = 0; i < L ; i++) {
			int X = sc.nextInt();
			String dir = sc.next();
			
			moveInfo.put(X,dir);
		}
		
		start(0);
	}
	private static void start(int curDir) {
		Queue<Integer> q = new LinkedList<>();
		
		//출발좌표와 시간
		q.add(0);
		int time =0;
		int x =0;
		int y =0;
		
		while(true) {
			//뱀머리 다음좌표
			int nx = x + dx[curDir];
			int ny = y + dy[curDir];
			time++;
            
			// 벽 부딪힘 종료
			if(nx<0 || ny<0 || nx>N-1|| ny> N-1) {
				break;
			}
            
			// 몸통 부딪힘 종료 -> 이부분 참고했는데 왜 ny*N + nx인지 모르겠음
			if(q.contains(ny*N +nx)){
				break;
			}
			
			// 안 부딪히면 뱀 이동시작 
			if(map[ny][nx] ==1) { //사과먹음
				map[ny][nx] = 0;
				q.add(ny*N +nx);
			}else {
				q.add(ny*N +nx);
				q.poll(); //꼬리 제거
			}
            
			// 방향 전환 할시간이 있으면 확인해서 방향바꾸고 
			if(moveInfo.containsKey(time)) {
				String dir = moveInfo.get(time);
				if(dir.equals("D")) {
					curDir +=1;
					if(curDir==4)  curDir=0;
				}else {
					curDir -=1;
					if(curDir==-1) curDir=3;
				}
			}
			x = nx;
			y = ny;
		}
		System.out.println(time);
	}

}
