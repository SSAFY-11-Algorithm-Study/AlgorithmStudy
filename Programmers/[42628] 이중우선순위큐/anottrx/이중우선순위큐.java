import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("D 1") && tm.size() > 0) { // 최댓값을 삭제
                int last = tm.lastKey();
                if (tm.get(last) > 1) { // 최댓값이 1개 이상
                    tm.put(last, tm.get(last) - 1);
                } else {
                    tm.remove(last);
                }
            } else if (operations[i].equals("D -1") && tm.size() > 0) { // 최솟값을 삭제
                int first = tm.firstKey();
                if (tm.get(first) > 1) { // 최솟값이 1개 이상
                    tm.put(first, tm.get(first) - 1);
                } else {
                    tm.remove(first);
                }
            } else if (operations[i].charAt(0) == 'I') { // 숫자를 삽입
                String[] splited = operations[i].split(" ");
                int num = Integer.parseInt(splited[1]);
                tm.put(num, tm.getOrDefault(num, 0) + 1);
            }
        }

        int size = tm.size();
        if (size == 0) { // 큐가 비어있으면 [0,0]
            answer = new int[] { 0, 0 };
            return answer;
        }

        answer = new int[2]; // 비어있지 않으면 [최댓값, 최솟값]
        answer[0] = tm.lastKey();
        answer[1] = tm.firstKey();
        return answer;
    }
}
