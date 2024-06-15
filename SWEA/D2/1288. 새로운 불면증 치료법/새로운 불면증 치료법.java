import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
		    // 우선 입력을 받은 다음, map을 초기화하자.
		    String num = br.readLine();
		    int pivot = Integer.parseInt(num);
		    int N = Integer.parseInt(num);
		    
		    Set<Character> set = new HashSet<>();
		    
		    // 이제 각각 구한다.
		    while(set.size() < 10) {
		        char[] charArr = Integer.toString(N).toCharArray();
		        for(char c: charArr) {
		            set.add(c);
		            
		        }

		        if(set.size() == 10) break;
		        N += pivot;
		    }
		    System.out.printf("#%d %d\n",test_case, N);
		    
		}
	}
}
