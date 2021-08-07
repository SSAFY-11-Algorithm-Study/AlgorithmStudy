package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


//요세푸스 0 문제
public class BOJ_11866 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		//큐에 값 담기
		for(int i=0 ; i < N ; i ++) 
			q.offer(i+1);
		
		System.out.printf("<");
		int cnt = 1;
		while(true) {
			//사이즈 1이면 값 출력하고 종료
			if(q.size() == 1) {
				int temp = q.peek();
				System.out.printf("%d", temp);
				break;
			}
			
			
			else {	
				//카운트 숫자되면 꺼내고 출력한다음에 카운트 초기화
				if(cnt == K) {
					int temp = q.poll();
					System.out.printf("%d, ", temp);
					cnt = 1;
				}
				
				//꺼낼차례가 아니면 꺼내서 맨 뒤에 다시 넣는다
				else {
					int temp = q.poll();
					q.offer(temp);
					cnt++;
				}
			}
		}
		System.out.printf(">");
		
	}
}
