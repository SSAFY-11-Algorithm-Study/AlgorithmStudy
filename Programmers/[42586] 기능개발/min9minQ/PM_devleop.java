package time3;

import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        LinkedList<Integer> pro_list = new LinkedList<>();
        LinkedList<Integer> speed_list = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();

        for(int i = 0; i < progresses.length; i ++) {
            pro_list.add(progresses[i]);
            speed_list.add(speeds[i]);
        }
        int cnt = 0;
        while(!pro_list.isEmpty()) {
            while(pro_list.get(0) < 100) {
                for(int i = 0; i < pro_list.size(); i ++) {
                    pro_list.set(i, pro_list.get(i)+speed_list.get(i));
                }
            }
            while(!pro_list.isEmpty() && pro_list.get(0)>=100) {
                pro_list.removeFirst();
                speed_list.removeFirst();
                cnt ++;
            }
            result.add(cnt);
            cnt = 0;
        }        
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i ++){
            answer[i]=result.get(i);
        }

        return answer;
    }
}
/*
import java.util.LinkedList;
import java.util.Queue;


public class PM_devleop {

	public static void main(String[] args) {

		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		LinkedList<Integer> pro_list = new LinkedList<>();
		LinkedList<Integer> speed_list = new LinkedList<>();
		LinkedList<Integer> result = new LinkedList<>();
		
		for(int i = 0; i < progresses.length; i ++) {
			pro_list.add(progresses[i]);
			speed_list.add(speeds[i]);
		}
		int cnt = 0;
		while(!pro_list.isEmpty()) {
			while(pro_list.get(0) < 100) {
				for(int i = 0; i < pro_list.size(); i ++) {
					pro_list.set(i, pro_list.get(i)+speed_list.get(i));
				}
			}
			while(!pro_list.isEmpty() && pro_list.get(0)>=100) {
				pro_list.remove(0);
				speed_list.remove(0);
				cnt ++;
			}
			result.add(cnt);
			cnt = 0;
		}
		System.out.println(result);
	}
}
*/
