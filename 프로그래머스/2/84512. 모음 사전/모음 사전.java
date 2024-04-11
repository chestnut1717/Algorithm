// 4:04 => 4:38
class Solution {
    static char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    static StringBuilder sb = new StringBuilder();
    static int cnt = 1;
    static int answer = -1;
    static boolean flag;
    public int solution(String word) {
        perm(0, word);
        
        return answer;
    }
    
    // 부분집합 + 중복순열
    static void perm(int depth, String word) {
        if(flag == true) return;
        if(depth >= 5) return;
        for(int i = 0; i < 5; i++) {
            sb.append(alpha[i]);
            if (flag == false && sb.toString().equals(word)) {
                flag = true;
                answer = cnt;
                return;
            } else {
                cnt++;
                perm(depth+1, word);
                sb.setLength(sb.length()-1);
            }

        }
        return;
    }
}