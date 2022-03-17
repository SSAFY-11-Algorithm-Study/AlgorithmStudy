// 틀림 => 중간부터 꼬인 듯

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1, s.length());
        s = s.substring(0, s.length() - 1);

        ArrayList<StringCount> arrList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                sb = new StringBuilder();
            } else if (s.charAt(i) == '}') {
                int cnt = 0;
                for (int j = 0; j < sb.toString().length(); j++) {
                    if (sb.toString().charAt(j) == ',') {
                        cnt++;
                    }
                }
                arrList.add(new StringCount(sb.toString(), cnt));
                i++;
            } else {
                sb.append(s.charAt(i));
            }
        }

        Collections.sort(arrList);

        // 여기 아래부터 틀린 듯
        ArrayList<Integer> ansewrList = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < arrList.size(); i++) {
            sb = new StringBuilder();
            for (int j = 0; j < arrList.get(i).str.length(); j++) {
                if (arrList.get(i).str.charAt(j) != ',') {
                    sb.append(arrList.get(i).str.charAt(j));
                } else {
                    int num = Integer.parseInt(sb.toString());
                    if (!hs.contains(num)) {
                        hs.add(num);
                        ansewrList.add(num);
                    }
                    sb = new StringBuilder();
                }
            }
        }
        answer = new int[ansewrList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ansewrList.get(i);
        }
        return answer;
    }
    private static class StringCount implements Comparable<StringCount> {
        private String str;
        private int cnt;

        public StringCount(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(StringCount strcnt) {
            if (strcnt.cnt < cnt) {
                return 1;
            } else if (strcnt.cnt > cnt) {
                return -1;
            }
            return 0;
        }

    }
}
