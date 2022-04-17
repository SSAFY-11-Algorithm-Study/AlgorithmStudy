/*
 * 문제 풀이 순서
 1. 나무 정보를 ArrayList로 받고 나이 오름차순으로 정렬한 뒤, 그대로 Queue에 넣기
 2. 순차적으로 봄, 여름, 가을, 겨울 진행하기

 * 시간 오래 걸린 이유
 1. 처음에는 위치마다 매순간 정렬이 필요하다고 판단하여 [위치, 위치에 있는 나무들 PriorityQueue]로 해시맵을 만들었지만,
  너무 많은 복붙이 진행되어서 그런지 시간초과가 발생했다. 알고보니 맨 처음에만 정렬을 하면 되는 일이었다
 2. 위치의 영양분보나 나이가 많으면 무조건 멈추고 큐 도는 것을 끝냈는데 모든 큐 나무를 확인해야 했다
 3. for문 안에 for문 복붙하다가 j가 아닌 i라고 그대로 입력해서 찾는데 시간이 아주 오래 걸렸다
 4. 처음 위치가 모두 5부터 시작하는 것도 놓쳤다
 *
 * 백준 채점 시 런타임 에러 (IllegalArgument) 발생했던 이유
  : Comparator를 사용했지만 0을 반환하지 않고 -1과 1만 반환했기 때문
  (출처 : https://www.acmicpc.net/board/view/10721, https://help.acmicpc.net/judge/rte/IllegalArgumentException)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B나무재테크 {
    static int N, K, map[][], addMap[][];
    static Queue<Tree> q;
    static int[][] d = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵 길이
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // K년이 지난 후 살아남은 나무의 수가 답

        map = new int[N][N];
        addMap = new int[N][N]; // 자양분
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = 5; // 가장 처음에 양분은 모든 칸에 5만큼 들어있다
                addMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Tree> treeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // 나무의 위치
            int y = Integer.parseInt(st.nextToken()) - 1; // 나무의 위치
            int z = Integer.parseInt(st.nextToken()); // 나무의 나이
            treeList.add(new Tree(x, y, z));
        }
        Collections.sort(treeList, new Comparator<Tree>() { // 나무 나이 오름차순으로 정렬
            @Override
            public int compare(Tree o1, Tree o2) {
                if (o1.age < o2.age) {
                    return -1;
                }
                return 0; // -1일 경우 백준 채점 시 런타임 에러 (IllegalArgument) 발생
            }
        });
        q = new LinkedList<>(treeList);

        for (int i = 0; i < K; i++) {
            SpringSummer();
            Fall();
            Winter();
        }

        int answer = q.size();
        System.out.println(answer);
    }


    private static void SpringSummer() {
        // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
        Queue<Tree> deadTreeQueue = new LinkedList<>();
        Queue<Tree> newTreeQueue = new LinkedList<>();
        int size = q.size();
        for (int i = 0; i < size; i++) {  // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
            Tree cur = q.poll(); // 하나씩 나무 빼기
            if (cur.age > map[cur.x][cur.y]) { // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다
                deadTreeQueue.add(new Tree(cur.x, cur.y, cur.age));
            } else {
                newTreeQueue.add(new Tree(cur.x, cur.y, cur.age + 1));
                map[cur.x][cur.y] -= cur.age;
            }
        }

        // 여름에는 봄에 죽은 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
        size = deadTreeQueue.size();
        for (int i = 0; i < size; i++) {
            Tree cur = deadTreeQueue.poll(); // 하나씩 나무 빼기
            map[cur.x][cur.y] += cur.age / 2;
        }
        q = newTreeQueue;
    }

    private static void Fall() {
        Queue<Tree> newTreeQueue = new LinkedList<>();
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Tree cur = q.poll(); // 하나씩 나무 빼기
            if (cur.age % 5 == 0) {
                // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다
                for (int j = 0; j < 8; j++) {
                    int dx = cur.x + d[j][0];
                    int dy = cur.y + d[j][1];
                    if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                        newTreeQueue.add(new Tree(dx, dy, 1));
                    }
                }
            }
            q.add(cur); // 다시 넣기
        }
        for (int i = 0; i < size; i++) {
            Tree cur = q.poll(); // 하나씩 나무 빼기
            newTreeQueue.add(cur); // 다시 넣기
        }
        q = newTreeQueue;
    }

    private static void Winter() {
        // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += addMap[i][j];
            }
        }
    }

    static class Tree {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
