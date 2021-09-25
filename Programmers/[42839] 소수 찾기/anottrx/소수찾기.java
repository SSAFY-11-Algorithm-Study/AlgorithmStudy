import java.util.HashSet;

class Solution {
    static char[] selected;
    static boolean[] isSelected;
    static HashSet<Integer> primes; // 중복 제거 위함
    
    public int solution(String numbers) {
        int answer = 0;
        primes = new HashSet<>(); 
        for (int i = 1; i <= numbers.length(); i++) {
            selected = new char[i];
            isSelected = new boolean[numbers.length()];
            makeNum(0, i, numbers); // 순열
        }
        answer = primes.size(); // 해시셋 개수
        return answer;
    }
    
    private static void makeNum(int cnt, int n, String numbers) { // 순열
        if (cnt == n) {
            String tempStr = "";
            for (int i = 0; i < n; i++) {
                tempStr = tempStr + selected[i];
            }
            int tempNum = Integer.parseInt(tempStr); // 0으로 시작하는 경우를 제거하기 위함
            if (checkIfPrime(tempNum)) { // 소수인지 확인하기
                primes.add(tempNum); // 소수라면 해시셋에 넣기
            }
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                selected[cnt] = numbers.charAt(i);
                makeNum(cnt + 1, n, numbers);
                isSelected[i] = false;
            }
        }
    }
    
    private static boolean checkIfPrime(int num) { // 소수인지 확인
        if (num == 0 || num == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
