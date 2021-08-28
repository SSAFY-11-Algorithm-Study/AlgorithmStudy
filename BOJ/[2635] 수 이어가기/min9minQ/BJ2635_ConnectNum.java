package time5;

import java.util.LinkedList;
import java.util.Scanner;

public class BJ2635_ConnectNum {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> result = new LinkedList<>();

		int num1 = sc.nextInt();
		int num2 = num1;
		int max = Integer.MIN_VALUE;

		for (int i = num1; i >= num1/2; i--) {
			LinkedList<Integer> numbers = new LinkedList<>();
			int count = 2;
			int cnum1 = num1;
			int cnum2 = num2;
			numbers.add(cnum1);
			numbers.add(cnum2);
			while (cnum2 >= 0 && cnum1 - cnum2 >= 0) {
				count++;
				numbers.add(cnum1- cnum2);

				int temp = cnum1;
				cnum1 = cnum2;
				cnum2 = temp - cnum2;
			}
			if(max < count) {
				max = count;
				result = numbers;
			}
			num2--;
		}
		
		System.out.println(max);
		for(int n : result) {
			System.out.print(n+" ");
		}
	}

}
