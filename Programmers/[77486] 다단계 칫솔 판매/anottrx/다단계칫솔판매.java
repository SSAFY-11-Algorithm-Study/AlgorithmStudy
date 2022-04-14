import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        HashMap<String, String> hm = new HashMap<>(); // [내 이름, 나를 참여시킨 사람 이름]
        HashMap<String, Integer> hmAnswer = new HashMap<>(); // [이름, 그 사람의 이익금]
        for (int i = 0; i < enroll.length; i++) {
            hm.put(enroll[i], referral[i]); // 나와 나를 참여시킨 사람 이름 저장
            hmAnswer.put(enroll[i], 0); // 우선 이익금을 모두 0으로 초기화
        }
        for (int i = 0; i < seller.length; i++) {
            getMoney(seller[i], amount[i] * 100, hm, hmAnswer); // 이름, 이익금*100(총 금액으로 계산), hm, hmAnswer
        }

        ArrayList<Integer> answerList = new ArrayList<>(); // 답
        for (int i = 0; i < enroll.length; i++) {
            answerList.add(hmAnswer.get(enroll[i]));
        }
        answer = new int[answerList.size()];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    private static void getMoney(String name, int money, HashMap<String, String> hm, HashMap<String, Integer> hmAnswer) {
        if (money == 0 || name.equals("-")) { // 0원이거나 center면 끝내기
            return;
        }
        int curCost = hmAnswer.get(name) + money - money / 10;
        hmAnswer.put(name, curCost);
        String upName = hm.get(name); // 윗사람 이름
        getMoney(upName, money / 10, hm, hmAnswer); // 10%의 금액
    }
}
