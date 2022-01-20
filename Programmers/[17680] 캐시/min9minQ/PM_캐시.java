package time24;

import java.util.LinkedList;
import java.util.Queue;

public class PM_캐시 {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };

		int answer = 0;
		Queue<String> cache = new LinkedList<>();

		if (cacheSize == 0)
			answer = 5 * cities.length;
		else {
			for (int i = 0; i < cities.length; i++) {
				String str = cities[i].toLowerCase();

				if (cache.contains(str)) {
					answer++;
					cache.remove(str);
					cache.offer(str);
				} else {
					if (cache.size() == cacheSize) {
						answer += 5;
						cache.poll();
						cache.offer(str);
					} else {
						answer += 5;
						cache.offer(str);
					}
				}
			}
		}

		System.out.println(answer);

	}

}
