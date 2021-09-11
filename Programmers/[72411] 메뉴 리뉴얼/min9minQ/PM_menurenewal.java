/*
 1. 각 orders 마다 course의 숫자 만큼 조합해서 각 orders 원소 돌면서 완탐. 
 2. 해쉬맵 사용 
 3.    
  
  결국 혼자서는 잘 못풀어서 ... 풀이 검색 ㅠㅠ..
  
  설명 듣고 다시 ㅠㅠ...
*/
package time7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
public class PM_menurenewal {
	static HashMap<String, Integer> hm = new HashMap<>();
	static String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
	static int[] course = {2,3,4};
	public static void main(String[] args) {
		
		ArrayList<String> al = new ArrayList<>();
		int len[] = new int[course[course.length-1]+1];
		
		for(int i = 0; i < orders.length; i++) {
			char str[] = orders[i].toCharArray();
			Arrays.sort(str);
			for(int j = 0; j < course.length; j++) {
				if(course[j] <= orders[i].length()) {
					comb(str, orders[i].length(), course[j], 0, "");
				}
			}
		}
		
		for(String k:hm.keySet()) {
			if(2 <= hm.get(k)) {
				if(len[k.length()] < hm.get(k)) {
					len[k.length()] = hm.get(k);
				}
			}
		}
		
		for(String k:hm.keySet()) {
			if(2 <= hm.get(k) && len[k.length()] == hm.get(k)) {
				al.add(k);
			}
		}
		
		Collections.sort(al);
		String[] answer = new String[al.size()];
		
		for(int i = 0; i < al.size(); i++) {
			answer[i] = al.get(i);
		}
	}
	
	static void comb(char[] str, int n, int r, int start, String result) {
		if(r == 0) {
			hm.put(result, hm.getOrDefault(result, 0)+1);
			return;
		}else {
			for(int i = start; i < str.length; i++) {
					comb(str, n, r-1, i+1, result+str[i]);
			}
		}
	}

}
*/