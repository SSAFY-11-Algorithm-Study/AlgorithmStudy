package week3;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2605_줄세우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		int [] randNum= new int[size];  //학생들이 뽑은 숫자
		ArrayList<Integer> order = new ArrayList<>(); //줄서는 순서
		
		for(int i = 0 ; i < size ; i++) {
			randNum[i] = sc.nextInt();
		}
		
		//첫번째 학생은 무조건 추가하고 시작
		order.add(1);
		
		//i+1이 학생번호 , 추가할 인덱스는 현재 리스트크기 - 뽑은번호
		for(int i = 1 ; i < size; i++) {
			int index = order.size() - randNum[i];
			order.add(index , i+1); //인덱스 위치에 학생을 위치시킴
		}
		
		for(int i = 0 ; i < order.size() ; i++)
			System.out.print(order.get(i) + " ");
			
	}
}
