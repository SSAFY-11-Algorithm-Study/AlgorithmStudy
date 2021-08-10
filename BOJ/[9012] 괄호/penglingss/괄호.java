import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        int cnt;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            String str = arr[i];
            for (int j = 0; j < str.length(); j++) {
                if (cnt < 0) {
                    break;
                }

                if (str.charAt(j) == '(') {
                    cnt++;
                } else if (str.charAt(j) == ')') {
                    cnt--;
                }
            } 
            if (cnt == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
