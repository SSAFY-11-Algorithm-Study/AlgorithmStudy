import java.util.*;

/*
  info가 "java backend junior pizza 150" 라면
  javabackendjuniorpizza
  -backendjuniorpizza
  java-juniorpizza
  javabackend-pizza
  javabackendjunior-
  --juniorpizza
  java--pizza
  .
  .
  .
  등등의 query에 검색된다.
  dfs를 이용해 모두 hashmap에 넣어준다.
*/

class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        
        for(int i = 0; i < info.length; i++) { // dfs로 가능한 모든 query를 hashmap에 넣어줌
            String[] infos = info[i].split(" ");
            dfs(0, "", infos);
        }

        map.forEach((key, value) -> { // 이분 탐색을 위한 정렬
            Collections.sort(value);    
        });
        
        int[] answer = new int[query.length];
        
        for(int i = 0; i < query.length; i++) {
            String[] filter = query[i].replace("and ", "").split(" ");
            String queryString = "";
            for(int j = 0; j < 4; j++) {
                queryString += filter[j]; // hashmap의 key형태로 String 만들어줌
            }

            int score = Integer.parseInt(filter[4]);
            if(!map.containsKey(queryString)) continue;
            ArrayList<Integer> list = map.get(queryString);
            answer[i] = list.size() - bsearch(list, score);
        }
        return answer;
    }
    public void dfs(int cnt, String str, String[] infos) {
        if(cnt == 4) {
            int score = Integer.parseInt(infos[4]);
            if(!map.containsKey(str)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(str, list);
            } else map.get(str).add(score);
            return;
        }
        
        dfs(cnt + 1, str + infos[cnt], infos);
        dfs(cnt + 1, str + "-", infos);
    }
    
    public int bsearch(ArrayList<Integer> list, int score) {
        int min = 0;
        int max = list.size() - 1;
        while(min <= max) {
            int mid = (min + max) / 2;
            if(list.get(mid) < score) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return min;
    }
}
