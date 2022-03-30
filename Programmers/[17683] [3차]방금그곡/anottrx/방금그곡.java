import java.util.HashMap;

class Solution {
  public String solution(String m, String[] musicinfos) {
        String answer = "";
        HashMap<String[], Integer> hm = new HashMap<>(); // [[제목, 입력된 순서], 재생된 시간]

        for (int i = 0; i < musicinfos.length; i++) {
            String[] strList = musicinfos[i].split(",");
            String[] startTime = strList[0].split(":");
            String[] endTime = strList[1].split((":"));
            String musicTitle = strList[2];
            String music = strList[3];

            int time = getTimeLast(startTime, endTime);
            int musicCnt = 0;
            for (int j = 0; j < music.length(); j++) {
                if (music.charAt(j) == '#') {
                    musicCnt++;
                }
            }
            musicCnt = music.length() - musicCnt;
            String result = getMusic(music, musicCnt, time);

            if (haveMusic(result, m)) {
                hm.put(new String[]{musicTitle, String.valueOf(i)}, time);
            }
        }

        if (hm.size() == 0) {
            answer = "(None)";
        } else {
            int maxTime = 0, idx = 0;
            for (String[] key : hm.keySet()) {
                int tempIdx = Integer.parseInt(key[1]);
                int lastTime = hm.get(key);
                if (lastTime > maxTime) { // 재생된 시간이 제일 긴 음악 제목을 반환,
                    maxTime = lastTime;
                    idx = tempIdx;
                    answer = key[0];
                } else if (lastTime == maxTime) { // 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환
                    if (tempIdx < idx) {
                        idx = tempIdx;
                        answer = key[0];
                    }
                }
            }
        }
        return answer;
    }
  
    private static int getTimeLast(String[] startTime, String[] endTime) {
        int hour = 0, min = 0;
        int startHour = Integer.parseInt(startTime[0]), startMin = Integer.parseInt(startTime[1]);
        int endHour = Integer.parseInt(endTime[0]), endMin = Integer.parseInt(endTime[1]);
        if (endMin >= startMin) {
            hour = endHour - startHour;
            min = endMin - startMin;
        } else {
            hour = endHour - startHour - 1;
            min = 60 + endMin - startMin;
        }
        return hour * 60 + min;
    }

    private static String getMusic(String music, int musicCnt, int time) {
        String result = music.repeat(time / musicCnt);
        int rest = time % musicCnt; // time - time / musicCnt; 아님
        int cntLength = 0, position = 0;
        for (int i = 0; i < music.length(); i++) {
            if (cntLength == rest) {
                position = i;
                break;
            }
            if (music.charAt(i) != '#') {
                cntLength++;
            }
        }
        result = result + music.substring(0, position);
        return result;
    }

    private static boolean haveMusic(String result, String m) {
        int j = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == m.charAt(j)) { // 둘 중 하나 다음이 #이면 문제가 됨
                if (i < result.length() - 1 && result.charAt(i + 1) == '#') {
                    if (j == m.length() - 1 || (j < m.length() - 2 && m.charAt(j + 1) != '#')) {
                        j = 0;
                        continue;
                    }
                } else if (j < m.length() - 2 && m.charAt(j + 1) == '#') {
                    if (i == result.length() - 1 || (i < result.length() - 2 && result.charAt(i + 1) != '#')) {
                        j = 0;
                        continue;
                    }
                }
                j++;
            } else {
                j = 0;
                if (result.charAt(i) == m.charAt(j)) { // 한번 더 확인
                    j++;
                }
            }
            if (j == m.length()) {
                return true;
            }
        }
        return false;
    }
}
