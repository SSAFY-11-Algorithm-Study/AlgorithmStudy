// 출처: 백준저지 1181번 단어 정렬 https://www.acmicpc.net/problem/1181

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashSet<String> removeHS = new HashSet<>(); // 주어진 문자열 중복제거용

        int N = Integer.parseInt(br.readLine()); // 단어의 개수 N (1 ≤ N ≤ 20,000)
        String[][] wordArr = new String[51][1]; // 단어길이에 따라 해당 배열에 단어 저장
        for (int i = 0; i < 51; i++) {
            wordArr[i][0] = ""; // 우선 ""로 초기화
        }

        for (int i = 0; i < N; i++) { // 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다
            removeHS.add(br.readLine()); // HashSet에 입력받아 중복을 제거
        }
        ArrayList<String> wordsCheck = new ArrayList<>(removeHS); // 중복제거한 뒤 ArrayList에 저장

        int len; // 단어의 길이
        for (int i = 0; i < wordsCheck.size(); i++) { // 중복제거한 후 남음 단어 개수만큼 반복
            len = wordsCheck.get(i).length(); // 해당 단어의 길이
            if (wordArr[len][0].equals("")) { // 처음이라면 그냥 저장
                wordArr[len][0] = wordsCheck.get(i);
            } else { // 같은 길이를 가진 단어가 또 있다면 " "로 구분한 후 저장
                wordArr[len][0] = wordArr[len][0] + " " + wordsCheck.get(i);
            }
        }

        for (int i = 0; i < 51; i++) { // 문자열의 길이는 최대 50이므로 하나하나 확인
            if (wordArr[i][0] != "") { // 해당 길이만큼의 단어가 있다면
                wordsCheck.clear(); // 초기화
                String[] splited = wordArr[i][0].split(" "); // " "로 분리하기
                for (int j = 0; j < splited.length; j++) {
                    wordsCheck.add(splited[j]); // 단어들을 분리한 후
                }
                Collections.sort(wordsCheck); // 알파벳순으로 정렬
                for (int j = 0; j < wordsCheck.size(); j++) {
                    bw.write(wordsCheck.get(j) + "\n"); // 그 결과를 출력
                }
            }
        }
        bw.flush();
        bw.close();
    }

}
