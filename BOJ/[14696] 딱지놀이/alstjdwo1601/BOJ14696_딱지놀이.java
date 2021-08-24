package week5;

import java.util.Scanner;

public class BOJ14696_딱지놀이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i = 0 ; i < N; i ++) {
			int sizeA = sc.nextInt();
			String A = sc.nextLine().trim();
			
			int sizeB = sc.nextInt();
			String B = sc.nextLine().trim();
			
			int [] cntA = new int[5];
			int [] cntB = new int[5];
			
			for(int j = 0 ; j < A.length() ; j++) {
				if(A.charAt(j) == '4')
					cntA[4]++;
				else if(A.charAt(j) == '3')
					cntA[3]++;
				else if(A.charAt(j) == '2')
					cntA[2]++;
				else if(A.charAt(j) == '1')
					cntA[1]++;
			}
			
			for(int j = 0 ; j < B.length() ; j++) {
				if(B.charAt(j) == '4')
					cntB[4]++;
				else if(B.charAt(j) == '3')
					cntB[3]++;
				else if(B.charAt(j) == '2')
					cntB[2]++;
				else if(B.charAt(j) == '1')
					cntB[1]++;
			}
			
			boolean flag = false;
			for(int j = 4 ; j >=1 ; j--) {
				if(cntA[j] > cntB[j]) {
					System.out.println("A");
					flag = true;
					break;
				}
				else if(cntA[j] == cntB[j])
					continue;
				
				else {
					System.out.println("B");
					flag = true;
					break;
				}
			}
			if(!flag) System.out.println("D");
		}
	}

}
