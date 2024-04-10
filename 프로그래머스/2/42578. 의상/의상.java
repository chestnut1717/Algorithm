import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    static int answer = 1;
    public int solution(String[][] clothes) {
        
        // 각 옷을 모두 map에 넣는다.
        for(int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            map.put(type, map.getOrDefault(type, 0) + 1);
            
        }
        
        for(int n: map.values()) {
            answer *= (n+1);
        }

        
        // nC0은 제외
        return answer - 1;
    }

}