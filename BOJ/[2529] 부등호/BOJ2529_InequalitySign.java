import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2529_InequalitySign {
	
	static int N;
	static String[] signs;
	static boolean[] isSelected;
	static int[] result;
	static ArrayList<String> answer = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		signs = new String[N];
		for(int i=0; i<N; i++) {
			signs[i] = sc.next();
		}
		isSelected = new boolean[10];
		result = new int[N+1];
		
		perm(0);
		Collections.sort(answer);
		System.out.println(answer.get(answer.size()-1));
		System.out.println(answer.get(0));
		
	}
	private static void perm(int cnt) {
		if(cnt==N+1) {
			if(isValid(result)) {
				String ans = "";
				for(int i=0; i<N+1; i++) {
					ans += Integer.toString(result[i]);
				}
				answer.add(ans);
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i]=true;
			result[cnt] = i;
			perm(cnt+1);
			isSelected[i]=false;
		}
	}
	private static boolean isValid(int[] result) {
		
		int idx=0;
		for(int i=0; i<N; i++) {
			if(signs[idx].equals("<")) {
				if(result[i] > result[i+1])
					return false;
			}else {
				if(result[i]<result[i+1])
					return false;
			}
			idx++;
		}
		return true;
	}
}
