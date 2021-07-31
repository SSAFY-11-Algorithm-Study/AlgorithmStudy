package week1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_7568{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int [] weight = new int[num];
		int [] height = new int[num];
		int [] rank = new int[num];
		
		//랭크는 1등으로 일단 초기화
		Arrays.fill(rank, 1);
		
		//몸무게랑 키 채워 넣음
		for(int i = 0 ; i < num ; i++) {
			weight[i] = sc.nextInt();
			height[i] = sc.nextInt();
		}
		
		//자신보다 덩치가 큰 사람이 있다면 rank 지정
		for(int i = 0 ; i < num ; i++) {
			for(int j = 0 ; j < num ; j++) {
				//나보다 덩치큰 사람 있으면 rank 더함
				if(weight[i] < weight[j] && height[i] < height[j]) {
					rank[i]++;
				}
			}
			System.out.printf("%d ", rank[i]);
		}
	}

}
