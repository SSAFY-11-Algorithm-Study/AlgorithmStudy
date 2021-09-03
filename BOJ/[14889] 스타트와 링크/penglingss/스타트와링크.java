import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N, min;
    static int[][] status;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        min = Integer.MAX_VALUE;
        N = sc.nextInt();
        status = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                status[i][j] = sc.nextInt();
            }
        }

        for (int bit = 1; bit < (1 << N); bit++) { // 조합
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if ((bit & (1 << i)) != 0) {
                    cnt++;
                }
            }
            if (cnt == N / 2) sumStatus(bit); // 팀이 반씩 잘 나뉘었으면
        }

        System.out.println(min);
    }

    private static void sumStatus(int bit) {
        int teamStart = 0; // 스타트팀 능력치 합
        int teamLink = 0; // 링크팀 능력치 합
        ArrayList<Integer> teamStartList = new ArrayList<>(); // 스타트팀 인원
        ArrayList<Integer> teamLinkList = new ArrayList<>(); // 링크팀 인원

        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0) { // 선택 됐으면 스타트팀으로
                teamStartList.add(i);
            } else teamLinkList.add(i); // 아니면 링크팀으로
        }

        for (int i = 0; i < teamStartList.size() - 1; i++) { // 0번 인덱스부터 보면서
            int start = teamStartList.get(i);
            int link = teamLinkList.get(i);
            for (int j = i + 1; j < teamStartList.size(); j++) { // 이후에 나오는 사람과의 능력치 계산
                teamStart += status[start][teamStartList.get(j)]; // 팀 능력치 합에 더해줌
                teamStart += status[teamStartList.get(j)][start];
                teamLink += status[link][teamLinkList.get(j)];
                teamLink += status[teamLinkList.get(j)][link];
            }
        }
        min = Math.min(min, Math.abs(teamStart - teamLink));
    }
}
