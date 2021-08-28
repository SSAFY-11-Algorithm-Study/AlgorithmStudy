package time5;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class BJ2627_cutPaper {

	public static void main(String[] args) {
		
		
		/* 2차원 배열에 올려 놓고 한번 자를때 마다 더 큰 면적에 해당하는 부분에 1을 더 해주는 로직
		 * 마지막에 입력 num 값을 가지고 있는 부분이 항상 큰 부분으로 선택 되온 부분이므로 가장 큼.
		 * 따라서 map[i][j] == num 인 부분의 숫자를 카운팅 후 출력.
		 * testcase 는 맞췄으나 결과 틀림.
		 * mid값 설정 부분에 서 오류 발생으로 추측...
		 * 밑에 다른 로직으로 코딩...
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		double rMid = R/2;
		double cMid = C/2;
		int count =0;
		int[][] map = new int[C][R];
		int num = sc.nextInt();
		
		for(int i = 0; i < num; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(a == 0) {
				 if(cMid > b) {
					 for(int j = b; j < C; j ++) {
						 for(int k = 0; k < R; k++)
							 map[j][k]++;
					 }
					 cMid = b+ (C - b)/2;
				 } else if(cMid < b) {
					 for(int j = 0; j < b; j ++) {
						 for(int k = 0; k < R; k++) {
							 map[j][k]++;
						 }
					 }
					 cMid = b/2;
				 } 
				 
			} else if(a == 1) {
				if(rMid > b) {
					for(int j = b; j < R; j ++) {
						for(int k = 0; k < C; k++) {
							map[k][j]++;
						}
					}
					rMid = b+ (R - b)/2;
				} else if(rMid < b) {
					for(int j = 0; j < b; j ++) {
						for(int k = 0; k < C; k ++) {
							map[k][j]++;
						}
					}
					rMid = b/2;
				}
			}
		}
		for(int i = 0; i < C; i++) {
			for(int j = 0; j < R; j ++) {
				if(map[i][j]==num) {
					count ++;
				}
			}
		}
		System.out.println(count);
		*/
		// 자른 후 가로 세로 각각의 자른 길이들 중 가장 큰값끼리 곱하기
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> arr1 = new LinkedList<>();
		LinkedList<Integer> arr2 = new LinkedList<>();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int num = sc.nextInt();
		arr1.add(y);// 계산을 위해서 마지막도 크기도 자른 값이라고 생각
		arr2.add(x);
		
		for(int i = 0; i < num; i ++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(a == 0) {
				arr1.add(b);
			} else if(a == 1) {
				arr2.add(b);
			}
		}
		
		Collections.sort(arr1, Collections.reverseOrder());
		Collections.sort(arr2, Collections.reverseOrder());
		
		
		LinkedList<Integer> xlength = new LinkedList<>();
		LinkedList<Integer> ylength = new LinkedList<>();
		
		for(int i = 0; i < arr1.size()-1; i++) {
			xlength.add(arr1.get(i) - arr1.get(i+1));
		}
		xlength.add(arr1.getLast());
		for(int i = 0; i < arr2.size()-1; i ++) {
			ylength.add(arr2.get(i) - arr2.get(i+1));
		}
		ylength.add(arr2.getLast());
		
		int result_x = Collections.max(xlength);
		int result_y = Collections.max(ylength);
		
		System.out.println(result_x * result_y);
		
	}

}
