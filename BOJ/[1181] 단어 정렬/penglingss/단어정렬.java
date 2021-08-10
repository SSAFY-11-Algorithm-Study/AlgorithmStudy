import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        // 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.length() == o2.length()) { // 길이가 같다면
                return o1.compareTo(o2); // 사전순
            } else { // 길이가 같지않다면
                return o1.length() - o2.length(); // 길이 오름차순
            } 
        });

        System.out.println(arr[0]);
        for (int i = 1; i < n; i++) {
            if (!arr[i].equals(arr[i - 1])) { // 중복 없이 출력
                System.out.println(arr[i]);
            }
        }
    }
}
