import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


//인터넷 풀이 참고했습니다.
/*
 * 1. 각 course에 대한 가능한 모든 조합 구하기 
 * 2. 그 조합을 key로, 그 조합이 나오는 갯수를 value로 하여 HashMap 구성
 * 3. value가 조건에 맞는 key만 골라내기
 */


public class PRO_MenuRenewal {
		static HashMap<String, Integer> map = new HashMap<>();
	    
	    public String[] solution(String[] orders, int[] course) {
	        ArrayList<String> tmpAnswer = new ArrayList<>();
			
			//1. 각 order에 대해 각 course수만큼의 조합을 모두 구함
			for(String order : orders) {
				
				char[] temp = order.toCharArray();
				Arrays.sort(temp); //결과는 알파벳 오름차순 정렬이어야 하므로
				StringBuilder sb = new StringBuilder();
				
				for(int num : course) {
					if(temp.length >= num) { //주문의 길이가 뽑을 수보다 커야지만 조합 구할 수 있으므로
						comb(temp, 0, 0, num, sb); //(temp)C(num)이며 그 결과를 sb에 저장함.
					}
				}
			}
			
			for(int cnt : course) {
				int max=-1;
				
				Set<Map.Entry<String, Integer>> mapSet = map.entrySet(); //entrySet은 Set 반환. Map을 iterable로 만들려면 Set으로 만들어야 함.
				
				//각 cnt에 맞는 조합들 중에서 가장 많이 주문된 횟수를 구함
				for(Map.Entry<String, Integer> ms : mapSet) {
					if(ms.getKey().length() == cnt) {
						max = Math.max(max, ms.getValue()); //max값 업데이트
					}
				}
				
				for(Map.Entry<String, Integer> ms : mapSet) {
					//최소 2개 이상의 메뉴로 구성되어 있어야 하므로
					if(max>1 && ms.getValue()==max && ms.getKey().length()==cnt) {
						tmpAnswer.add(ms.getKey());
					}
				}
			}
			
			Collections.sort(tmpAnswer);
			
			String[] answer = new String[tmpAnswer.size()];
			
			for(int i=0; i<answer.length; i++) {
				answer[i] = tmpAnswer.get(i);
			}

			return answer;
	    }
	    
	    public static void comb(char[] temp, int cnt, int start, int num, StringBuilder sb) {
			
			if(cnt==num) {
				//2. HashMap 구성
				map.put(sb.toString(), map.getOrDefault(sb.toString(), 0)+1);
				return;
			}
			
			for(int i=start; i<temp.length; i++) {
				
				sb.append(temp[i]);
				comb(temp, cnt+1, i+1, num, sb);
				sb.delete(cnt, cnt+1); //추가했던 거 떼기.
			}
			
		}
}

/*
 * 기억할 것
 * 
 * 조합으로 풀되 뭔가 되게 복잡해 질 것 같으면 HashMap 접근법 떠올리기(패션왕 신해빈, 메뉴 리뉴얼..)
 * 
 * 1. HashMap.entrySet() : Map에 저장되어 있는 key, value를 Set의 형태로 반환. iterable로 만들기 위해선 이를 호출하고 Set에 저장해야.
 * 2. entrySet의 결과를 받기 위해 Set의 type은 Map.Entry<Key type, Value type>이어야 함.
 * 3. HashMap.getOrDefault(key, default value) : key의 value값을 가져오거나 key가 없다면 default value를 리턴
 * 
 */

