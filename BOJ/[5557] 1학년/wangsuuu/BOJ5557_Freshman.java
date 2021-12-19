package week21;

import java.util.Scanner;

//dp문제임을 파악하지 못해서 블로그 참고..

public class BOJ5557_Freshman {

	static int n, numbers[];
	static long count[]; //연산 시 각 숫자가 나오는 경우의 수를 저장
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		numbers = new int[n];
		for(int i=0; i<n; i++) {
			numbers[i] = sc.nextInt();
		}//입력 완료
		
		count = new long[21]; //0이상 20이하의 수만 (중간) 계산 결과로 가능
		
		//처음 세팅
		count[numbers[0]]=1;
		
		for(int i=0; i<n-2; i++) { //연산은 총 n-2번
			long[] tmp = new long[21];
			for(int j=0; j<=20; j++) { //count순회하며 tmp에 업데이트
				if(count[j]!=0) {
					int result1 = j - numbers[i+1];
					if(result1 >=0) {
						tmp[result1] += count[j];
					}
					int result2 = j + numbers[i+1];
					if(result2<=20)
						tmp[result2] += count[j];
				}
			}
			count=tmp.clone();
		}
		System.out.println(count[numbers[n-1]]);
	}
}
