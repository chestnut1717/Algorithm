import java.util.*;

class Solution {
    static Stack<Integer> stk = new Stack<>();
    public String solution(String number, int k) {
        int targetN = number.length() - k;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < number.length(); i++) {
            int num = number.charAt(i) - '0';
            while (!stk.isEmpty() && k > 0 && stk.peek() < num) {
                stk.pop();
                k--;
            }
            stk.push(num); //스택이 비지 않았다면 값 넣어줌
        }
        
        while(k > 0) {
            stk.pop();
            k--;
        }
        
        // 그리고 뒤에서부터 빼준다.
        for(int i = 0; i < targetN; i++) {
            sb.append(stk.pop());
        }
        
        
        return sb.reverse().toString();
    }
}