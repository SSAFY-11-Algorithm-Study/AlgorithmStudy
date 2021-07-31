// 출처: 백준저지 9012번 괄호 https://www.acmicpc.net/problem/9012

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // T
        String[] answer = new String[T]; // 답
        String str; // 괄호 문자열 (2 이상 50 이하)
        int openCnt = 0; // (의 개수
        String result;

        for (int i = 0; i < T; i++) {
            result = "YES"; // 답은 YES로 초기화
            str = br.readLine();
            String[] splited = str.split("(?!^)");
            openCnt = 0;
            checking: for (int j = 0; j < splited.length; j++) {
                if (splited[j].equals("(")) { // (일 경우 숫자 증가시키기
                    openCnt++;
                } else {
                    if (openCnt < 1) { // (가 없다면 )하는 것이 의미 없으므로
                        result = "NO";
                        break checking; // 해당 괄호 문자열 확인하는 것을 중단한다
                    } else { // (가 있을 경우
                        openCnt--; // 1개를 뺀다
                    }
                }
            }
            if (openCnt != 0) { // 정상으로 괄호가 모두 닫히지 않았다면
                result = "NO";
            }
            answer[i] = result; // 답 입력
        }

        for (int i = 0; i < T; i++) {
            bw.write(answer[i] + "\n"); // 답 출력
        }
        bw.flush();
        bw.close();
    }
}
