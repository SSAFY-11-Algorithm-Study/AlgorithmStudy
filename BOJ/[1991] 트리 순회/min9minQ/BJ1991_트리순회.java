//node클래스 구현 방법으로 하려다 Map으로 트리 구조 익혀보기 위해 블로그 참조 map사용

package time12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BJ1991_트리순회 {	 	
	static Map<String, List<String>> map = new HashMap<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		 
		for(int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			List<String> list = new ArrayList<>();
			
			list.add(str[1]);
			list.add(str[2]);
			map.put(str[0], list);
		}
		
		preorder("A"); 
		System.out.println(); 
		inorder("A"); 
		System.out.println(); 
		postorder("A"); 
	}
	
	private static void preorder(String v) {
		if(v.equals(".")) return;

		System.out.print(v);
		preorder(map.get(v).get(0));
		preorder(map.get(v).get(1));
	}

	private static void inorder(String v) {
		if(v.equals(".")) return;

		inorder(map.get(v).get(0));
		System.out.print(v);
		inorder(map.get(v).get(1));
	}

	private static void postorder(String v) {
		if(v.equals(".")) return;
		
		postorder(map.get(v).get(0));
		postorder(map.get(v).get(1));
		System.out.print(v);
	}
}