package time1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class wordsorting {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String[] ary = new String[num];
		List<String> list = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		/*
		ArrayList<String> strary = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			strary.add(sc.next());
		}
		*/
		for(int i = 0; i < num; i++) {
			list.add(sc.next());
		}
		
		list.sort(new Comparator<String>() {

			@Override// 리스트 sorting
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				} else {
					return Integer.compare(o1.length(), o2.length());
				}
			}
			
		});
		
		
		/*
		if(strary!=null)
			result.add(strary.get(0));
		for(int i = 0; i < strary.size(); i++) {
			int index = 0;
			for(int j = 0; j < result.size(); j++) {
				if(strary.get(i).compareTo(result.get(j))>0) {
					index ++;
					if(index>=result.size()) {
						result.add(index,strary.get(i));	
					}
				} else if(strary.get(i).compareTo(result.get(j))<0){
					result.add(index,strary.get(i));
				}
			}
		}
		*/
		
		for(String s : list) {
			if(!result.contains(s)) {
				result.add(s);
			}
		}
		for(String item : result) {
			System.out.println(item);
		}
		
		
	
		
	}

}
