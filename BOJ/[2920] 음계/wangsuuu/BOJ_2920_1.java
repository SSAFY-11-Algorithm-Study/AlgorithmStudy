package week1;

import java.util.Scanner;


public class BOJ_2920_1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[] arr = new int[8];
		
		for(int i=0; i<8; i++)
			arr[i]=sc.nextInt();
		sc.close(); //입력값 받기 완료
		
		int asc=1;
		int dsc=8;
		
		for(int i=0; i<8; i++) {
			if(arr[i]==asc)
				asc++;
			if(arr[i]==dsc)
				dsc--;
		}
		
		/*
		else if(arr[i]==dsc] 하면 틀리는 이유:
		만약 입력값이 8 7 6 5 4 3 2 1 이라 하면, arr[7]==asc이기 때문에 첫번째 조건문에만 걸리고, 두 번째 조건문은 패스하게 된다.
		그렇기 때문에 무조건 검사를 하는 if문을 두개 만들어 줘야 한다!
		
		*/
		
		if(asc==9) System.out.println("ascending");
		else if(dsc==0) System.out.println("descending");
		else System.out.println("mixed");
			
	}

}