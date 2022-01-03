package week1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2589_보물섬 {
	
	public static class Node {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
	
	static int N,M;
	static char [][] map;
	static boolean [][] visited;
	static int answer = 0;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {-1,0,1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new char[N][M];
		
		
		for(int i = 0 ; i < N ; i ++) {
			String str = sc.next();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == 'L') {
					//계속 초기화
					visited = new boolean[N][M];
					
					int cnt = bfs(i,j);
					answer = Math.max(answer, cnt);
				}
			}
		}
		System.out.println(answer);
	}
	
	public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if( !visited[nx][ny]  && map[nx][ny] == 'L') {
                        q.offer(new Node(nx, ny, current.cost + 1));
                        cnt = Math.max(cnt, current.cost + 1);
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return cnt;
    }

}
