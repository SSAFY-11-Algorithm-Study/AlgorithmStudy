import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        //나가는 순서대로 오름차순 정렬
        Arrays.sort(routes, (a1, a2) -> Integer.compare(a1[1], a2[1]));
        
        int min = routes[0][1]; //맨 처음 나가는 시간
        answer++;
        for(int i=1; i<routes.length; i++){
            if(min < routes[i][0]){ //그 다음꺼 시작 시간보다 min이 작음 -> 그 다음꺼 나갈 때 카메라 설치 필요
                answer++;
                min = routes[i][1]; //그 다음꺼의 나가는 걸로 min값 갱신
            }
            //그게 아니면 카메라 설치할 필요 X. 그 다음꺼 살펴봄
        }
        
        return answer;
    }
}
