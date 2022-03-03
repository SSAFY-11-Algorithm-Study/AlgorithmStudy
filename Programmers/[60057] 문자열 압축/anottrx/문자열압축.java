class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE; // 최댓값으로 초기화
      
        if (s.length() == 1) { // s 문자열 길이가 1이라면 그냥 바로 출력하고 끝
            answer = 1;
        } else {
            // 1개부터 (s문자열 길이의 절반+1)까지 모두 한번씩 시도하기 => +1 안 하면 답 안나옴
            for (int i = 1; i < (s.length() / 2 + 1); i++) {
                int cnt = 1; // 쪼갠 단위 개수는 1부터 시작
                String checkStr = s.substring(0, i); // 처음부터 i개만큼 문자 우선 저장
                String makeStr = checkStr; // 압축 문자열 결과물 (문제와 다르게 a2b2ac3 형태로 출력할 것)
                int last = 0; // 맨마지막에 단위 길이 부족으로 출력 못한 문자도 makeStr에 포함하기 위함
                for (int j = i; j < s.length() - i + 1; j = j + i) {
                    last = j + i; // 마지막 위치 매번 저장
                    String cur = s.substring(j, j + i);
                    if (checkStr.equals(cur)) { // 이전 단위와 동일하면 cnt 증가
                        cnt++;
                    } else { // 다르다면
                        if (cnt != 1) { // 2개 이상의 단위가 반복되었다면 반복한 횟수를 문자열 결과물에 더할 것
                            makeStr = makeStr + Integer.toString(cnt);
                            cnt = 1;
                        }
                        checkStr = cur; // 새로운 문자 단위 저장
                        makeStr = makeStr + checkStr; // 문자열 결과물에 단위 합치기
                    }
                }
                if (cnt != 1) { // 다 끝나고 나왔을 때 2번 이상 반복되었다면 횟수를 문자열 결과물에 더할 것
                    makeStr = makeStr + Integer.toString(cnt);
                }
                // 다 끝났는데 길이 부족으로 출력 못한 것은 (s.length() - last)만큼 더해 길이 계산 제대로 하기
                answer = Math.min(answer, makeStr.length() + (s.length() - last));
            }
        }
        return answer;
    }
}
