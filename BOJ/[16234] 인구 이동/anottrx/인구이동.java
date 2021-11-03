import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {

    static int N, L, R, map[][];
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = countMovingDays();

        System.out.println(cnt);
    }

    private static int countMovingDays() { // 인구이동 발생일수 구하기
        int cnt = 0;
        boolean peopleMoved = false; // 전날 인구이동이 일어났는지 여부

        while (true) { // 첫날이거나 이전날에 인구 이동이 일어났다면 반복
            peopleMoved = movePeople(); // 이동시키기
            if (!peopleMoved) { // 그 외는 끝내기
                break;
            }
            cnt++;
        }

        return cnt; // 인구이동 발생일수 리턴
    }

    private static boolean movePeople() { // 인구이동 시키기
        boolean[][] visited = new boolean[N][N];
        ArrayList<ArrayList<int[]>> movePeopleArrAll = new ArrayList<>(); // 이동시키는 국가들
        ArrayList<int[]> oneMovedPeople = new ArrayList<>();
        boolean moved = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문 안 했다면 bfs 통해 주변 국가와 차이 구하기
                    oneMovedPeople = changePeopleCnt(i, j, visited); // 현재 위치에서 주변 탐색하면서 차이 구하기
                    if (oneMovedPeople != null) { // 이동 가능한 경우라면 이후 인구수 바꾸기 위해 인접한 것끼리 arraylist에 담기
                        movePeopleArrAll.add(oneMovedPeople);
                        moved = true;
                    }
                }
            }
        }

        if (moved) { // 이동한 경우가 있다면 인구수 조정하기
            changeMap(movePeopleArrAll);
        }

        return moved;
    }

    private static ArrayList<int[]> changePeopleCnt(int x, int y, boolean[][] visited) {
        ArrayList<int[]> movePeopleArr = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y });
        visited[x][y] = true;
        movePeopleArr.add(new int[] { x, y }); // 맨 처음은 무조건 리스트에 담기

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int dx = cur[0] + d[i][0];
                int dy = cur[1] + d[i][1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy]) {
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[dx][dy]);
                    if (diff >= L && diff <= R) {
                        q.offer(new int[] { dx, dy });
                        visited[dx][dy] = true;
                        movePeopleArr.add(new int[] { dx, dy }); // 이동한 국가를 리스트에 담기
                    }
                }
            }
        }

        if (movePeopleArr.size() == 1) { // 리스트가 1개라면 이동 가능한 국가가 없기 때문에 null 리턴
            return null;
        } else {
            return movePeopleArr;
        }
    }

    private static void changeMap(ArrayList<ArrayList<int[]>> movePeopleArrAll) { // 인구수 조정하기
        int size = movePeopleArrAll.size();
        for (int i = 0; i < size; i++) {
            int countryCnt = movePeopleArrAll.get(i).size();
            int total = 0;
            for (int j = 0; j < countryCnt; j++) { // 평균 구하기 위해 total 구하기
                int[] cur = movePeopleArrAll.get(i).get(j);
                total += map[cur[0]][cur[1]];
            }
            total = total / countryCnt;
            for (int j = 0; j < countryCnt; j++) { // 국가들 인구수를 평균으로 바꾸기
                int[] cur = movePeopleArrAll.get(i).get(j);
                map[cur[0]][cur[1]] = total;
            }
        }
    }
}
