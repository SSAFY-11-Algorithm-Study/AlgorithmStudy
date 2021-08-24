import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class boj2628 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int width = sc.nextInt();
        ArrayList<Integer> cutW = new ArrayList<>();
        int height = sc.nextInt();
        ArrayList<Integer> cutH = new ArrayList<>();
        int cnt = sc.nextInt();

        cutW.add(0);
        cutH.add(0);
        for (int i = 0; i < cnt; i++) {
            int op = sc.nextInt();
            int idx = sc.nextInt();
            if (op == 0 && !cutH.contains(idx)) {
                cutH.add(idx);
            } else if (op == 1 && !cutW.contains(idx)) {
                cutW.add(idx);
            }
        }
        cutW.add(width);
        cutH.add(height);

        Collections.sort(cutW); // 오름차순 정렬
        Collections.sort(cutH);

        int maxW = 0;
        int maxH = 0;
        for (int i = 0; i < cutW.size() - 1; i++) { // 두개씩 보면서
            int dif = cutW.get(i + 1) - cutW.get(i); // 차이를 구해줌
            if(dif > maxH) maxH = dif; // 차이의 최대값 구함 (잘랐을때 최대의 길이)
        }
        for (int i = 0; i < cutH.size() - 1; i++) {
            int dif = cutH.get(i + 1) - cutH.get(i);
            if(dif > maxW) maxW = dif;
        }
        System.out.println(maxH * maxW);
    }
}
