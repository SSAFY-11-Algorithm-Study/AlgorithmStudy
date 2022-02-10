package time27;

public class PM_프렌즈4블록 {

	private static char[][] map;

	public static void main(String[] args) {

		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };

		int answer = 0;

		map = new char[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}

		boolean flag = true;
		while (flag) {
			boolean[][] check = new boolean[m][n];
			for (int i = 0; i < m - 1; i++) {
				for (int j = 0; j < n - 1; j++) {
					check(i, j,check);
				}
			}
			
			int count = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (check[i][j] == true) {
						count++;
					}
				}
			}
			if(count == 0) flag= false; 
			
			//내리기 구현
			
			//
			
			answer += count;
		}

		System.out.println(answer);
	}

	private static void check(int i, int j,boolean[][] check) {
		if (map[i][j] == map[i + 1][j] && map[i][j] == map[i][j + 1] && map[i][j] == map[i + 1][j + 1]) {
			check[i][j] = true;
			check[i + 1][j] = true;
			check[i][j + 1] = true;
			check[i + 1][j + 1] = true;
		}

	}

}
