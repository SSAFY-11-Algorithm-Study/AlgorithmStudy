import java.util.Arrays;
import java.util.Comparator;

//인터넷 참고해서 풀었습니다!

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
		
		String[] tmp = new String[numbers.length];
		
		for(int i=0; i<numbers.length; i++) {
			 tmp[i] = String.valueOf(numbers[i]);//숫자를 숫자 문자열로 바꿔줌
		}
		
		Arrays.sort(tmp, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {//두 문자열을 앞뒤로 합쳐 보고, 내림차순 정렬 
				return (o2+o1).compareTo(o1+o2);
			}
		});
		
		for(int i=0; i<tmp.length; i++) {
			answer += tmp[i];
		}
		//만약 입력값이 모두 0으로 들어온다면 -> 0000... 은 0으로 만들어주기
		if(answer.startsWith("0")) {
			answer="0";
		}
		
		return answer;
    }
}

/*
compareTo나 compare함수에서 비교대상이 문자열이라면,
비교대상 문자열의 각 첫번째 자리부터 하나씩 비교하다가 가장 먼저 만나는 상이한 문자들의 아스키코드 값 차이를 반환하고 끝냄.
*/

