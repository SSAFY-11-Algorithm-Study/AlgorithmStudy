package week7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;


/*
 * 처음엔 모든 등수의 경우의수를 순열로 뽑은 후 그 차이를 각각 계산했으나
 * 시간초과 뜸.
 * */
public class BOJ2012_등수매기기 {
	static int N;
	static int [] expect;   //예상등수
	static int [] real;     //실제 부여한 등수
	static boolean [] visited;// 방문배열
	
	//최악의 경우 1 + 2+ 3 + .... 499000 인데 이게 int 범위 넘어감
	static long angry = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		expect = new int [N];
		real = new int [N];
		visited = new boolean [N];
		
		//기대 등수는 값을 받아오고 , 실제등수는 1부터 차례로 채움
		for(int i = 0 ; i < N ; i ++) {
			expect[i] = sc.nextInt();
			real[i] = i+1;
		}
		
		//정렬
		Arrays.sort(expect);
		
		for(int i = 0 ; i < N ; i ++) 
			angry += Math.abs(expect[i] - real[i]);
		
		
		System.out.println(angry);
	}
	
	
	/*
	private static void permu(int depth) {
		
		//N 개로 만들수 있는 순열이 곧 모든 등수의 경우의 수
		if(depth == N) {
			//System.out.println(Arrays.toString(real));
			int sum = 0 ;
			for(int i = 0 ; i < N ; i ++) {
				angry = Math.abs(expect[i] - real[i]);
				sum += angry;
			}
			min = Math.min(sum, min);
		}
		
		for(int i = 0 ; i < N ;  i ++) {
			if(!visited[i]) {
				visited[i] = true;
				real[depth] = i+1;    //0등은 없으니까 인덱스 +1
				permu(depth+1);
				visited[i] = false;
			}
		}
	}
	*/
}
