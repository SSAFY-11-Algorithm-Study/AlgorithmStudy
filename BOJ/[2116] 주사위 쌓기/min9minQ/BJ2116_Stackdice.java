/*
 * 주사위의 맨 아랫부분이 정해지면 최댓값은 정해지기 때문에 총 6가지 경우의 수가 나온다
 * 이 여섯가지의 경우수 중에 최대값이 답.
 * 주사위 top bottom을 정하기 위해 index 3과 4 를 바꿈.
 * 이렇게 하면 top index + bottom index = 5 로 항상 짝이 맞추어짐.
 * 
 * 테스트 케이스는 맞는데 백준에서 런타임에러 나네요 ㅠㅠ(ArrayIndeOutofBounds)
 */
package time5;

import java.util.Scanner;

public class BJ2116_Stackdice {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[][] arr = new int[num][6];
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < num; i ++) {
			for(int j = 0; j < 6; j ++) {
				if(j == 3) arr[i][4] = sc.nextInt(); //3 이랑 4 인덱스 바꿔서 짝간의 인덱스 합이 5가 되도록.
				else if(j == 4) arr[i][3] = sc.nextInt();
				else arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < 6; i ++) {// 6가지 경우의 수 중에 가장 큰 값.
			int sum = 0;
			int bottom = arr[0][i];
			int top = arr[0][5-i];
			
			int temp_max = 0;
			for(int k = 0; k < 6; k ++) {
				if(k==i || i+k == 5) continue;
				if(arr[0][k] > temp_max) {
					temp_max = arr[0][k];
				}
			}
			sum+=temp_max;
			int temp = -1;
			for(int j = 1; j <= num-1; j ++) {
				
				for(int k = 0; k <6; k ++) {
					if(arr[j][k]==top) {
						temp = k;
					}
				}
				
				temp_max = 0;
				for(int k = 0; k < 6; k ++) {
					if(k==temp || temp+k ==5) continue;
					if(arr[j][k] > temp_max) {
						temp_max = arr[j][k];
						top = arr[j][5-temp];
					}
				}
				sum+=temp_max;
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
