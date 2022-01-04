package week1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2933_미네랄 {
	static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	
	static char[][] map;
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();
    static int r;
    static int c;
    static int n; // 던지는 횟수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		r = sc.nextInt();
		c = sc.nextInt();
		
		map = new char[r][c];
		
		for(int i = 0 ; i < r; i ++) {
			String str = sc.next();
			for(int j = 0 ; j < c ; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		n = sc.nextInt(); //던질 횟수
		
		int idx = 1; //방향 구분하기위해서 1이면 왼->오 , -1이면 오->왼
		for(int i = 0 ; i< n ; i++) {
			int height = sc.nextInt();
			throwStick(r-height, idx); //던지기
			down();//내리기
			
			idx *=-1; //던지는 방향바꾸기
		}
		
		//출력
		for(int i = 0 ; i < r; i ++) {
			for(int j = 0 ; j < c ; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}
	static void throwStick(int h, int idx) {
        int start;
        int temp;

        if (idx > 0) //왼 -> 오
            start = 0;
        else //오 -> 왼
            start = c-1;
        

        temp = start;
        //x찾아서 없애고 종료
        for (int i = 0; i < c; i++) {
            if (map[h][temp] == 'x') {
                map[h][temp] = '.';
                break;
            }

            temp += idx;
        }
    }
	
	//블로그보고 참고했음
	static void down() {
        visited = new boolean[r][c];
        ArrayList<Point> cluster = new ArrayList<>();

        /* 바닥에 있는 클러스터 체크 */
        for (int i = 0; i < c; i++) {
            if (map[r-1][i] == '.' || visited[r-1][i]) {
                continue;
            }

            q.add(new Point(r-1, i));
            visited[r-1][i] = true;

            while (!q.isEmpty()) {
                Point p = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                        if (map[nx][ny] == 'x' && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && map[i][j] == 'x') {
                    cluster.add(new Point(i, j));
                    map[i][j] = '.';
                }
            }
        }

        if (cluster.isEmpty()) {
            return;
        }

        boolean flag = true;
        while (flag) {
            for (Point p : cluster) {
                int x = p.x + 1;
                int y = p.y;

                /* 아래로 내려갈 수 없는 경우 flag = false */
                if (x < 0 || y < 0 || x >= r || y >= c || map[x][y] == 'x') {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                for (Point p : cluster) {
                    p.x++;
                }
            }
        }

        for (Point p : cluster) {
            map[p.x][p.y] = 'x';
        }
    }

}
