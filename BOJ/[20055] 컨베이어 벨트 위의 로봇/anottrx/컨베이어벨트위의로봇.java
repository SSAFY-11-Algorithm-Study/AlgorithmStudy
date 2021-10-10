import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    static int N, K, map[], copyMap[];
    static boolean[] robot, copyRobot; // 로봇이 해당 자리에 있는지 확인

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[2 * N];
        robot = new boolean[N];
        copyMap = new int[2 * N];
        copyRobot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 1; // 가장 처음 수행되는 단계는 1번째 단계이다
        while (true) {
            rotateConveyorRobot(); // 컨베이어 벨트와 벨트 위 로봇이 함꼐 이동
            K = K - moveRobotCountZero(); // 로봇이 오른쪽으로 이동 (해당 위치가 0이라면 그만큼 K에서 뺴기)
            K = K - putRobotCountZero(); // 로봇을 올림 (해당 위치가 0이라면 그만큼 K에서 뺴기)

            if (K <= 0) {
                break;
            }

            cnt++; // cnt 증가
        }
        System.out.println(cnt);
    }

    private static void rotateConveyorRobot() { // 컨베이어 벨트 내구도 이동
        for (int i = 0; i < 2 * N - 1; i++) {
            copyMap[i + 1] = map[i];
            if (i < N - 1) { // 로봇의 개수는 컨베이어 벨트 개수와 다름
                copyRobot[i + 1] = robot[i];
            }
        }
        copyMap[0] = map[2 * N - 1];
        copyRobot[N - 1] = false;
        for (int i = 0; i < 2 * N; i++) {
            map[i] = copyMap[i];
            if (i < N) {
                robot[i] = copyRobot[i];
            }
        }
    }

    private static int moveRobotCountZero() { // 로봇이 오른쪽으로 이동
        int cnt = 0;
        robot[N - 1] = false; // 로봇이 내리는 위치
        for (int i = N - 2; i > 0; i--) { // N-1은 비었으니 N-2부터 오른쪽으로 이동 시작
            if (robot[i] && !robot[i + 1] && map[i + 1] > 0) {
                // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
                robot[i] = false;
                robot[i + 1] = true;
                map[i + 1]--;
                if (map[i + 1] == 0) { // 내구도 1 줄여서 0이 되었다면 cnt 증가
                    cnt++;
                }
            }
        }
        robot[N - 1] = false; // 로봇이 내리는 위치
        return cnt;
    }

    private static int putRobotCountZero() { // 올리는 위치에 로봇 올리기
        int cnt = 0;
        if (!robot[0] && map[0] > 0) {
            robot[0] = true;
            map[0]--;
            if (map[0] == 0) { // 내구도 1 줄여서 0이 되었다면 cnt 증가
                cnt++;
            }
        }
        return cnt;
    }
}
