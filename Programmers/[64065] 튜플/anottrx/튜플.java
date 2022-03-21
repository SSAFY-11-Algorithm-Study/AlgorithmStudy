import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};

        HashMap<Integer, Integer> hm = new HashMap<>();
        s = s.replaceAll("\\{", "").replaceAll("\\}", ""); // {, } 제거
        String[] strList = s.split(",");
        for (int i = 0; i < strList.length; i++) { // 숫자 확인하면서 개수 세기
            int tempNum = Integer.parseInt(strList[i]);
            if (hm.containsKey(tempNum)) {
                hm.put(tempNum, hm.get(tempNum) + 1);
            } else {
                hm.put(tempNum, 1);
            }
        }

        ArrayList<int[]> arrList = new ArrayList<>();
        for (int key : hm.keySet()) {
            arrList.add(new int[] { key, hm.get(key) }); // {key, key개수}를 리스트에 담기
        }
        Collections.sort(arrList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        answer = new int[arrList.size()]; // 답
        for (int i = 0; i < arrList.size(); i++) {
            answer[i] = arrList.get(i)[0];
        }
        return answer;
    }
}
