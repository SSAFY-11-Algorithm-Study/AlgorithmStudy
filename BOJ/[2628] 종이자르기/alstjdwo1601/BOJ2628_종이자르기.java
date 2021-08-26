package week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2628_종이자르기 {

	static int N,M;
	static int [][] paper;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		int num= sc.nextInt();
		ArrayList<Integer> rowList = new ArrayList<>();
		ArrayList<Integer> colList = new ArrayList<>();

		for(int i =0; i < num ; i++) {
			int flag = sc.nextInt();
			int idx = sc.nextInt();

			if(flag ==0)
				rowList.add(idx);
			else colList.add(idx);
		}

		Collections.sort(rowList);
		Collections.sort(colList);

		//자른 선 사이에 가장 긴 row 를 찾자
		int maxRow = Integer.MIN_VALUE;
		if(rowList.size()>1) {
			for(int i = 0 ; i < rowList.size() -1; i++) {
				maxRow = Math.max(maxRow, rowList.get(i+1) - rowList.get(i));
			}
			maxRow = Math.max(maxRow, rowList.get(0) - 0);
			maxRow = Math.max(maxRow, M- rowList.get(rowList.size()-1));
		}
		//한번잘랐으면 
		else if(rowList.size() ==1) {
			maxRow = Math.max(rowList.get(0), M-rowList.get(0));
		}
		else maxRow = M;
		
		int maxCol = Integer.MIN_VALUE;
		if(colList.size()>1) {
			for(int i = 0 ; i < colList.size() -1; i++) {
				maxCol = Math.max(maxCol, colList.get(i+1) - colList.get(i));
			}
			maxCol = Math.max(maxCol, colList.get(0) - 0);
			maxCol = Math.max(maxCol, N- colList.get(colList.size()-1));
		}
		//한번잘랐으면 
		else if(colList.size() ==1) {
			maxCol = Math.max(colList.get(0), N-colList.get(0));
		}
		else maxCol = N;
		
		//System.out.println(M);
		//System.out.println(N);
		System.out.println(maxRow*maxCol);
	}

}
