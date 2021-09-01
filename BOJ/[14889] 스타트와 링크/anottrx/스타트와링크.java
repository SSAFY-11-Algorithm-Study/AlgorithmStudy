// 출처: 백준저지 14889번 스타트와 링크 https://www.acmicpc.net/problem/14889

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ14889 {
    static int N, scoreTemp;
    static ArrayList<int[]> startTeam, linkTeam;
    static boolean[] isSelectedInTeam;
    static int[][] map;
    static int[] picked, diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N명의 사람들
        map = new int[N][N];
        for (int i = 0; i < N; i++) { // 입력
            String[] strArr = (br.readLine()).split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        // 1. N명의 사람들을 두 팀(각 N/2명씩)으로 나눈다.
        startTeam = new ArrayList<>(); // 스타트팀. 예: (1,2,3), (1,2,4),...
        linkTeam = new ArrayList<>(); // 링크팀. 예: (4,5,6), (3,5,6),...
        isSelectedInTeam = new boolean[N]; // true면 스타트팀, false면 링크팀
        MakeTeam(0);

        diff = new int[startTeam.size()]; // 차이
        // 2. 각 팀에서 2명씩 고르고, 능력치(ij+ji)를 각각 구해서, 모두 더한다.
        // 3. 그 후 팀끼리의 차이를 구한다.
        getScoreDiff(startTeam, 1); // 스타트팀
        getScoreDiff(linkTeam, -1); // 링크팀. (스타트팀-링크팀)을 위해 -1이 필요.

        // 4. 구한 능력치의 차이 중 최솟값을 찾는다
        int answer = diff[0];
        for (int i = 1; i < diff.length; i++) {
            answer = Math.min(answer, diff[i]); // 최솟값 찾기
        }
        System.out.println(answer); // 답
    }

    private static void MakeTeam(int cnt) { // N/2명씩 두 팀을 구하기
        if (cnt == N) {
            int counts = 0;
            for (int i = 0; i < N; i++) {
                if (isSelectedInTeam[i]) {
                    counts++;
                }
            }
            if (counts == N / 2) { // N/2만큼 구해졌다면 제대로 팀이 나뉜 것
                int[] start = new int[N / 2];
                int[] link = new int[N / 2];
                int s = 0;
                int l = 0;
                for (int i = 0; i < N; i++) {
                    if (isSelectedInTeam[i]) { // 선택되었다면 스타트팀에 넣기
                        start[s] = i;
                        s++;
                    } else { // 선택되지 않았다면 링크팀에 넣기
                        link[l] = i;
                        l++;
                    }
                }
                startTeam.add(start);
                linkTeam.add(link);
            }
            return;
        }
        isSelectedInTeam[cnt] = true;
        MakeTeam(cnt + 1);
        isSelectedInTeam[cnt] = false;
        MakeTeam(cnt + 1);
    }

    private static void getScoreDiff(ArrayList<int[]> team, int flag) {
        for (int i = 0; i < team.size(); i++) {
            scoreTemp = 0; // 점수를 우선 0으로 초기화
            int[] pick = team.get(i); // team 리스트에서 하나 고르기
            picked = new int[2]; // 구한 팀 중 2명씩 다시 고를 것
            PickTwo(0, 0, pick); // pick 팀에서 2명 고르기
            diff[i] = Math.abs(diff[i] + scoreTemp * flag);
            // 차이를 구하기 위해 스타트팀은 더하고 링크팀은 뺄 것. 차이를 구하는 것이기 때문에 절댓값.
        }
    }

    private static void PickTwo(int cnt, int start, int[] pick) { // 조합
        if (cnt == 2) { // 2명 고르면 해당 쌍의 능력치 구하기
            int a = picked[0];
            int b = picked[1];
            scoreTemp = scoreTemp + map[a][b] + map[b][a];
            return;
        }
        for (int i = start; i < pick.length; i++) {
            picked[cnt] = pick[i];
            PickTwo(cnt + 1, i + 1, pick);
        }
    }
}
