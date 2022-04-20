/*
 * 처음에는 정렬 후, 가장 작은 수의 약수 배열로 하나씩 비교해서 최소공배수를 구했다. 그랬더니 [2, 7, 14]가 완전히 다른 수가 나왔다.
 * 찾아보니, 정렬 후 맨 뒤의 두 수의 최소공배수를 구해 다시 맨 뒤에 넣고 반복하는 것이 답이란 걸 알게 되었다
 * 최소공배수 참고 : https://st-lab.tistory.com/193, https://programmer-chocho.tistory.com/9
 */

import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;

        Arrays.sort(arr); // 정렬
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) { // 리스트에 담기
            numList.add(arr[i]);
        }

        while (true) {
            int num1 = numList.get(numList.size() - 2);
            int num2 = numList.get(numList.size() - 1);
            int tempResult = getTemp(num1, num2); // 최소공배수 구하기
            numList.remove(numList.size() - 1);
            numList.set(numList.size() - 1, tempResult); // 최소공배수 구하는데 사용한 두 수는 제거하고 방금 구한 최소공배수를 다시 넣기

            if (numList.size() == 1) { // 리스트에 1개 남으면 그게 답
                answer = numList.get(0);
                break;
            }
        }

        // System.out.println(answer);
        return answer;
    }

    private static int getTemp(int num1, int num2) { // 최소공배수 구하기
        int divide = getDivideNumber(num1, num2); // 최대공약수 구하기
        return num1 * num2 / divide;
    }

    public static int getDivideNumber(int num1, int num2) { // 최대공약수 구하기
        // 방법 1
        while (num1 != 0) {
            int temp = num2 % num1;
            num2 = num1;
            num1 = temp;
        }
        return num2;

        // 방법 2
//        if (num2 % num1 == 0) {
//            return num1;
//        }
//        return getDivideNumber(num1, num2 % num1);
    }
}
