package week1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BOJ_1181 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		
		String[] arr = new String[num];
		for (int i = 0; i < num; i++) {
			arr[i]=sc.next();
		}
		sc.close();
		//입력 완료
		
		
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				if(o1.length()==o2.length())
					return o1.compareTo(o2);
				else
					return o1.length()-o2.length();
			}
		});
		//정렬된 array 완성
		
		//중복값 제외하고 차례대로 넣음
		List<String> result = new ArrayList<>();
		
		for(String str : arr) {
			if(!result.contains(str))
				result.add(str);
		}
		
		for(String s: result)
			System.out.println(s);
		
		
	}
}
