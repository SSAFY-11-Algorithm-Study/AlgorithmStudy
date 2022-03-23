import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>(); //큐는 다리
        int time = 0;
        int total_w = 0;
        
        for(int truck : truck_weights){
            while(true){ //트럭 하나하나가 q에 올라갈 때까지 반복
                if(q.isEmpty()){ //다리에 트럭이 하나도 없다면
                    q.add(truck);
                    total_w += truck;
                    time++; //올라가는 데 1초 소요
                    break;
                } else if(q.size() == bridge_length) { //다리가 꽉 찼다면 -> 맨 앞 트럭 내림
                    total_w -= q.poll(); //내리는 동시에 새 트럭 또는 0을 올리므로, 이때는 time 증가 X
                } else { //다리에 공간이 있다면
                    if(total_w + truck <= weight){ //다리 올릴 수 있으면
                        q.add(truck);
                        total_w += truck;
                        time++;
                        break;
                    } else { //못 올리면 -> "일단 무게가 0인 트럭을 넣어서 앞에 있는 트럭들이 한 칸씩 움직이게끔 함"
                        q.add(0);
                        time++;
                    }
                }
            }
        } //마지막은 다리에 올라가자마자 끝이 남. 앞에 트럭 몇 개가 있든 어쨌든 마지막까지 다 건너려면 다리 길이만큼 time을 더해줘야 함 (맨 마지막의 경우에는 내려갈 때 1초가 걸리게 계산)
        return time + bridge_length;
    }
}
