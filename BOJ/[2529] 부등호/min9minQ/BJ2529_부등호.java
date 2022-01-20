// 입력이랑 형변환 왜케 헷갈렸지
package time24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ2529_부등호 {
	
	private static int k;
	private static char[] arr;
	private static boolean[] visited = new boolean[10];
	private static ArrayList<String> result = new ArrayList<>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		k = sc.nextInt();
		arr= new char[k];
		
		for(int i = 0; i < k; i++) {
			arr[i] = sc.next().charAt(0);
		}
		
		dfs(0, "");
		
		Collections.sort(result);
		
		System.out.println(result.get(result.size()-1));
		System.out.println(result.get(0));

	}

	private static void dfs(int cnt, String number) {
		if(cnt == k+1) {
			result.add(number);
			return;
		}
		
		for(int i = 0; i <= 9; i++) {
			if(visited[i] == true) continue;
			
			boolean flag = false;
			
			if (cnt == 0) flag = true;
			if (cnt>0&&isvalid(number.charAt(cnt-1),(char)(i+'0'),arr[cnt-1])) {
				flag = true;
			}
			
			if(flag) {
				visited[i] = true;
				dfs(cnt+1,number+Integer.toString(i));
				visited[i] = false;
			}
		}
	}
	
	private static boolean isvalid(char num1, char num2, char sign) {
		if (sign == '<') {
			if (num1 < num2) {
				return true;
			}
		}
		if (sign == '>') {
			if (num1 > num2) {
				return true;
			}
		}
		return false;
	}

}
