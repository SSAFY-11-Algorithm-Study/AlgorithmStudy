import java.util.ArrayList;
import java.util.Collections;

//"중복순열 - 정렬 - 같은 것 찾아나가기"

class PRO_Dictionary {
    static String[] dict = {"A", "E", "I", "O", "U"};
	static ArrayList<String> ans = new ArrayList<>();
	static String result;
    
    public int solution(String word) {
        //A ~ UUUUU 가능한 모든 경우의 수 구해서 리스트에 저장
		for(int i=1; i<=5; i++) {
			result = "";
			perm(0, result, i);
		}
		
		Collections.sort(ans); //사전순 정렬
		
		int idx=1;
		int answer = 0;
		//word와 같은 것을 몇 번째인지 계산하며 찾아나가기
		for(int i=0; i<ans.size(); i++) {
			if(ans.get(i).equals(word)) {
				answer=idx;
				break;
			}
			idx++;
		}
		
        return answer;
    }
    
    //중복순열
    public void perm(int cnt, String result, int R) {
		
		if(cnt==R) {
			ans.add(result);
			return;
		}
		
		for(int i=0; i<5; i++) {
			String tmp = dict[i];
			perm(cnt+1, result + tmp, R);
		}
	}
}
