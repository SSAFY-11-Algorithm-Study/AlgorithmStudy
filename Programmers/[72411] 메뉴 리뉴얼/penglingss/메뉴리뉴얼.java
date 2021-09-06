import java.util.*;

class Solution {

    static HashMap<String, Integer>[] setByCourse; // course 갯수 별로 set 저장할 hashmap 배열
    static int N; // 현재 course 갯수
    static String order; // 현재 보고있는 order
    static int[] max; // course 갯수 별로 최댓값 저장할 배열

    public String[] solution(String[] orders, int[] course) {
        setByCourse = new HashMap[course.length];
        max = new int[course.length];
        PriorityQueue<String> setList = new PriorityQueue<>(); // 조건 "정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 오름차순 정렬해서 return 해주세요." 를 위해 PQ
      
        for(int i = 0; i < orders.length; i++) { // 조건 "배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬되어야 합니다." 를 위한 정렬
            String[] o = orders[i].split("");
            Arrays.sort(o);
            orders[i] = String.join("", o);
        }
      
        for(int i = 0; i < course.length; i++) {
            setByCourse[i] = new HashMap<>();
            N = course[i];
            for(int j = 0; j < orders.length; j++) {
                order = orders[j];
                getCourseSet(0, 0, "", i);
            }

            int m = max[i];
            if(m == 1) continue; // 조건 "최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로 했습니다."

            setByCourse[i].forEach((key, value) -> { // hashmap의 key, value 보면서
                if(value == m) { // 최댓값이면 offet
                    setList.offer(key);
                }
            });
        }

        String[] answer = new String[setList.size()];
        int idx = 0;
        while(!setList.isEmpty()) {
            answer[idx++] = setList.poll();
        }
        return answer;
    }

    public void getCourseSet(int start, int cnt, String set, int courseIdx) { // 조합
        if(cnt == N) {
            setByCourse[courseIdx].put(set, setByCourse[courseIdx].getOrDefault(set, 0) + 1);
            max[courseIdx] = Math.max(max[courseIdx], setByCourse[courseIdx].get(set));
            return;
        }
        for(int i = start; i < order.length(); i++) {
            getCourseSet(i + 1, cnt + 1, set + order.charAt(i), courseIdx);
        }
    }
}
