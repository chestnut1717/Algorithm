import java.util.*;
import java.io.*;

public class Main
{
    static int T; //test case
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder();
	    
	    T = Integer.parseInt(br.readLine());
	    
	    for(int t = 0; t < T; t++) {
	        // 명령어 입력
	        char[] cmdArray = br.readLine().toCharArray();
	        
	        // 배열 문자열 입력
	        int N = Integer.parseInt(br.readLine());
	        String temp = br.readLine();
	        
	        
	        Deque<Integer> deque = new ArrayDeque<>();
	        
            if(N > 0) {
                
	            String[] arrString = temp.replaceAll("[\\[\\]]", "").split(",");
	            
                for(String s: arrString) {
    	            deque.add(Integer.parseInt(s));
    	        }
            }
	        
            // 이제 여기서 명령어대로 처리를 해준다.
            boolean reverse = false; // false이면 정방향, true이면 역방향
            boolean isError = false; // 에러 여부 체크
            
            for(char cmd: cmdArray) {
                if(cmd == 'R') {
                    reverse = (reverse) ? false : true;
                } else if(cmd == 'D') {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    else if(reverse == false) {
                        deque.pollFirst();
                    } else if(reverse == true) {
                        deque.pollLast();
                    }
                }
            }
            
            if(isError) {
                sb.append("error").append('\n');
            } else {
                int initSize = deque.size();
                sb.append('[');
                while(deque.size() > 0) {
                    if(reverse) sb.append(deque.pollLast()).append(',');
                    else sb.append(deque.pollFirst()).append(',');
                }
                // 빈 배열일때는 ',' 제거하지 못하도록
                if(initSize > 0) {
                    sb.setLength(sb.length() - 1);
                }
                sb.append("]\n");

            }

	    }
	    
	    
	    bw.write(sb.toString());
	    bw.close();
	    
	}
}
