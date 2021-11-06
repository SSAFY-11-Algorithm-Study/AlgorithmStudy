package time15;

public class PM_단어변환 {
	
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		int answer = 0;
		String[] words = {"hot","dot","dog","lot","log","cog"};
		String begin = "hit";
		String target = "cog";
		boolean[] visited = new boolean[words.length];
		
		dfs(0,begin, target, words,visited);
		
		if(result == Integer.MAX_VALUE) {
			answer = 0;
		}
		answer = result;
		System.out.println(answer);
		//return answer;
		
	}

	private static void dfs(int cnt, String begin, String target, String[] words, boolean[] visited) {
		  
		if(begin.equals(target)) {
			result = Math.min(result, cnt);
			return;
		}
		
		for(int i=0;i<words.length;i++){
			if(!visited[i] && check(begin,words[i])){
				visited[i]=true;
				dfs(cnt+1,words[i],target,words,visited);
				visited[i]=false;
			}
		}
	}

	private static boolean check(String begin, String target) {
		
		int num = 0;
		for(int i = 0; i < begin.length(); i ++) {
			if( begin.charAt(i) != target.charAt(i)) {
				num++;
			}
		}
		
		if(num == 1)
			return true;
		return false;
	}

}
