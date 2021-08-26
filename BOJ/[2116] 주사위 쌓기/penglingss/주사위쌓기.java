import java.util.ArrayList;
import java.util.Scanner;

public class boj2116 {
    static class Dice {
        int idx;
        int[] side;

        public Dice(int idx, int[] side) {
            this.idx = idx;
            this.side = side;
        }
    }

    static int[] topbot = {0, 6, 4, 5, 2, 3, 1}; // index로 접근했을 때, 반대편 index를 구함 ex) topbot[1] = 6 : 1번째 index의 반대편 index는 6
                                                 // 입력이 A(1), B(2), C(3), D(4), E(5), F(6) 로 주어지기때문에 쌍을 맞춰주기 위함
                                                 // 1-6, 2-4, 3-5 가 위-아래 쌍
    static int N, max;
    static ArrayList<Dice> diceList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        diceList = new ArrayList<>();
        max = 0;

        for (int i = 0; i < N; i++) {
            int[] arr = new int[7];
            for (int j = 1; j <= 6; j++) {
                arr[j] = sc.nextInt();
            }
            diceList.add(new Dice(i, arr));
        }

        for (int i = 1; i <= 6; i++) { // 0번 Dice의 각 side를 bottom으로 했을때
            int firstMaxSide = findMax(0, i); // 위-아래 side 제외 최댓값 찾기
            getMaxSide(i, 0, firstMaxSide); // i번째 side를 bottom index로 두고 시작
        }
        
        System.out.println(max);
    }

    private static void getMaxSide(int bottomIdx, int cnt, int sum) {
        if (cnt == N - 1) {
            max = Math.max(max, sum);
            return;
        }

        int topNum = diceList.get(cnt).side[topbot[bottomIdx]]; // 현재 Dice의 top 숫자 구함
        int idx = getNextBottomIdx(cnt + 1, topNum); // 다음 Dice의 bottom index 구함

        getMaxSide(idx, cnt + 1, sum + findMax(cnt + 1, idx)); // side 중 최댓값 더해주며 다음 Dice로
    }

    private static int getNextBottomIdx(int cnt, int topNum) { // top number와 같은 값을 가지는 Dice side의 index 반환
        for (int i = 1; i <= 6; i++) {
            int num = diceList.get(cnt).side[i];
            if (num == topNum) return i;
        }
        return -1; // 의미 없는 return
    }

    private static int findMax(int cnt, int idx) { // top(또는 bottom) index가 idx일 때 top, bottom이 아닌 수들 중 최댓값 찾기
        int maxSideNum = 0;
        int botIdx = topbot[idx];
        for (int i = 1; i <= 6; i++) {
            if (i == idx || i == botIdx) continue;
            maxSideNum = Math.max(maxSideNum, diceList.get(cnt).side[i]);
        }
        return maxSideNum;
    }
}
