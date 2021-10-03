import java.util.ArrayList;

class PRO_MaxAbsNumber {
    
	static ArrayList<String> list;
	static String[] result;
	static ArrayList<String> op;
	static boolean[] isSelected;
	static long answer = -1;
	    
    public long solution(String expression) {
        list = new ArrayList<>();
        op = new ArrayList<>();
        String str = "";
 
        for (int i = 0; i < expression.length(); i++) {
        	char ch = expression.charAt(i);
        	if(!Character.isDigit(ch)) { //연산 기호라면
        		list.add(str); //여태껏 누적한 숫자를 리스트에 담고
        		str=""; //문자열 초기화
        		
        		if(!op.contains(String.valueOf(ch)))
        			op.add(String.valueOf(ch)); //연산자를 리스트에 담음
        		list.add(String.valueOf(ch));
        	} else {
        		str += expression.charAt(i);
        	}
		} //expression을 String 리스트로(숫자, 연산자 구분해서 저장)
        list.add(str);
        
        result = new String[op.size()];
        isSelected = new boolean[op.size()];
        
        perm(0);
        
        return answer;
    }
    
    public void perm(int cnt) { //연산자의 우선순위에 대한 순열
		
		if(cnt==op.size()) {//result의 인덱스가 작을수록 높은 우선순위
			
			ArrayList<String> copy = new ArrayList<>(); //list의 복사본
			for (String s : list) {
				String tmp = s;
				copy.add(s);
			}
			long output = calculate(result, copy);
			answer = Math.max(answer, Math.abs(output));
			return;
			
		}
		
		for(int i=0; i<op.size(); i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			result[cnt] = op.get(i);
			perm(cnt+1);
			isSelected[i] = false;
		}
		
	}

	public long calculate(String[] result, ArrayList<String> copy) {		//해당 우선순위 순서대로 계산
		long output=0;
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < copy.size(); j++) {
				
				if(copy.get(j).equals(result[i])) { //만약 연산자라면
					long num1 = Long.parseLong(copy.get(j-1));
					long num2 = Long.parseLong(copy.get(j+1));
					
					if(result[i].equals("*")) {
						output = num1 * num2;
					} else if(result[i].equals("+")) {
						output = num1 + num2;
					} else {
						output = num1 - num2;
					}
					
					for (int k = 0; k < 3; k++) { // 연산에 사용된 숫자, 연산자를 지우고
						copy.remove(j-1);
					}
					copy.add(j-1, String.valueOf(output)); //해당 자리에 결과값 집어넣음
					j--; //다시 탐색 시작할 위치 조정
					
					if(copy.size()==1) {
						return Long.parseLong(String.valueOf(copy.get(0)));
					}
				}
			}
		}
		return 0;
	}
  
}
