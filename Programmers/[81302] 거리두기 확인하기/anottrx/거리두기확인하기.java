class Solution {   
    static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 위 오른쪽 아래 왼쪽
    static char[][] map;
    static boolean[][] visited;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5]; // 답
        map = new char[5][5]; // 대기실
        visited = new boolean[5][5]; // 방문 여부

        for (int tc = 0; tc < places.length; tc++) {
            for (int i = 0; i < 5; i++) {
                map[i] = places[tc][i].toCharArray();
            }

            boolean keepingDistance = true; // 우선 거리두기하고 있다고 초기화
            checking: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == 'P') { // P일 경우
                        visited[i][j] = true; // 해당 위치는 방문했다고 체크 (O일 때 P라고 확인하지 않기 위함)
                        keepingDistance = checkNear(i, j, map[i][j]); // x,y좌표와 해당 자리
                        if (!keepingDistance) { // 주변 확인했는데 거리두기를 안 지키고 있다면 확인 끝내기
                            break checking;
                        }
                        visited[i][j] = false; // 다음 자리가 확인할 수 있도록 해당 위치 방문체크 해제
                    }
                }
            }
            answer[tc] = (keepingDistance) ? 1 : 0; // 거리두기 true면 1, false면 0
        }
        return answer;
    }
    
    private static boolean checkNear(int x, int y, char seat) { // xy좌표와 해당 자리에 있는 것
        for (int i = 0; i < 4; i++) {
            int dx = x + d[i][0];
            int dy = y + d[i][1];
            if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5 && !visited[dx][dy]) { // 대기실 내일 경우, 또 아직 방문하지 않았다면 확인하기 (방문한 경우: 맨 처음 확인하게 했던 P)
                if (map[dx][dy] == 'P') { // P가 있다면 false 리턴하고 끝
                    return false;
                } else if (map[dx][dy] == 'O' && seat == 'P') { // P 주변에 O가 있을 경우
                    boolean isPNear = checkNear(dx, dy, 'O'); // 주변을 다시 확인하기
                    if (!isPNear) { // 만약 주변이 false라면 false 리턴하고 끝
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
