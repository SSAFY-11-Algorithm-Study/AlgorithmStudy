import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Queue 사용! "원(Circle)형 문제"
public class BOJ11866_Josephus {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), K=sc.nextInt();
		Queue<Integer> que = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		int idx=0;
		
		for(int i=1; i<=N; i++) {
			que.add(i);
		}
	
		sb.append("<");
		
		while(!que.isEmpty()) {
			for(int i=0; i<K-1; i++) { //제거할 것 이전의 요소들을 dequeue & enqueue
				int n = que.peek(); //맨 첫번째 값
				que.remove();
				que.add(n);
			}
		sb.append(que.peek() + ", ");
		que.remove();
		}
		
		sb.setLength(sb.length()-2); //끝에 쉼표와 공백 제거
		sb.append(">");
		System.out.println(sb);
	}

}
