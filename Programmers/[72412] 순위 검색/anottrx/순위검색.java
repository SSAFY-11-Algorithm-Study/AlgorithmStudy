/*
https://tech.kakao.com/2021/01/25/2021-kakao-recruitment-round-1/ 
https://github.com/SSAFY-11-Algorithm-Study/AlgorithmStudy/blob/main/Programmers/%5B72412%5D%20%EC%88%9C%EC%9C%84%20%EA%B2%80%EC%83%89/penglingss/%EC%88%9C%EC%9C%84%EA%B2%80%EC%83%89.java
덕분에 시간 초과 문제를 해결했습니다 (정렬, 이진 탐색(lower bound))
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    static HashMap<String, ArrayList<Integer>> applyMap; // info의 조건: <(언어+직군+경력+소울푸드), (점수)>
    static String[] strInfo; // 부분집합 위함
    static boolean[] selectedInfo; // 부분집합 위함 (조건 또는 -)

    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        applyMap = new HashMap<>(); // 생성

        for (int i = 0; i < info.length; i++) {
            strInfo = splitAndGetScore(info[i]); // info의 (언어, 직군, 경력, 소울푸드, 점수)
            selectedInfo = new boolean[4];
            makeApplyInfo(0); // 하나의 info를 가지고 16가지 경우 만들기
        }

        applyMap.forEach((str, score) -> { // 정렬할 때 시간 초과 해결
            Collections.sort(score);
        });

        answer = new int[query.length]; // query 개수만큼 답
        for (int i = 0; i < query.length; i++) {
            String[] splitedQuery = splitAndGetScore(query[i]); // query의 (언어, 직군, 경력, 소울푸드, 점수)
            String strQuery = "";
            for (int j = 0; j < splitedQuery.length - 1; j++) { // 검색 조건 (언어+직군+경력+소울푸드)
                strQuery = strQuery + splitedQuery[j]; // javabackendjuniorpizza
            }
            int score = Integer.parseInt(splitedQuery[splitedQuery.length - 1]); // 검색 조건 (점수) (100)
            answer[i] = countApply(strQuery, score); // 검색 조건에 해당하는 지원자 수 구하기
        }

        return answer; // 답 출력
    }

    private static String[] splitAndGetScore(String infoQuery) { // info, query
        String[] result = new String[5]; // {언어, 직군, 경력, 소울푸드, 점수}
        String[] splited = infoQuery.split(" "); // 띄어쓰기 제외
        int j = 0;
        for (int i = 0; i < splited.length; i++) {
            if (!splited[i].equals("and")) { // and 제외
                result[j] = splited[i];
                j++;
            }
        }
        return result;
    }

    private static void makeApplyInfo(int cnt) { // (언어+직군+경력+소울푸드). 각각 - 일때와 아닐 때도 포함
        if (cnt == 4) {
            String str = "";
            for (int i = 0; i < 4; ++i) {
                str = (selectedInfo[i]) ? (str + strInfo[i]) : (str + "-");
            }
            addInfo(str, Integer.parseInt(strInfo[4])); // 완성된 지원자 조건 가지고 해시맵에 넣으러 가기
            return;
        }
        selectedInfo[cnt] = true;
        makeApplyInfo(cnt + 1);
        selectedInfo[cnt] = false;
        makeApplyInfo(cnt + 1);
    }

    private static void addInfo(String str, int score) { // 지원자 조건을 해시맵에 넣기
        ArrayList<Integer> scoreArr;
        if (applyMap.containsKey(str)) { // 지원자 조건이 해시맵에 있다면 찾아서 추가
            scoreArr = applyMap.get(str);
        } else { // 없다면 새로 생성해서 추가
            scoreArr = new ArrayList<>();
        }
        scoreArr.add(score);
        applyMap.put(str, scoreArr);
    }

    private static int countApply(String strQuery, int score) {
        if (!applyMap.containsKey(strQuery)) { // 검색 조건에 해당하는 지원자가 없다면 0 리턴
            return 0;
        }
        ArrayList<Integer> scoreArr = applyMap.get(strQuery); // 검색 조건에 해당하는 정렬된 점수 모음
        // Collections.sort(scoreArr); // 시간 초과

        int start = 0; // 점수 리스트의 시작 인덱스
        int end = scoreArr.size() - 1; // 점수 리스트의 마지막 인덱스
        while (start <= end) {
            int mid = (start + end) / 2;
            if (scoreArr.get(mid) < score) { // 탐색한 점수 < 원하는 점수
                start = mid + 1;
            } else { // 탐색한 점수 >= 원하는 점수
                end = mid - 1;
            }
        }
        int cnt = scoreArr.size() - start; // 조건에 맞는 지원자 중 점수 미만인 사람 제외
        return cnt;
    }
}
