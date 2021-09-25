// 다인언니가 준 추가 테케 중 "ABABAAAAAB" 는 통과 못함
// 근데 애초에 왜 "ABABAAAAAB"의 output이 8이어야 하는지 모르겠음.. 10아닌가?ㅠㅠ

public class Solution {
    
	public int solution(String name) {

		char[] arr = new char[name.length()];
		
		for(int i=0; i<name.length(); i++) {
			arr[i] = name.charAt(i);
		}
		
		int answer = Straight(arr); //왼쪽->오른쪽으로 진행
		
		for(int i=0; i<arr.length; i++) {
			//i번째 글자에서 처음으로 되돌아 가, 맨 끝 글자로 이동 후 오른쪽 -> 왼쪽 진행
			int tmp = Reverse(arr, i);
			answer = Math.min(answer, tmp);
		}
		
		return answer;
	}

	public int Reverse(char[] arr, int idx) {
		
		int cnt=0;
		
		//i번째 문자까지 바꾼 후
		for(int i=0; i<=idx; i++) {
			
			if(arr[i]=='A') { 
				cnt++; continue;
			}
			
			if(arr[i] < 'N') {
				cnt+= arr[i]-'A';
			} else {
				cnt += ('Z' - arr[i] + 1);
			}
			cnt++;
		}
		
		//되돌아가서 맨 끝으로 커서 옮겨놓기
		cnt += idx;
		
		// 오 -> 왼 이동
		for(int i=arr.length-1; i>idx; i--) {
			
			if(arr[i]=='A') {
				
				if(allA(arr, idx+1, i)) //인덱스 idx+1부터 i까지가 다 A로 이루어져 있다면 더 이상 조작 X
					break;
				
				if(i==idx+1) //다 완성되어서 커서 움직일 필요 없으므로, cnt더하지 않고 바로 끝냄.
					break;
				
				cnt++; continue;
			}
			
			if(arr[i] < 'N') {
				cnt+= arr[i]-'A';
			} else {
				cnt += ('Z' - arr[i] + 1);
			}
			cnt++;
		}
		return --cnt;
	}

	public int Straight(char[] arr) {
		
		int cnt=0;
		
		for(int i=0; i<arr.length; i++) {
			
			if(arr[i]=='A') { //바꿀 필요 없으므로 커서만 이동 후 스킵
				
				if(allA(arr, i, arr.length-1)) { //i부터 끝가지가 다 A라면 더 이상 조작 X
					break;
				}
				
				cnt++; continue;
			}
			
			if(arr[i] < 'N') {
				cnt+= arr[i]-'A';
			} else {
				cnt += ('Z' - arr[i] + 1);
			}
			cnt++;
		}
		return --cnt;
	}

	public boolean allA(char[] arr, int start, int end) {
		
		for(int i=start; i<=end; i++) {
			if(arr[i]!='A')
				return false;
		}
		return true;
	}

}
