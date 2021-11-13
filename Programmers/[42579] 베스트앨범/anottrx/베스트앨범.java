import java.util.*;

class Solution {
    
    static class genresCnt implements Comparable<genresCnt> { // 장르를 많이 재생된 순서대로
        private String genres;
        private int cnt;

        public genresCnt(String genres, int cnt) {
            this.genres = genres;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(genresCnt o) {
            return o.cnt - this.cnt;
        }
    }

    static class genresMax implements Comparable<genresMax> { // 하나의 장르 안에서 많이 재생된 순서
        private int num, cnt;

        public genresMax(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(genresMax o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num; // 고유번호 낮은 순서대로
            }
            return o.cnt - this.cnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        HashMap<String, int[]> genresHM = new HashMap<>(); // 장르명, 해당 장르의 노래 재생 횟수 리스트
        int[] temp;
        for (int i = 0; i < genres.length; i++) {
            if (genresHM.containsKey(genres[i])) {
                temp = genresHM.get(genres[i]);
            } else {
                temp = new int[genres.length];
            }
            temp[i] = plays[i];
            genresHM.put(genres[i], temp);
        }

        HashMap<String, int[]> genresCntNumHM = new HashMap<>(); // 장르명, [최대횟수,두번째최대횟수]
        ArrayList<genresCnt> genresMaxCnt = new ArrayList<>(); // 장르를 재생 많이한 순서대로
        for (String genresKey : genresHM.keySet()) {
            ArrayList<genresMax> genresMaxArr = new ArrayList<>(); // 해당 장르를 재생 많이한 순서대로
            int cnt = 0; // 해당 장르를 재생한 총 횟수
            temp = genresHM.get(genresKey);
            for (int i = 0; i < genres.length; i++) {
                cnt += temp[i];
                genresMaxArr.add(new genresMax(i, temp[i]));
            }
            genresMaxCnt.add(new genresCnt(genresKey, cnt));

            Collections.sort(genresMaxArr); // 장르 내에서 많이 재생된 노래를 먼저 수록

            int max1Num = genresMaxArr.get(0).num; // 해당 장르 재생 최댓값
            int max2Num = -1; // 해당 장르 재생 두번째 최댓값
            if (genresMaxArr.size() > 1 && genresMaxArr.get(1).cnt > 0) {
                max2Num = genresMaxArr.get(1).num;
            }
            genresCntNumHM.put(genresKey, new int[] { max1Num, max2Num });
        }

        Collections.sort(genresMaxCnt); // 속한 노래가 많이 재생된 장르를 먼저 수록

        ArrayList<Integer> answerArr = new ArrayList<>();
        for (int i = 0; i < genresMaxCnt.size(); i++) {
            genresCnt curGenres = genresMaxCnt.get(i);
            int[] curMax = genresCntNumHM.get(curGenres.genres);
            answerArr.add(curMax[0]);
            if (curMax[1] != -1) { // 장르 내 노래가 1개라면 1개만 출력하고, 그외는 2개 출력
                answerArr.add(curMax[1]);
            }
        }

        answer = new int[answerArr.size()]; // 답
        for (int i = 0; i < answerArr.size(); i++) {
            answer[i] = answerArr.get(i);
        }
        
        return answer;
    }
}
