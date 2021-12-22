package week21;

import java.util.Scanner;

/*
 * 프로그래머스 타겟넘버랑 똑같은건줄알았는데
 * dp로 해야되는건 모르겠어서 블로그 참조,,
 * https://hyunah030.tistory.com/5 여기 설명 잘되어있음
 * 
 * Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int [] input = new int[n];
		for(int i = 0 ; i < n ; i ++) {
			input[i] = sc.nextInt();
		}
		
		
		int targetNum = input[n-1]; // 수식의 목표값
		
		dfs(input, 1,input[0],targetNum);
		
		System.out.println(answer);
	}

	private static void dfs(int[] input, int depth,int sum,int target) {
		if(depth == input.length-1) {
			if(sum == target) 
				answer++;
			
			return;
		}
		
		//중간에 누적값이 0미만, 20초과면 제외
		if(sum <0 || sum >20)
			return;
		
		dfs(input, depth+1, sum + input[depth], target);
		dfs(input, depth+1, sum - input[depth], target);
	}
 */


public class BOJ5557_1학년 {
	
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int n = sc.nextInt();
	        
	        //입력값 담는 배열
	        int[] arr = new int[n];
	        for(int i=0; i<n; i++) 
	            arr[i] = sc.nextInt();
	        
	    
	        //결과 값이 2의 63승 넘어가니까 long으로 해야됨
	        long[] num = new long[21];
	        for(int i=0; i<n-2; i++) {
	            if(i==0) 
	                num[arr[0]] = 1; //시작값을 1로 만들고 시작(경우의 수)
	            
	            num = calSum(arr, num, i+1);
	        }
	        
	        System.out.println(num[arr[n-1]]);
	        
	    }
	 
	    private static long[] calSum(int[] arr, long[] tmp, int x) {
	        long[] num  = new long[21];
	        for(int i=0; i<21; i++) {
	            if(tmp[i] == 0) continue;
	            
	            
	            //다음 값이 +일지 -일지에 따라 배열에 경우의수를 메모이제이션 해간다
	            else {
	                if(i-arr[x] >= 0 && i-arr[x] <= 20) 
	                    num[i-arr[x]] += tmp[i];
	                
	                if(i+arr[x] >= 0 && i+arr[x] <= 20) 
	                    num[i+arr[x]] += tmp[i];
	                
	            }
	        }
	        return num; 
	    }

}
