import java.util.*;
//1번 테케만 안 되는데 왜 그런지 도저히 모르겠음ㅠ
class Solution {
    
    static ArrayList<String> list = new ArrayList<>();
    static boolean[] visited;
    public String[] solution(String[][] tickets) {
        
        for(int i=0; i<tickets.length; i++){
            if(tickets[i][0].equals("ICN")){
                visited = new boolean[tickets.length]; //각 티켓이 선택되었는지 여부 저장
                visited[i] = true;
                dfs(i, 1, "ICN ", tickets); //출발지의 인덱스 번호, 선택된 티켓 수, 경로, 방문 배열, 티켓 정보
            }
        }
        Collections.sort(list);
        
        String t_answer = list.get(0);
        String[] answer = t_answer.split(" ");
        return answer;
    }
    
    public void dfs(int i, int cnt, String str, String[][] tickets){

        str += tickets[i][1] + " "; //도착지를 더해 감
        visited[i] = true;
        
        if(cnt==tickets.length){ //표가 다 선택됐다면
            list.add(str);
            return;
        }
        
        String target = tickets[i][1];
        for(int j=0; j<tickets.length; j++){
            if(!visited[j] && tickets[j][0].equals(target)){ //다음 티켓이 아직 선택 안됐고 이어진다면
                dfs(j, ++cnt, str, tickets);
            }
        }
    }
}
