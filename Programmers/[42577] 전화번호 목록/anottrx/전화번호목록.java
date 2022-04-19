// 처음에는 완전탐색으로 가려다가 아닌 것 같아서 질문글 https://programmers.co.kr/questions/25595 보고 힌트 얻음

import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book); // 정렬
        for(int i=1; i<phone_book.length; i++) {
            if(checkNear(phone_book[i-1], phone_book[i])) { // 이전번호와 현재번호를 비교
                answer = false;
                break;
            }
        }
        return answer;
    }

    public static boolean checkNear(String bef, String cur) { // 이전번호와 현재번호를 비교
        if(!cur.contains(bef) || cur.length()<bef.length()) { // 현재번호에 이전번호가 없거나, 현재번호가 더 짧다면 false
            return false;
        }

        for(int i=0; i<bef.length(); i++) { // 현재번호와 이전번호를 0번째 위치부터 이전번호 끝까지 비교
            if(cur.charAt(i)!=bef.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
