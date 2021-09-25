package time9;

import java.util.ArrayList;

public class PM_NewsClustering {
	
	static String str1 = "FRANCE";
	static String str2 = "french";

	public static void main(String[] args) {
		
		int answer = 0;
		// 대소문자 무시(소문자로 통일)
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		// 두글자씩 담을 리스트
		ArrayList<String> arr1 = new ArrayList<>();
		ArrayList<String> arr2 = new ArrayList<>();
		
		for(int i = 0; i < str1.length()-1; i ++) {
			if(str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z' && str1.charAt(i+1) >= 'a' && str1.charAt(i+1) <= 'z')
			arr1.add(Character.toString(str1.charAt(i))+Character.toString(str1.charAt(i+1)));
		}
		
		for(int i = 0; i < str2.length()-1; i ++) {
			if(str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z' && str2.charAt(i+1) >= 'a' && str2.charAt(i+1) <= 'z')
			arr2.add(Character.toString(str2.charAt(i))+Character.toString(str2.charAt(i+1)));
		}
		
		// 결과 계산을 위해 다시 교집합과 합집합 담을 배열
		ArrayList<String> intersection = new ArrayList<>();
		ArrayList<String> union = new ArrayList<>();
		
		//교집합 합집합 구하기.
		
		for(String str : arr1) {
			if(arr2.remove(str)) { // 제거가 되면 있는 거 이므로 교집합
				intersection.add(str);
			} 
			union.add(str);
		}
		
		for(String str : arr2) { // 교집합 아니어서 삭제 안된 것 더해주기
			union.add(str);
		}
		/*
		System.out.println(intersection.size());
		System.out.println(union.size());
		if(union.size() == 0) System.out.println(65536);
		answer = (intersection.size()/union.size() * 65536);
		System.out.println(answer);
		*/
		double ilen = intersection.size();
		double ulen = union.size();
		if(ulen == 0) answer = 65536;
		else answer = (int) (ilen/ulen*65536);
		//return answer;
		System.out.println(answer);
	}

}
