class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = changeToSec(play_time); // 동영상 전체 재생시간
        int[] cnt = new int[playTime + 1]; // 시청 중인 시청자 수를 초단위로 센 리스트
        int advTime = changeToSec(adv_time); // 광고 시간
        int[] watch = new int[logs.length]; // 죠르디 동영상 재생한 구간을 초단위로 바꾼 리스트
        for (int i = 0; i < watch.length; i++) {
            String[] tempTime = logs[i].split("-");
            int startTime = changeToSec(tempTime[0]);
            int endTime = changeToSec(tempTime[1]);
            watch[i] = endTime - startTime;
            for (int j = startTime; j < endTime; j++) {
                cnt[j]++; // 시청 중인 시청자 수 세기
            }
        }

        long max = 0;
        for (int i = 0; i < advTime; i++) { // 0초 ~ advTime
            max += cnt[i];
        }
        long sum = max, answerLong = 0;
        int startTime = 0, endTime = advTime;
        while (endTime <= playTime) { // 0 ~ advTime 구간을 시작으로 끝인 playTime까지 반복
            sum = sum - cnt[startTime]; // 매번 1초 이동시켜서 맨앞 1초 cnt 빼고, 맨뒤 1초 cnt 더하기
            sum = sum + cnt[endTime];
            if (sum > max) { // 최댓값보다 커지면 갱신
                max = sum;
                answerLong = startTime + 1; // 답은 가장 빠른 시작 시각
            }
            startTime++;
            endTime++;
        }
        String sec = String.valueOf(answerLong % 60);
        String hour = String.valueOf(answerLong / 3600);
        String min = String.valueOf(answerLong % 3600 / 60);
        if (Integer.parseInt(sec) <= 9) {
            sec = "0" + sec;
        }
        if (Integer.parseInt(hour) <= 9) {
            hour = "0" + hour;
        }
        if (Integer.parseInt(min) <= 9) {
            min = "0" + min;
        }
        answer = hour + ":" + min + ":" + sec;
        // System.out.println(answer);
        return answer;
    }

    private static int changeToSec(String adv_time) { // 초로 바꾸기
        String[] time = adv_time.split(":");
        int totalSec = 0, secs = 1;
        for (int i = 2; i >= 0; i--) {
            totalSec = totalSec + Integer.parseInt(time[i]) * secs;
            secs = secs * 60;
        }
        return totalSec;
    }
}
