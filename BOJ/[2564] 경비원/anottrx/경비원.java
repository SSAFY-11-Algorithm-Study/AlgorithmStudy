// 출처: 백준저지 2564번 경비원 https://www.acmicpc.net/problem/2564

import java.util.Scanner;

public class BOJ2564 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0; // 답
        int W = sc.nextInt(); // 가로
        int H = sc.nextInt(); // 세로
        int N = sc.nextInt(); // 상점 개수 (100 이하)
        int[][] stores = new int[N][2]; // 상점의 위치
        for (int i = 0; i < N; i++) {
            stores[i][0] = sc.nextInt(); // 북남서동(1,2,3,4)
            stores[i][1] = sc.nextInt(); // 위치
        }
        int pos = sc.nextInt(); // 동근이 위치 (북남서동)
        int posLen = sc.nextInt();

        for (int i = 0; i < N; i++) {
            if (stores[i][0] == pos) { // 1. 북남서동이 같다면
                answer = answer + Math.abs(posLen - stores[i][1]); // 거리 차이만 구하기
            } else if ((stores[i][0] == 1 || stores[i][0] == 2) && (pos == 1 || pos == 2)) {
                // 2-1. 둘다 북남 중 하나라면, 세로길이는 무조건 더하고, 더 작은 가로 차이를 더하기
                answer = answer + H + Math.min((stores[i][1] + posLen), (2 * W - stores[i][1] - posLen));
            } else if ((stores[i][0] == 3 || stores[i][0] == 4) && (pos == 3 || pos == 4)) {
                // 2-2. 둘다 서동 중 하나라면, 가로길이는 무조건 더하고, 더 작은 세로 차이를 더하기
                answer = answer + W + Math.min((stores[i][1] + posLen), (2 * H - stores[i][1] - posLen));
            } else if ((stores[i][0] == 1 && pos == 3) || (pos == 1 && stores[i][0] == 3)) {
                // 3-1. 북쪽과 서쪽이라면, 각 꼭짓점으로부터의 거리만 더하기
                answer = answer + stores[i][1] + posLen;
            } else if ((stores[i][0] == 2 && pos == 4) || (pos == 2 && stores[i][0] == 4)) {
                // 3-2. 남쪽과 동쪽이라면, 각 반대편으로부터의 거리 차이를 더하기
                answer = answer + H + W - stores[i][1] - posLen;
            } else if ((stores[i][0] == 1 && pos == 4)) {
                // 3-3-1. 북쪽(상점)과 동쪽(동근)이라면, 동쪽은 꼭짓점으로부터의 거리를, 북쪽은 반대편 거리 차이를 더하기
                answer = answer + (W - stores[i][1]) + posLen;
            } else if (pos == 1 && stores[i][0] == 4) {
                // 3-3-2. 북쪽(동근)과 동쪽(상점)이라면, 동쪽은 꼭짓점으로부터의 거리를, 북쪽은 반대편 거리 차이를 더하기
                answer = answer + (W - posLen) + stores[i][1];
            } else if ((stores[i][0] == 2 && pos == 3)) {
                // 3-4-1. 남쪽(상점)과 서쪽(동근)이라면, 남쪽은 꼭짓점으로부터의 거리를, 서쪽은 반대편 거리 차이를 더하기
                answer = answer + stores[i][1] + (H - posLen);
            } else if (pos == 2 && stores[i][0] == 3) {
                // 3-4-2. 남쪽(동근)과 서쪽(상점)이라면, 남쪽은 꼭짓점으로부터의 거리를, 서쪽은 반대편 거리 차이를 더하기
                answer = answer + (H - stores[i][1]) + posLen;
            }
        }

        System.out.println(answer); // 답 출력
    }
}
