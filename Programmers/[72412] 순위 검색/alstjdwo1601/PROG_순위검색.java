package week7;

import java.util.Arrays;

public class PROG_순위검색 {

	static String [] info = {"java backend junior pizza 150",
	                         "python frontend senior chicken 210",
	                         "python frontend senior chicken 150",
	                         "cpp backend senior pizza 260",
	                         "java backend junior chicken 80",
	                         "python backend senior chicken 50"};
	
	static String [] query = {"java and backend and junior and pizza 100",
	                          "python and frontend and senior and chicken 200",
	                          "cpp and - and senior and pizza 250",
	                          "- and backend and senior and - 150",
	                          "- and - and - and chicken 100",
	                          "- and - and - and - 150"};
	public int[] solution(String[] info, String[] query) {
		int[] answer = {};

		String [] qArr = new String[5];
		for(int i = 0 ; i < query.length ; i++){
			//쿼리에서 스페이스 기준으로 자르고 and는 빼고 배열에 담음
			if(!query[i].split(" ").equals("and")){
				qArr = query[i].split(" ");
			}

			System.out.println(Arrays.toString(qArr));

		}
		return answer;
	}


}
