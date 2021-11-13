import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {

    static int N;
    static ArrayList<Fireball>[][] map;
    static int[][] d = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

    static private class Fireball {
        int r, c, m, d, s;

        public Fireball(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N]; // 모든 map 위치마다 파이어볼 리스트가 존재

        ArrayList<Fireball> first = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = first;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0,0부터 시작하기 위함
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            ArrayList<Fireball> fireballArr = new ArrayList<>();
            fireballArr.add(new Fireball(r, c, m, d, s));
            map[r][c] = fireballArr;
        }

        for (int i = 0; i < K; i++) {
            moveFireball();
            checkMap();
        }

        int cnt = 0; // 남아있는 파이어볼 질량의 합
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayList<Fireball> tempArr = map[i][j];
                for (int k = 0; k < tempArr.size(); k++) {
                    cnt += tempArr.get(k).m;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void moveFireball() { // 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
        ArrayList<Fireball>[][] tempMap = new ArrayList[N][N]; // 임시 배열
        ArrayList<Fireball> first = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = first;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayList<Fireball> curArr = map[i][j];
                for (int k = 0; k < curArr.size(); k++) {
                    Fireball cur = curArr.get(k);
                    int dr = cur.r + d[cur.d][0] * cur.s;
                    int dc = cur.c + d[cur.d][1] * cur.s;
                    if (dr < 0) { // 1번 행은 N번과 연결, 1번 열은 N번 열과 연결
                        int tempDr = dr * (-1);
                        tempDr = (tempDr % N) * (-1);
                        dr = N + tempDr;
                    }
                    if (dr >= N) {
                        dr = dr % N;
                    }
                    if (dc < 0) {
                        int tempDc = dc * (-1);
                        tempDc = (tempDc % N) * (-1);
                        dc = N + tempDc;
                    }
                    if (dc >= N) {
                        dc = dc % N;
                    }
                    ArrayList<Fireball> tempArr;
                    if (tempMap[dr][dc].size() > 0) {
                        tempArr = tempMap[dr][dc];
                    } else {
                        tempArr = new ArrayList<>();
                    }
                    tempArr.add(new Fireball(dr, dc, cur.m, cur.d, cur.s));
                    tempMap[dr][dc] = tempArr;
                }
            }
        }
        for (int i = 0; i < N; i++) { // 복사
            for (int j = 0; j < N; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }

    private static void checkMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayList<Fireball> curArr = map[i][j];
                if (curArr.size() > 1) { // 2개 이상의 파이어볼이 있는 칸
                    int totalM = 0, totalS = 0, multi = 1;
                    boolean isAllEven = true;
                    for (int k = 0; k < curArr.size(); k++) {
                        totalM += curArr.get(k).m;
                        totalS += curArr.get(k).s;
                        multi *= curArr.get(k).d;
                        if (curArr.get(k).d % 2 == 1) {
                            isAllEven = false;
                        }
                    }
                    totalM = totalM / 5; // 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋
                    totalS = totalS / curArr.size(); // 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋
                    ArrayList<Fireball> tempMapArr = new ArrayList<>();
                    if (totalM > 0) { // 질량이 0인 파이어볼은 소멸되어 없어진다.
                        if (multi % 2 == 1 || isAllEven) { // 모두 홀수이거나 모두 짝수이면
                            for (int k = 0; k < 8; k = k + 2) { // 방향은 0, 2, 4, 6
                                tempMapArr.add(new Fireball(i, j, totalM, k, totalS));
                            }
                        } else { // 그렇지 않으면 1, 3, 5, 7
                            for (int k = 1; k < 8; k = k + 2) {
                                tempMapArr.add(new Fireball(i, j, totalM, k, totalS));
                            }
                        }
                    }
                    map[i][j] = tempMapArr; // 질량이 0인 경우는 빈 리스트가 들어감
                }
            }
        }
    }
}
