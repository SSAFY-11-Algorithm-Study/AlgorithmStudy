// 출처: 백준저지 2606번 바이러스 https://www.acmicpc.net/problem/2606

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2606 {
    static boolean[] isVirus; // 바이러스라면 true, 아니면 false
    static int[][] coms; // 컴퓨터 하나당 연결된 컴퓨터 확인용
    /*
     * 1번 컴퓨터 - 2 3 4 5 6 7 (0번째칸은 무시. 1번 컴퓨터의 1번째칸은 나중에 풀 때 의미 없음) 
     * 2번 컴퓨터 - 1 3 4 5 6 7 처음에는 0으로 초기화하고, 연결된 게 있다면 1로 바꾸기
     */
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        C = sc.nextInt(); // 컴퓨터의 수 (100 이하)
        int L = sc.nextInt(); // 연결되어 있는 컴퓨터 쌍의 수

        coms = new int[C + 1][C + 1]; // 1번 컴퓨터부터 시작하니까 1번부터 C까지
        Arrays.stream(coms).forEach(a -> Arrays.fill(a, 0)); // 처음에는 0으로 채우기

        for (int i = 0; i < L; i++) { // 컴퓨터의 번호 쌍 입력 받기
            int c = sc.nextInt(); // 컴퓨터 번호
            int l = sc.nextInt(); // 해당 컴퓨터와 연결된 컴퓨터 번호
            coms[c][l] = 1;
        }

        isVirus = new boolean[C + 1];
        Arrays.fill(isVirus, false); // 우선 모든 컴퓨터가 바이러스에 감염되지 않았다고 저장

        for (int i = 1; i <= C; i++) { // 입력이 (1,2)처럼 1번 컴퓨터가 앞에 존재한 경우
            if (coms[1][i] != 0) { // 1번 컴퓨터와 연결된 컴퓨터 중 감염된 컴퓨터 확인
                isVirus[1] = true; // 1번 컴퓨터는 바이러스에 감염되었다고 표시
                isVirus[i] = true; // 1번 컴퓨터와 연결된 컴퓨터도 감염되었다고 표시
                find(i);
            }
        }
        if (isVirus[1] == false) { // 만약 입력이 (2,1)처럼 1번 컴퓨터가 뒤에만 있는 경우
            for (int i = 1; i <= C; i++) {
                if (coms[i][1] != 0) {
                    isVirus[1] = true;
                    isVirus[i] = true;
                    find(i);
                }
            }
        }

        int total = 0; // 답은 0으로 초기화
        for (int i = 1; i <= C; i++) {
            if (isVirus[i] == true) { // 바이러스 걸린 컴퓨터 개수 세기
                total++;
            }
        }
        System.out.println(total - 1);
    }

    private static void find(int cc) {
        for (int i = 1; i <= C; i++) {
            // 입력받은 cc번 컴퓨터와 연결된 컴퓨터가 있는지 확인. (cc번, i번)인 경우
            if (isVirus[i] == true) { // i번 컴퓨터가 이미 바이러스에 걸렸다면 무시
                continue;
            }
            if (coms[cc][i] != 0) {
                if (isVirus[i] == true) {
                    continue;
                }
                isVirus[i] = true;
                find(i);
            }
        }
        // 입력받은 cc번 컴퓨터와 연결된 컴퓨터가 있는지 확인. (i번, cc번)인 경우
        for (int i = 1; i <= C; i++) {
            if (isVirus[i] == true) {
                continue;
            }
            if (coms[i][cc] != 0) {
                if (isVirus[i] == true) {
                    continue;
                }
                isVirus[i] = true;
                find(i);
            }
        }
        return;
    }
}
