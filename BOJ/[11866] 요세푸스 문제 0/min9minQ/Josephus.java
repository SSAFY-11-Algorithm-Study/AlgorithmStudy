package time2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Josephus {

	public static void main(String[] args) {

		Queue<Integer> que = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();// q에 넣고
		for (int i = 1; i <= num; i++) {
			que.offer(i);
		}

		int count = sc.nextInt();
		sb.append("<");
		while (!que.isEmpty()) {
			for (int i = 0; i < count-1; i++) {
				int temp = que.poll();
				que.offer(temp);
			}
			sb.append(que.poll()+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}

}
