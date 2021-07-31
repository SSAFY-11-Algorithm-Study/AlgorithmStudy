package week1;
import java.util.*;

//백준 1181번 단어 정렬
public class BOJ_1181 {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		 
		int num = sc.nextInt();
		String[] arr = new String[num];
 
		sc.nextLine();	
 
		for (int i = 0; i < num; i++) 
			arr[i] = sc.nextLine();
		
		
		/*
		 * Comparator 메소드가 양의 정수를 리턴하면 위치를 바꾸고
		 * 음의정수나 0을 리턴하면 위치를 그대로 둔다.
		 */
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				// 단어 길이가 같을 경우 
				if (s1.length() == s2.length()) {
					//compareTo는 앞이 작으면 -1 같으면 0 크면 1을 반환
					//즉 s1이 커서(사전순 뒤쪽이면) 위치를 바꾼다는 의미
					return s1.compareTo(s2);
				} 
				// 그 외의 경우 
				else {
					//s1이 길어서 양수나오면 위치 바꿈
					return s1.length() - s2.length();
				}
			}
		});
 
 
		StringBuilder sb = new StringBuilder();
 
		sb.append(arr[0]).append('\n');
		
		for (int i = 1; i < num; i++) {
			// 중복되지 않는 단어만 출력
			// 이미 정렬되어 있어서 같은건 붙어 있으니까 앞에것만 체크해도됨
			if (!arr[i].equals(arr[i - 1])) {
				sb.append(arr[i]).append('\n');
			}
		}
		System.out.println(sb);
		
	}
}