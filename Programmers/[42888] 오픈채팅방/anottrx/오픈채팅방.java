import java.util.*;

class Solution {
	public String[] solution(String[] record) {
		String[] answer = {};

		ArrayList<String> answerArr = new ArrayList<>(); // 메세지에서 닉네임 제외
		ArrayList<String> user = new ArrayList<>(); // 닉네임
		HashMap<String, String> userIdHM = new HashMap<>(); // 아이디, 닉네임

		for (int i = 0; i < record.length; i++) {
			String[] splited = record[i].split(" ");
			if (splited[0].equals("Enter")) {
				user.add(splited[1]);
				answerArr.add("님이 들어왔습니다.");
				userIdHM.put(splited[1], splited[2]); // 아이디, 닉네임 저장
			} else if (splited[0].equals("Leave")) {
				user.add(splited[1]);
				answerArr.add("님이 나갔습니다.");
			} else { // Change
				userIdHM.put(splited[1], splited[2]); // 아이디, 닉네임 변경
			}
		}

		answer = new String[answerArr.size()]; // 메세지 개수만큼 answer 생성
		for (int i = 0; i < answerArr.size(); i++) {
			String userNickname = userIdHM.get(user.get(i));
			answer[i] = userNickname + answerArr.get(i); // 메세지 합치기
		}

		return answer;
	}
}
