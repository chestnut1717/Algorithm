// 4:04 => 4:38
class Solution {
    static char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;
    static int answer = -1;
    static boolean flag;
    public int solution(String word) {
        perm(0, word);
        
        return answer;
    }
    
    // 부분집합 + 중복순열
    static void perm(int depth, String word) {
        
        if (sb.toString().equals(word)) {
            answer = cnt;
            return;
        }
        
        if(depth == 5) {
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            sb.append(alpha[i]);
            cnt++;
            perm(depth+1, word);
            sb.setLength(sb.length() - 1);
        }
        
    }

}

