package week1;

import java.util.Scanner;

//백준 2920번 음계
public class BOJ_2920 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = new int[8];
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0 ; i < arr.length; i++) 
			arr[i] = sc.nextInt();
		
		int asc = 1;
		int des = 8;
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i] == asc)
				asc++;
			if(arr[i] == des)
				des--;
		}
		
		if(asc == 9) 
			System.out.println("ascending");
		else if(des == 0) 
			System.out.println("descending");
		else 
			System.out.println("mixed");
		
	}

}
