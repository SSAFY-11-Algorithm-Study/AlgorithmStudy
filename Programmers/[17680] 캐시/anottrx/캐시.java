import java.util.*;

class Solution {

    static class Cache {
        String name; // 도시 이름
        int cnt; // 캐시된 횟수

        public Cache(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<Cache> arr = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase(); // 모두 소문자로 바꾸기
            if (cacheSize == 0) { // cacheSize가 0이라면 무조건 5 더하기만 하기
                answer += 5;
                continue;
            }

            boolean isCached = false; // 캐시되었는지 확인
            for (int j = 0; j < arr.size(); j++) {
                Cache cur = arr.get(j);
                if (cur.name.equals(city)) { // 도시가 캐시에 존재한다면
                    int cnt = cur.cnt;
                    arr.remove(cur);
                    arr.add(new Cache(city, cnt + 1)); // 횟수 1 증가시키고 맨 뒤로 이동시키기
                    answer += 1;
                    isCached = true;
                    break;
                }
            }

            if (!isCached) { // 캐시 적용이 안 이루어졌다면
                if (arr.size() == cacheSize) { // 캐시가 꽉 찼다면
                    arr.remove(0); // 가장 마지막에 사용된 도시를 지우기
                }
                arr.add(new Cache(city, 1)); // 새롭게 1번 캐시된 도시 집어넣기
                answer += 5;
            }
        }

        return answer;
    }
}
