package week9;
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

public class BOJ2805_나무자르기 {
	public static int [] arr;
	public static int M;
	public static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		
		//값 받아서 정렬하고 시작
		for(int i = 0 ; i < N ; i ++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		
		//시작 끝 중간 설정후 바이너리 서치
		int start = 1;
		int end = arr[N-1];
		int mid = 0;
		while(start <= end) {
			mid = (start + end) /2;
			long value = 0;
			
			//절단기 높이보다 낮은건 더하지 않음
			for(int i = 0 ; i < N; i ++) { 
				if(mid <= arr[i])
					value += arr[i]-mid;
			}
			//더한값이 원하는 값보다 크면 높이를 올려야됨
			if( M <= value)
				start = mid +1;
			else 
				end = mid -1;
			
		}
		System.out.printf("%d",end);
	}
}
