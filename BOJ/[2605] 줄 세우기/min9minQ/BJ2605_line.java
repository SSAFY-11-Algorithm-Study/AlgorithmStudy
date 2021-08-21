package time4;

import java.util.ArrayList;
import java.util.Scanner;

public class BJ2605_line {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		ArrayList<Integer> line = new ArrayList<>();
		
		// 일단 첫번째 넣어주기
		sc.nextInt();
		line.add(1);
		// 첫번째 사람 이후로 줄 세우기
		for(int i = 0; i < num - 1; i ++) {
			int temp = sc.nextInt();
			line.add(line.size()-temp, i+2);
		}
		//출력
		for(int i = 0; i < num; i ++) {
			System.out.print(line.get(i) + " ");
		}
	}

}
