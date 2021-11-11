package week16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ17142_연구소3 {
	static class Virus {
        int x, y, time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
	
	static int N,M,zeroCnt;
	static int answer = Integer.MAX_VALUE;
	static int [][] map;
	static boolean [][] visited;
	static List<Virus> viruses = new ArrayList<>();
	static Virus[] selected;
	
	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,-1,0,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][N];
		
		selected = new Virus[M]; //선택된 바이러스 담는거
		
		zeroCnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] ==0)
					zeroCnt++;
				else if(map[i][j]==2)
					viruses.add(new Virus(i,j,0));
			}
		}
		
		//예외처리
		if(zeroCnt ==0)
			System.out.println(0);
		else {
			choiceVirus(0,0);
			//애초에 확산 불가
			if(answer == Integer.MAX_VALUE)
				System.out.println(-1);
			else
				System.out.println(answer);
		}
	}

	
	public static void choiceVirus(int depth , int start) {
		if(depth == M) {
			for(int i = 0 ; i < M ; i++) {
        		//System.out.println(selected[i].x + " , " + selected[i].y);
			//System.out.println("-----------------------");
				map[selected[i].x][selected[i].y] = -1; //활성 바이러스는 -1
			}
			
			spreadVirus(zeroCnt);
			return;
		}
		
		//조합으로 바이러스 M개 뽑기
		for (int i = start; i < viruses.size(); i++) {
            selected[depth] = viruses.get(i);
            choiceVirus(depth+ 1,i + 1);
        }
	}


	private static void spreadVirus(int cnt) {
		Queue<Virus> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		for(int i = 0 ; i < M ; i++) {
			Virus virus = selected[i];
			visited[virus.x][virus.y] = true;
			q.add(virus); //활성바이러스를 큐에 담음
		}
		
		while(!q.isEmpty()) {
			Virus virus = q.poll();
			
			for(int i = 0 ; i < 4 ; i ++) {
				int nx = virus.x+ dx[i];
				int ny = virus.y+ dy[i];
				
				//범위밖이거나 방문했거나 벽이면 패스
				if (nx < 0 || nx >= N || ny < 0 || ny >= N
						||visited[nx][ny]|| map[nx][ny] == 1) continue;
                
				//빈칸이면 확산되므로 빈칸을 하나 줄임
                if (map[nx][ny]== 0) 
                    cnt--; //빈칸 갯수
                
                //빈칸 갯수가 0이면 확산 완료
                if (cnt == 0) {
                	//각 경우(조합으로 뽑아온) 마다 나온 값에서 최소고르기
                    answer = Math.min(answer, virus.time + 1);
                    return;
                }

                visited[nx][ny] = true;
                q.add(new Virus(nx, ny, virus.time + 1));
			}
		}
		
	}
	
	
	
}
