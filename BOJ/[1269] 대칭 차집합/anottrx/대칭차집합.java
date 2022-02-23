import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aCnt = Integer.parseInt(st.nextToken());
        int bCnt = Integer.parseInt(st.nextToken());
      
        HashSet<Integer> hs = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aCnt; i++) {
            hs.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bCnt; i++) {
            hs.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(hs.size() * 2 - aCnt - bCnt);
    }
}
