package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//왠만한 테케 여러개 돌려봤는데 다 잘나와서 왜 틀린건지 모르겠습니다 ㅠㅠ

public class BOJ2304_창고다각형 {
	//노드 클래스 (꼭 public static 쓰자 . 안쓰면 밑에 배열에 new 못함)
	public static class Node{
		int x ;
		int height;
		
		public Node(int x , int height){
			this.x = x;
			this.height = height;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		Node [] arr = new Node[size];
		int answer = 0;
		
		for(int  i = 0 ; i < size ; i++) {
			int x = sc.nextInt();
			int h = sc.nextInt();
			arr[i] = new Node(x,h);
		}
		
		//x좌표 순으로 정렬
		Arrays.sort(arr, new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				return n1.x - n2.x;
			}
		});
		
		
		//맥스값  x좌표 찾기
		int max = -1;
		int maxIdx = -1;
		for(Node n : arr) {
			if(n.height > max) {
				max = n.height;
				maxIdx = n.x;
			}
		}
		
		//맥스 인덱스 찾기
		int cnt = 0;
		int maxPosition = 0;
		int maxLastidx = 0;
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i].x == maxIdx) {
				maxPosition = i;
			}
		}
		
		//맥스가 여러개면 카운트 셈
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i].height == arr[maxPosition].height)
				cnt++;
		}
		
		for(int i = arr.length-1 ; i > 0 ; i--) {
			if(arr[i].height == arr[maxPosition].height) {
				maxLastidx = i;
				break;
			}
		}
		
		int startIdx = arr[0].x;
		int startHeight = arr[0].height;
		int endIdx = arr[size-1].x;
		int endHeight = arr[size-1].height;
		
		
		
		for(int i = 1 ; i <= size-1 ; i ++) {
			//길이 더 큰게 뒤에 나오면 지금까지 넓이 더함
			if(startHeight < arr[i].height) {
				answer +=  startHeight * (arr[i].x - startIdx);
				startHeight = arr[i].height;
				startIdx = arr[i].x;
			}
		}
		
		//가장 큰곳 넓이 추가
		if(cnt ==1)
			answer += arr[maxPosition].height;
		else {
			System.out.println(arr[maxLastidx].x);
			System.out.println(arr[maxPosition].x);
			answer += arr[maxPosition].height * (arr[maxLastidx].x- arr[maxPosition].x +1);
		}
		
		
		for(int i = size-2 ; i >= 0 ; i --) {
			//길이 더 큰게 뒤에 나오면 지금까지 넓이 더함
			if(endHeight < arr[i].height) {
				answer += endHeight * (endIdx -arr[i].x );
				endHeight = arr[i].height;
				endIdx = arr[i].x;
			}
		}
		
		
		
		System.out.println(answer);
		
	}
}
