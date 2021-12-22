package week21;

import java.util.Scanner;

public class BOJ2467_용액 {
	static int n;
	static int left,right=0;
	static int max = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		int[] liquid = new int[n];
		for(int i = 0 ; i < n ; i ++)
			liquid[i] = sc.nextInt();
		
		
		binarySearch(liquid);
		
		System.out.println(left + " " + right);
	}
	
	
	/*
	 * -99 를 0으로 만드려면 99가 필요하니까 
	 * -99의 오른쪽에서 99에 가장 가까운 값을
	 *  바이너리서치로 찾는다. 
	 *  
	 *  그러려면 현재 인덱스는 i 고 , i+1 부터 n-1 까지 
	 *  for문돌리면서 바이너리 서치를 돌려야함
	 */
	private static void binarySearch(int[] liquid) {
		for(int i = 0 ; i < n ; i ++) {
			int start = i+1;
			int end = n-1;
			
			
			while(start<=end) {
				//이거 값 start , end값이 계속 바뀌므로 while문 안에서 mid설정
				//이거 안했더니 값이 계속 안나왔음
				int mid = (start + end)/2;
				
				
				//현재 인덱스값과 for문 돌려서 찾은 mid 값 더해봄
				int sum = liquid[i] + liquid[mid];
				
				//max보다 작은경우엔 정답 두개 저장
				if(Math.abs(sum) < max) {
					left = liquid[i];
					right = liquid[mid];
					max = Math.abs(sum);
				}
				
				//아니면 값 조정
				if(sum<0)
					start = mid+1;
				else
					end = mid-1;
			}
		}
	}

}
