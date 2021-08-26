import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj2116 {
    static class Dice {
        int idx, bottom;
        int[] side;

        public Dice(int idx, int[] side) {
            this.idx = idx;
            this.side = side;
            this.bottom = -1;
        }
    }

    static int[] topbot = {0, 6, 4, 5, 2, 3, 1};
    static int N, max;
    static ArrayList<Dice> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        list = new ArrayList<>();
        max = 0;

        for (int i = 0; i < N; i++) {
            int[] arr = new int[7];
            for (int j = 1; j <= 6; j++) {
                arr[j] = sc.nextInt();
            }
            list.add(new Dice(i, arr));
        }

        for (int i = 1; i <= 6; i++) { // 각 side를 bottom으로 했을때
            int firstMaxSide = findMax(0, i);
            getMaxSide(i, 0, firstMaxSide);
        }
        System.out.println(max);
    }

    private static void getMaxSide(int bottomIdx, int cnt, int sum) {
        if (cnt == N - 1) {
            max = Math.max(max, sum);
            return;
        }

        int topNum = list.get(cnt).side[topbot[bottomIdx]]; // top 숫자 구함
        int idx = getNextBottomIdx(cnt + 1, topNum);

        getMaxSide(idx, cnt + 1, sum + findMax(cnt + 1, idx));

    }

    private static int getNextBottomIdx(int cnt, int topNum) {
        for (int i = 1; i <= 6; i++) {
            int num = list.get(cnt).side[i];
            if (num == topNum) {
                return i;
            }
        }
        return -1;
    }

    private static int findMax(int cnt, int idx) { // cnt번째 dice의 top(bottom) index가 idx일 때 top, bottom이 아닌 수들 중 최댓값 찾기
        int maxSideNum = 0;
        int botIdx = topbot[idx];
        for (int i = 1; i <= 6; i++) {
            if (i == idx || i == botIdx) continue;
            maxSideNum = Math.max(maxSideNum, list.get(cnt).side[i]);
        }

        return maxSideNum;
    }
}
