// hash map 연습하기 위해 블로그 참조
package time16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PM_베스트앨범 {

	static String[] genres = { "classic", "pop", "classic", "classic", "pop" };
	static int[] plays = { 500, 600, 150, 800, 2500 };

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
		}

		// 입력
		ArrayList<String> genre = new ArrayList<>();
		for (String key : map.keySet()) {
			genre.add(key);
		}
		Collections.sort(genre, (o1, o2) -> map.get(o2) - map.get(o1)); 

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < genre.size(); i++) {
			String str = genre.get(i);

			
			int max = 0;
			int first = -1;
			for (int j = 0; j < genres.length; j++) {
				if (str.equals(genres[j]) && max < plays[j]) {
					max = plays[j];
					first = j;
				}
			}
			
			max = 0;
			int second = Integer.MIN_VALUE;
			
			for (int j = 0; j < genres.length; j++) {
				if (str.equals(genres[j]) && max < plays[j] && j != first) {
					max = plays[j];
					second = j;
				}
			}

			list.add(first);
			if (second >= 0)
				list.add(second); 
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}

		System.out.println(Arrays.toString(result));
		// return result;

	}

}

/*
 * 
 * import java.util.*;
 * 
 * class Solution { public int[] solution(String[] genres, int[] plays) {
 * Map<String, Integer> map = new HashMap<>(); for(int i = 0; i < genres.length;
 * i++) { map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]); }
 * 
 * //key값만 가져와서 genre에 넣어준다 ArrayList<String> genre = new ArrayList<>();
 * for(String key : map.keySet()) { genre.add(key); } Collections.sort(genre,
 * (o1, o2) -> map.get(o2) - map.get(o1)); //key값에 해당하는 value를 내림차순으로 정렬한다.
 * 
 * ArrayList<Integer> list = new ArrayList<>(); for(int i = 0; i < genre.size();
 * i++) { String g = genre.get(i);
 * 
 * //해당 장르의 음악 중에서 play횟수가 가장 큰 인덱스를 찾는다 int max = 0; int firstIdx = -1; for(int
 * j = 0; j < genres.length; j++) { if(g.equals(genres[j]) && max < plays[j]) {
 * max = plays[j]; firstIdx = j; } }
 * 
 * //해당 장르의 음악 중에서 play횟수가 두번째로 큰 인덱스를 찾는다. max = 0; int secondIdx = -1; for(int
 * j = 0; j < genres.length; j++) { if(g.equals(genres[j]) && max < plays[j] &&
 * j != firstIdx) { max = plays[j]; secondIdx = j; } }
 * 
 * list.add(firstIdx); if(secondIdx >= 0) list.add(secondIdx); //secondIdx는 존재
 * 할수도, 안할수도 있다. }
 * 
 * int[] result = new int[list.size()]; for(int i = 0; i < list.size(); i++) {
 * result[i] = list.get(i); } return result; } }
 * 
 */
