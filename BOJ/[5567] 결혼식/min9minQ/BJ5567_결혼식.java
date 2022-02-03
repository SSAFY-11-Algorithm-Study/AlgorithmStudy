package time26;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ5567_결혼식 {
	
	private static int n,m;
	private static boolean[] visited;
	private static ArrayList<Integer>[] arr;
	private static int answer = 0;
	

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for(int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			arr[num1].add(num2);
			arr[num2].add(num1);
		}
		
		bfs();
		
		System.out.println(answer);
	
	}


	private static void bfs() {
		
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		visited[1] = true;
		
		int depth = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			if(depth >= 2) break;
			for(int i = 0; i < size; i++) {
				int temp = que.poll();
				for(int j = 0; j < arr[temp].size(); j++) {
					if(visited[arr[temp].get(j)]) continue;
					visited[arr[temp].get(j)] = true;
					answer ++;
					que.add(arr[temp].get(j));
				}
			}
			depth++;
		}
		
		
	}

}
