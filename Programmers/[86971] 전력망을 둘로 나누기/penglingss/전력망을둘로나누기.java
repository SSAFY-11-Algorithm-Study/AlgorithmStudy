import java.util.*;

class Solution {
    ArrayList<Integer>[] list;
    int count;
    public int solution(int n, int[][] wires) {
        int answer = 100;
        list = new ArrayList[n + 1];

        int cut = 0;
        while(cut < n - 1) {
            for(int i = 1; i < n + 1; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < wires.length; i++) {
                if(cut == i) continue;
                int wire1 = wires[i][0];
                int wire2 = wires[i][1];
                list[wire1].add(wire2);
                list[wire2].add(wire1);
            }

            count = 0;
            dfs(1, 0);
            int res = Math.abs(count - (n - count));
            answer = Math.min(res, answer);
            cut++;
        }
        return answer;
    }

    public void dfs(int start, int before) {
        count++;
        for(int index : list[start]) {
            if(index != before) {
                dfs(index, start);
            }
        }
    }

}
