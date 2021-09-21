import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        ArrayList<String> str1Arr = new ArrayList<>();
        ArrayList<String> str2Arr = new ArrayList<>();
        getTwoWord(str1, str1Arr); // 두 글자씩 끊어서 다중집합(ArrayList)의 원소로 만들기
        getTwoWord(str2, str2Arr);

        int union = str1Arr.size() + str2Arr.size(); // 합집합. 우선 두 집합을 더하기 (이후 중복 제거)
        int intersection = getJSet(str1Arr, str2Arr); // 교집합
        union = union - intersection; // 교집합 빼서 최종 합집합 요소 개수 구하기
        if (union == 0) { // 집합 A와 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으므로 따로 J(A,B) = 1로 정의
            answer = 65536;
        } else {
            answer = (int) (((double) intersection / union) * 65536); // double 사용해서 0 나오는 것 막기
        }

        return answer;
    }

    private static void getTwoWord(String str, ArrayList<String> strArr) { // str를 두 글자씩 끊어 strArr에 넣기
        str = str.toLowerCase(); // 소문자로 바꾸기

        for (int i = 0; i < str.length() - 1; i++) {
            char firstChar = str.charAt(i);
            char secondChar = str.charAt(i + 1);
            if (firstChar >= 97 && firstChar <= 122 && secondChar >= 97 && secondChar <= 122) {
                // 알파벳 소문자로 이루어진 경우만 ArrayList에 넣기
                strArr.add(String.valueOf(firstChar) + String.valueOf(secondChar));
            }
        }
    }

    private static int getJSet(ArrayList<String> str1Arr, ArrayList<String> str2Arr) { // str1Arr와 str2Arr의 교집합 요소 개수 구하기
        int cnt = 0;
        for (int i = 0; i < str1Arr.size(); i++) {
            if (str2Arr.contains(str1Arr.get(i))) { // 해당 원소를 가지고 있다면
                str2Arr.remove(str1Arr.get(i)); // 이후 중복되지 않게 제거하기
                cnt++; // 횟수 세기
            }
        }
        return cnt;
    }

}
