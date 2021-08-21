/*
 * 아직 완성 못했습니다 ㅠㅠ...
 * 짜고자 했던 로직은
 * 
 * 가장 높은 기둥을 찾고
 * 가장 처음 기둥을 저장 -> 첫기둥과 가장 높은 기동 사이의 기둥 들 중에 그 사이 높이를 갖고 있는 기둥들 저장
 * -> 가장 높은 기둥 저장 -> 가장 높은 기둥과 가장 마지막 기둥 사이에 그 사이 높이를 갖고 있는 기둥들 저장
 * -> 가장 마지막 기둥 저장
 * 
 * 해서 저장된 기둥들을 가지고 넓이를 구하기 였는데
 * 
 * 중간에 코드에 오류가 있는 것 같습니다... 
 */

package time4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BJ2304_warehouse {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[][] top = new int[num][2];
		int[] bestTop = {Integer.MIN_VALUE, Integer.MIN_VALUE};
		int index = 0;
		ArrayList<int[]> select = new ArrayList<>();
		
		
		for(int i = 0; i < num; i++) {
			top[i][0] = sc.nextInt();
			top[i][1] = sc.nextInt();
			if(top[i][1] > bestTop[1]) {
				bestTop[0] = top[i][0];
				bestTop[1] = top[i][1];
				index = i;
			}
		}
		Arrays.sort(top, Comparator.comparingInt(o1->o1[0]));
		
		System.out.println(index);
		
		select.add(top[0]); // 처음 집어넣고
		 
		for(int i = 1; i < index; i ++) { // 처음과 가장 높은탑 사이 집어넣고
			if(top[i][1] >= top[0][1] && top[i][1] <= top[index][1]) {
				select.add(top[i]);
			}
		}
		
		if(index != 0) select.add(top[index]); // 가장 높은 곳  저장
		
		for(int i = index+1; i < num-1; i ++) { // 가장 높은곳과 마지막 탑 사이
			if(top[i][1] >= top[num-1][1] && top[i][1] <= top[index][1]) {
				select.add(top[i]);
			}
		}
		
		select.add(top[num-1]); // 마지막 탑 저장
		
		for(int[] a: select) {
			System.out.println(Arrays.toString(a));
		}
		
	}

}
