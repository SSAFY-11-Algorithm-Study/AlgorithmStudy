import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    static String expression = "100-200*300-500+20";
    static Deque<String> expr; // expression 문자열을 숫자와 연산자 단위로 쪼개서 저장
    static ArrayList<Character> oper; // 연산자 저장
    static boolean[] isSelected; // 순열
    static char[] selectedOper; // 순열 결과
    static long max;

    public long solution(String expression) {
        long answer = 0;

        expr = new LinkedList<>(); // 50, *, 6, -, 3, *, 2
        HashSet<Character> operSet = new HashSet<>(); // 연산자를 중복 없이 저장하기 위함
        StringBuilder sb = new StringBuilder(); // 숫자 저장하기 위함
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') { // 숫자라면 sb에 저장
                sb.append(expression.charAt(i));
            } else { // 연산자라면
                expr.addLast(sb.toString()); // 여태까지 저장한 sb를 덱에 넣기
                sb.setLength(0); // sb 초기화
                expr.addLast(String.valueOf(expression.charAt(i))); // 연산자도 덱에 넣기
                operSet.add(expression.charAt(i)); // 연산자를 리스트에 넣기
            }
        }
        expr.addLast(sb.toString()); // 마지막 숫자도 덱에 넣기

        oper = new ArrayList<>(operSet);
        isSelected = new boolean[oper.size()];
        selectedOper = new char[oper.size()];

        max = Integer.MIN_VALUE;
        setOperOrder(0, oper.size()); // 순열 -> 계산
        answer = max;
        return answer;
    }

    private static void setOperOrder(int cnt, int total) { // 연산자를 가지고 순열
        if (cnt == total) {
            max = Math.max(max, Math.abs(getResult())); // 계산
            return;
        }

        for (int i = 0; i < total; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                selectedOper[cnt] = oper.get(i);
                setOperOrder(cnt + 1, total);
                isSelected[i] = false;
            }
        }
    }

    private static long getResult() {
        Deque<String> copyExpr = new LinkedList<>(expr); // expr는 순열 결과만큼 사용할 것이기 때문에 복사
        for (int i = 0; i < selectedOper.length; i++) { // 연산자 순서에 따라서 연산하기
            getTempWithOper(copyExpr, i);
        }
        return Long.parseLong(copyExpr.poll());
    }

    private static void getTempWithOper(Deque<String> copyExpr, int n) {
        Deque<String> copyDeque = new LinkedList<>(copyExpr); // // 50,*,6,-,3,*,2 -> 300,-,6
        copyExpr.clear();
        while (true) {
            String a = copyDeque.pollFirst(); // 첫번째 숫자 빼기
            if (copyDeque.isEmpty()) { // 다 뺐다면 뺐던 숫자 넣고 끝내기
                copyExpr.offerLast(a);
                break;
            }
            String op = copyDeque.pollFirst(); // 연산자 빼기
            if (op.equals(String.valueOf(selectedOper[n]))) { // 뺸 연산자가 우선순위 순서에 맞다면
                String b = copyDeque.pollFirst(); // 두번째 숫자도 빼기
                long c = 0;
                if (selectedOper[n] == '+') { // 해당 연산자에 맞게 계산
                    c = Long.parseLong(a) + Long.parseLong(b);
                } else if (selectedOper[n] == '-') {
                    c = Long.parseLong(a) - Long.parseLong(b);
                } else if (selectedOper[n] == '*') {
                    c = Long.parseLong(a) * Long.parseLong(b);
                }
                copyDeque.offerFirst(String.valueOf(c)); // 결과를 다시 덱에 넣어서 계산이 이어지도록 하기
            } else { // 연산자가 다르다면 숫자와 연산자를 덱에 넣기
                copyExpr.offerLast(a);
                copyExpr.offerLast(op);
            }
        }
    }
}
