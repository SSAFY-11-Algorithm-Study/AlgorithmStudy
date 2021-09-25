import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        String[] numStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(numStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt((o1 + o2));
                int b = Integer.parseInt((o2 + o1));
                if (a > b) { // a가 더 크면 음수 (예: 210 102)
                    return -1;
                } else if (a < b) { // b가 더 크면 양수 (예: 106 610)
                    return 1;
                } else { // 같으면 0
                    return 0;
                }
                // 위의 9줄(16~24)을 한 줄로 나타나면 다음과 같다
                // return (o2 + o1).compareTo(o1 + o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String str : numStr) { // 정렬이 완성되었으니 합치기
            sb.append(str);
        }
        answer = sb.toString();

        int cntZero = 0;
        for (int i = 0; i < answer.length(); i++) { // 앞자리가 0인지 확인하고 있다면 없애기
            if (answer.charAt(i) == '0') {
                cntZero++;
            } else {
                break;
            }
        }
        answer = (answer.length() == cntZero) ? "0" : answer.substring(cntZero); // 0으로만 이루어졌다면 0 하나 출력

        return answer;
    }
    
}
