package time25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ14888_연산자끼워넣기 {
	
	private static int N;
	private static int[] nums;
	private static int[] opers = new int[4];
	private static ArrayList<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4 ; i++) {
			opers[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(nums[0],0);
		
		Collections.sort(result);
		System.out.println(result.get(result.size()-1));
		System.out.println(result.get(0));
		
	}

	private static void dfs(int num, int idx) {
		
		if(idx == N-1) {
			result.add(num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opers[i] <= 0) continue;
			
			opers[i]--;
			
			if(i == 0) dfs(num+nums[idx+1],idx+1);
			else if(i == 1) dfs(num-nums[idx+1],idx+1);
			else if(i == 2) dfs(num*nums[idx+1],idx+1);
			else dfs(num/nums[idx+1],idx+1);
			
			opers[i]++;
		}
	}

}
