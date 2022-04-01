import java.util.HashMap;
import java.util.Stack;

class Solution {
    static HashMap<Character, Character> hmSkill;

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        hmSkill = new HashMap<>();
        for (int i = 1; i < skill.length(); i++) {
            hmSkill.put(skill.charAt(i), skill.charAt(i - 1)); // {이후, 이전}
        }

        for (int i = 0; i < skill_trees.length; i++) {
            answer = canUseSkill(skill, skill_trees[i]) ? answer + 1 : answer;
        }

        return answer;
    }

    public boolean canUseSkill(String skill, String skillTrees) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < skillTrees.length(); i++) {
            if (skill.contains(String.valueOf(skillTrees.charAt(i)))) { // 해시셋 쓸 필요 없이 contains로 가능
                if (skillTrees.charAt(i) == skill.charAt(0)) { // 스킬트리 첫번째라면 무조건 스택에 넣기
                    stack.push(skillTrees.charAt(i));
                } else if (stack.size() > 0 && stack.peek() == hmSkill.get(skillTrees.charAt(i))) {
                    stack.pop();
                    stack.push(skillTrees.charAt(i)); // 다음차례 스킬도 확인해야하므로 이번 스킬도 스택에 넣기
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
