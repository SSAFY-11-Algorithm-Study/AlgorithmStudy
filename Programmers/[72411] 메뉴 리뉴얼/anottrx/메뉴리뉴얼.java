import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    static HashSet<String> menuSet = new HashSet<>(); // 메뉴 중복을 없애기 위함
    static char[] menu, selectedMenu; // 조합 위함
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        for (int i = 0; i < course.length; i++) { // 코스 (2,3,4)
            for (int j = 0; j < orders.length; j++) { // 주문 ("ABCFG")
                menu = orders[j].toCharArray();
                Arrays.sort(menu); // 정렬 (XY, YX 막기 위함)
                selectedMenu = new char[course[i]];
                makeMenu(0, 0, course[i]); // 코스 개수만큼 조합
            }
        }

        String[] menuSetList = menuSet.toArray(new String[menuSet.size()]); // 해시셋 -> 문자열 배열
        HashMap<String, Integer> menuCntMap = new HashMap<>(); // 가장 많이 주문 가능한 코스 요리 개수 구하기 위해 해시맵
        int[] maxCnt = new int[course[course.length - 1] + 1]; // 각 코스 개수마다 최대값 저장해놓기 위함 ([2][3][4])
        for (int i = 0; i < menuSetList.length; i++) {
            int cnt = 0; // 해당 코스를 최대 몇명이 주문할지 횟수 세기
            char[] menuSelectedArr = menuSetList[i].toCharArray(); // 만든 코스를 하나씩 쪼개기 ("AC" -> "A","C")
            for (int j = 0; j < orders.length; j++) {
                int containCnt = 0;
                for (int k = 0; k < menuSetList[i].length(); k++) { // order가 해당 코스를 가지고 있는지 확인
                    String str = String.valueOf(menuSelectedArr[k]);
                    if (orders[j].contains(str)) { // "ABCFG"가 "A", "C"를 가지고 있는지
                        containCnt++;
                    }
                }
                if (containCnt == menuSetList[i].length()) {
                    cnt++;
                }
            }
            if (cnt > 1) { // 최소 2명 이상이 주문해야함
                menuCntMap.put(menuSetList[i], cnt);
            }
            maxCnt[menuSetList[i].length()] = Math.max(maxCnt[menuSetList[i].length()], cnt);
        }

        ArrayList<String> answerList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            for (String str : menuCntMap.keySet()) { // 해당 코스 개수와 코스당 최대값과 같다면 답에 저장
                if (menuCntMap.get(str) == maxCnt[course[i]] && str.length() == course[i]) {
                    answerList.add(str);
                }
            }
        }
        Collections.sort(answerList); // 답 정렬
        answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
        
        return answer;
    }
    
    private static void makeMenu(int cnt, int start, int menuCnt) {
        if (cnt == menuCnt) {
            String str = "";
            for (int i = 0; i < cnt; i++) {
                str = str + selectedMenu[i];
            }
            menuSet.add(str); // 완성된 코스를 해시셋에 넣기
            return;
        }
        for (int i = start; i < menu.length; ++i) {
            selectedMenu[cnt] = menu[i];
            makeMenu(cnt + 1, i + 1, menuCnt);
        }
    }
}
