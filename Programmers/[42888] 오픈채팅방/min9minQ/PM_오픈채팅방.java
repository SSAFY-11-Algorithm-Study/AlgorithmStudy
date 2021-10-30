package time14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PM_오픈채팅방 {
	
	static String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

	public static void main(String[] args) {
		String[] answer = {};
		
		ArrayList<String> arr = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		//아이디 문장
		for(int i = 0; i < record.length; i ++) {
			String[] temp = record[i].split(" ");
			
			if(temp[0].equals("Enter")) {
				map.put(temp[1], temp[2]);
				arr.add(temp[1]+"님이 들어왔습니다.");
			} else if(temp[0].equals("Change")) {
				map.put(temp[1],temp[2]);
			} else {
				arr.add(temp[1]+"님이 나갔습니다.");
			}
		}
		//아이디 문장을 닉네임 문장으로
		answer = new String[arr.size()];
		for(int i = 0; i < arr.size(); i++) {
			int idx = arr.get(i).indexOf("님");
			String id = arr.get(i).substring(0,idx);
			answer[i]= map.get(id) + arr.get(i).substring(idx);
		}
		
		System.out.println(Arrays.deepToString(answer));
	}

}
