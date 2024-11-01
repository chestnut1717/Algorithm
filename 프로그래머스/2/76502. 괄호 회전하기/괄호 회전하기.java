import java.util.*;
import java.io.*;

class Solution {
    static int answer = 0;
    
    public int solution(String s) {
        Stack<Character> stk = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            String str = s.substring(i, s.length()) + s.substring(0, i);
            // 이제 해당 스택의 조건 진행
            for(int j = 0; j < str.length(); j++){
                char c = str.charAt(j);
                
                if(stk.isEmpty()) {
                    stk.push(c);
                }
                else{
                    if(stk.peek() == '(' && c == ')') {
                        stk.pop();
                    }
                    else if(stk.peek() == '{' && c == '}') {
                        stk.pop();
                    }
                    else if(stk.peek() == '[' && c == ']') {
                        stk.pop();
                    }
                    else {
                        stk.push(c);
                    }
                }
            }
            if(stk.isEmpty()) answer++; 
            stk.clear();
        }
        
        return answer;
    }
}