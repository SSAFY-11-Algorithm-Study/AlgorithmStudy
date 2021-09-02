package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1654_랜선자르기 {
	public static int [] arr;
	public static int K;
	public static int N;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		K= sc.nextInt();
		N = sc.nextInt();
		arr = new int [K];
		for(int i = 0 ; i < K; i ++) 
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		System.out.printf("%d", binarySearch());
	}

	public static long binarySearch() {
		//랜선의 길이가 너무 김
		long mid ;
		long start = 1;
		long end = arr[K-1];	

		while(start<= end) {
			mid = (start + end)/2;
			long cnt = 0;
			
			for(int i = 0 ; i < K ; i ++)
				cnt += arr[i]/mid;
			
			//갯수가 안맞는 경우
			if(cnt < N)
				end = mid -1;  //최대 길이를 줄여야됨
			else if (cnt >= N) 
				start = mid +1;	//최소길이를 늘려야됨
		}
		return end; //최대값을 리턴
	}
}
