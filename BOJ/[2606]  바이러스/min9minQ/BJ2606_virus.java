package time3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2606_virus {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = Integer.parseInt(br.readLine());
		int link = Integer.parseInt(br.readLine());
		int count = 0;
		
		ArrayList<Integer>[] virus = new ArrayList[num+1];
		boolean[] isSelected = new boolean[num+1];
		for(int i = 0; i < num+1; i++) {
			virus[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < link; i ++) {
			st = new StringTokenizer(br.readLine());
			int num1= Integer.parseInt(st.nextToken());
			int num2= Integer.parseInt(st.nextToken());
			virus[num1].add(num2);
			virus[num2].add(num1);
		}
		
		search(1,isSelected,virus);
		for(int i = 0; i < num+1; i++) {
			if(isSelected[i]==true) {
				count++;
			}
		}
		
		System.out.println(count-1);
		
		
		/*
		HashSet<Integer> set = new HashSet<>();
		set.add(1);
		
		for(int i = 0; i < link; i ++) {
			st = new StringTokenizer(br.readLine());
			int num1= Integer.parseInt(st.nextToken());
			int num2= Integer.parseInt(st.nextToken());
			if(set.contains(num1)) {
				set.add(num2);
				count++;
			}
		}
		System.out.println(--count);
		*/
	}
	private static void search(int num, boolean[] isSelected,ArrayList<Integer>[] virus) {
		isSelected[num] = true;
		for(int v : virus[num]) {
			if(isSelected[v]==false)
				search(v,isSelected,virus);
		}
		
	}
}
