import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, map[][], min, virusNum;
    static ArrayList<Virus> virusList; // 처음 입력될 때 모든 바이러스의 위치 저장
    static int[] selected; // 조합
    static Queue<Virus> q; // BFS
    static boolean[][] visited; // BFS
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static private class Virus { // 바이러스의 위치
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        virusList = new ArrayList<>();
        q = new LinkedList<>();
        min = 2501; // 답 초기화. 연구소의 최대 크기가 50이라서 2501로 설정

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 입력받을 때 바이러스 위치 리스트에 담기
                    virusList.add(new Virus(i, j));
                }
            }
        }

        virusNum = virusList.size(); // 조합 위해 바이러스 전체 개수 저장
        selected = new int[M]; // 조합: M개의 바이러스 선택
        getMVirus(0, 0); // 조합하러 이동

        if (min == 2501) { // 다 했는데도 min값 변하지 않았다면 이동 불가능하기 때문에 -1
            bw.write(-1 + "\n");
        } else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void getMVirus(int cnt, int start) { // M개의 바이러스 선택 (조합)
        if (cnt == M) { // 다 선택되었다면
            min = Math.min(min, spreadVirus(selected)); // BFS하러 보냄
            return;
        }

        for (int i = start; i < virusNum; i++) {
            selected[cnt] = i;
            getMVirus(cnt + 1, i + 1);
        }
    }

    private static int spreadVirus(int[] selected) { // 선택된 바이러스를 가지고 퍼뜨리기 (BFS)
        for (int i = 0; i < N; i++) { // 매번 visited 배열을 false로 설정
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
        q.clear(); // 큐도 매번 비우기

        for (int i = 0; i < M; i++) { // 선택된 바이러스 위치를 true로 바꾸고 큐에 담기
            Virus vv = virusList.get(selected[i]);
            visited[vv.x][vv.y] = true;
            q.add(vv);
        }

        int cnt = 0; // 바이러스를 퍼뜨리는 시간
        int size = 0; // 큐 사이즈
        int blanks = 0; // 비활성화 바이러스 만난 시간
        while (!q.isEmpty()) {
            boolean countUp = false; // 비활성화 바이러스만 만났다고 우선 초기화

            if (cnt > min) { // cnt가 min보다 커지면 의미가 없기 때문에 리턴
                return 2501;
            }

            size = q.size(); // 현재 큐 사이즈 계산
            for (int j = 0; j < size; j++) { // 큐 사이즈만큼 반복
                Virus cur = q.poll();
                visited[cur.x][cur.y] = true;

                for (int i = 0; i < 4; i++) {
                    int dx = cur.x + d[i][0];
                    int dy = cur.y + d[i][1];
                    if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy]) {
                        if (map[dx][dy] == 0) { // 0이라면 바이러스가 퍼질 수 있다
                            visited[dx][dy] = true;
                            q.add(new Virus(dx, dy));
                            countUp = true; // 빈칸도 만났기 때문에 true
                        } else if (map[dx][dy] == 2) { // 2라면 비활성바이러스를 활성상태로 바꾼다
                            visited[dx][dy] = true;
                            q.add(new Virus(dx, dy));
                        }
                    }
                }
            }

            if (!countUp) { // 비활성화 바이러스만 만났다면
                blanks++;
            } else { // 빈칸도 만났다면
                cnt = cnt + blanks + 1;
                blanks = 0; // 초기화
            }
        }

        for (int i = 0; i < N; i++) { // 다 끝났는데도 방문 안했고 0인 곳이 있다면 바이러스가 다 안 퍼진 것
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    return 2501;
                }
            }
        }

        return cnt; // 여기까지 오면 여태까지 나온 값 중 최솟값이기 때문에 cnt 리턴
    }
}
