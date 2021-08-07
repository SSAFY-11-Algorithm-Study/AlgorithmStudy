package week1;

import java.util.Arrays;
import java.util.Scanner;

//각 사람의 덩치 등수는 자신보다 "더 큰 덩치"(즉, 키와 몸무게가 확실히 자기보다 더 큰)사람의 수로 정해진다.
//따라서, 덩치 등수 정하기 애매한 거 생각하지 않고, 그냥 자기보다 확실히 더 큰 사람이 몇 명인지만 알 수 있으면 됨


public class BOJ_7568_1 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		int[] weight = new int[num];
		int[] height = new int[num];
		int[] rank = new int[num];
		
		Arrays.fill(rank, 1);
		
		for(int i=0; i<num; i++) {
			weight[i]=sc.nextInt();
			height[i]=sc.nextInt();
		}
		sc.close();
		//입력값 받기 완료
		
		for(int i=0; i<num; i++) { //각각 순위를 정함
			for(int j=0; j<num; j++) { //한 사람의 순위를 정하기 위해 배열 전체를 순회하며 비교 
				if(weight[i]<weight[j] && height[i]<height[j]) //자기보다 덩치가 더 큰 사람이 있으면
					rank[i]++; //rank에 담겨있는 값 추가
			}
			System.out.printf("%d ", rank[i]);
		}
	}
}