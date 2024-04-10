import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    static int arr[];
    static boolean visited[];
    static int mulArr[];
    static int answer = 0;
    public int solution(String[][] clothes) {
        
        // 각 옷을 모두 map에 넣는다.
        for(int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            if(map.containsKey(type)) {
                map.put(type, map.get(type)+1);
            } else {
                map.put(type, 1);   
            }
        }
        // 각 Map을 모두 담아줌
        arr = new int[map.size()+1];
        mulArr = new int[map.size()+1];
        visited = new boolean[map.size()+1];
        
        Arrays.fill(mulArr, 1);
        
        int idx = 1;
        for(int cnt: map.values()) {
            arr[idx] = cnt;
            idx++;
            
        }

        
        
        calc(1);
        
        // nC0은 제외
        answer -= 1;
        return answer;
    }
    
    static void calc(int cnt) {
        if(cnt > visited.length-1) {
            // 갱신
            answer += mulArr[cnt-1];
            return;
        }
        visited[cnt] = true;
        mulArr[cnt] = arr[cnt] * mulArr[cnt-1];
        calc(cnt+1);
        mulArr[cnt] = mulArr[cnt-1];
        visited[cnt] = false;
        calc(cnt+1);
        
    }
}