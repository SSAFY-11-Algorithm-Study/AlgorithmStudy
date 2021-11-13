package time16;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj17142_연구소3 {

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static ArrayList<Point> virus;
	static int count;
	static int min = Integer.MAX_VALUE;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		count = N * N;

		map = new int[N][N];
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] == 1)
					count--;

				if (map[i][j] == 2) {
					count--;
					virus.add(new Point(i, j));
				}
			}
		}

		if (count == 0) { 
			System.out.println(0);
		} else {
			choice(0, 0);
			if(min == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(min);
		}

	}

	public static void choice(int cnt, int start) {
		if (cnt == M) { 

			spread();

			return;
		} else {
			
			for (int i = start; i < virus.size(); i++) {
				Point p = virus.get(i);

				map[p.x][p.y] = 3; 
				choice(cnt + 1, i + 1);
				map[p.x][p.y] = 2; // 다시 원래대로
			}
		}
	}

	
	public static void spread() {
		Queue<Point> queue = new LinkedList<>();
		int[][] visited = new int[N][N];

		
		int[][] copyMap = new int[N][N]; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];

				if (copyMap[i][j] == 3) { 
					queue.add(new Point(i, j));
					visited[i][j] = 1;
				}
			}
		}

		int cnt = 0;

		while (!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || copyMap[nx][ny] == 1)
					continue;

				if (visited[nx][ny] == 0) {
					visited[nx][ny] = visited[cx][cy] + 1;
					queue.add(new Point(nx, ny));

					if (copyMap[nx][ny] == 0) // 
						cnt++;

					if (cnt == count) {
						min = Math.min(min, visited[nx][ny] - 1); //시작할때 1했으니까 -1
					}
				}
			}
		}
	}

}
