package time26;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ18352_특정거리의도시찾기 {

	private static int N, M, K, X;
	private static ArrayList<Integer>[] arr;
	private static int[] distance;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		X = sc.nextInt();

		arr = new ArrayList[N + 1];
		distance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			arr[num1].add(num2);
		}

		bfs();
		
		boolean flag = false;
		for(int i = 1; i < distance.length; i++) {
			if(distance[i] == K && i != X) {
				System.out.println(i);
				flag = true;
			}
		}
		if(flag == false)
			System.out.println(-1);

	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.add(X);
		while(!que.isEmpty()) {
			int temp = que.poll();
			for(int i = 0; i < arr[temp].size();i++) {
				if(distance[arr[temp].get(i)]==0) {
					distance[arr[temp].get(i)] = distance[temp]+1;
					que.add(arr[temp].get(i));
				}
			}
		}
		
	}

}
