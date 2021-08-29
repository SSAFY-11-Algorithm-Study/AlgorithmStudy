//답이 무조건 30으로 나옵니다ㅠㅠ
//thanks to penglingss :) 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2116_DiceStacking {
	
	static int[][] dices;
	static int N; 
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //주사위의 갯수
		StringTokenizer st;
		
		dices = new int[N][6];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				dices[i][j]=Integer.parseInt(st.nextToken());
			}
		}//입력 완료

		int result = Integer.MIN_VALUE;
		for(int i=0; i<6; i++) {
			int max = findMax(i); //첫번째 주사위의 i 인덱스의 수를 밑면으로 해서 주사위 쌓아보기
			result = Math.max(result, max); //max값 업데이트
		}
		System.out.println(result);
		
	}
	
	private static int findMax(int idx) {
		int sum=0;
		int lower=idx, upper=findPair(idx);
		
		int i=0;
		while(i<N) {
			int lower_val = dices[i][lower];
			int upper_val = dices[i][upper];
			int max=0; //주사위 하나 당 옆면의 최대값
			
			for(int j=0; j<6; j++) {
				if(dices[i][j]!= lower_val && dices[i][j]!=upper_val) {
					max = Math.max(max, dices[i][j]);
				}
			}
			sum += max;
			i++;
			
			//다음 주사위의 밑, 윗면
			if(i<N) {
				for(int k=0; k<6; k++) {
					if(dices[i][k]==upper_val) {
						lower = k; upper = findPair(lower);
					}
				}
			}
		}
		return sum;
	}

	public static int findPair(int n){
		if(n==0) return 5;
		if(n==1) return 3;
		if(n==2) return 4;
		if(n==3) return 1;
		if(n==4) return 2;
		if(n==5) return 0;
		
		return 0;
	}
}
