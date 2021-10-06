import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1747 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        getPrimePalindrome(N);
    }

    private static void getPrimePalindrome(int n) { // 에라토스테네스의 체
        boolean[] isPrime = new boolean[1003002]; // 입력값 N이 최댓값인 1000000일 때의 답은 1003001
        Arrays.fill(isPrime, true); // 우선 소수라고 가정 (true)
        for (int i = 2; i < 1003002; i++) { // 2부터 소수인지 확인
            if (isPrime[i]) { // 소수라면
                if (i >= n && isPalindrome(i)) { // 주어진 N보다 크고 펠린드롬일 경우 답 출력하고 끝
                    System.out.println(i);
                    break;
                }
                for (int j = 2, k = i * j; k < 1003002; j++, k = i * j) {
                    isPrime[k] = false; // 소수는 false로 체크
                }
            }
        }
    }

    private static boolean isPalindrome(int n) { // 팰린드롬 
        String str = String.valueOf(n);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
    
}
