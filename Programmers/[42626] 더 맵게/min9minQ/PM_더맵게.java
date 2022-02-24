package time28;

import java.util.PriorityQueue;

public class PM_더맵게 {

	public static void main(String[] args) {

		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;

		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue();

		for (int s : scoville) {
			pq.offer(s);
		}

		while (pq.peek() <= K) {
			if (pq.size() == 1) {
				answer = -1;
				break;
			}
			int a = pq.poll();
			int b = pq.poll();

			int newsco = a + (b * 2);

			pq.offer(newsco);
			answer++;
		}

		// return answer;
		System.out.println(answer);

	}

}
