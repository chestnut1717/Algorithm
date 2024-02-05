import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    

		for(int test_case = 1; test_case <= T; test_case++)
		{
		    int N = Integer.parseInt(br.readLine()); // 암호문의 길이
		    st = new StringTokenizer(br.readLine());
		    
		    List<Integer> cryptoArr = new LinkedList<>(); // 암호문 저장하는 배열
		    for(int i = 0; i < N; i++) {
		        cryptoArr.add(Integer.parseInt(st.nextToken()));
		    }
		    

		    int M = Integer.parseInt(br.readLine()); // 명령어의 개수
		    st = new StringTokenizer(br.readLine());
		    List<List<String>> cmdArr = new ArrayList<>();
		    for(int i = 0; i < M; i++) {
		        List<String> tmpList = new ArrayList<>();
		        for(int j = 0; j < 3; j++) {
		            tmpList.add(st.nextToken());
		        }
		        String str = "";
		        for(int j = 0; j < Integer.parseInt(tmpList.get(2)); j++) { // 뒤에 들어오는 문자열 처리
		            
		            str += st.nextToken();
		            str += " ";
		        }
		        tmpList.add(str.trim());
		        cmdArr.add(tmpList);
		    }
		    

		    
		    // 이제 명령어를 처리해보자
		    for(List<String> l: cmdArr) {
		        String cmd = l.get(0);
		        int x = Integer.parseInt(l.get(1));
		        int y = Integer.parseInt(l.get(2));
		        String[] str = l.get(3).split("\\s");
		        List<Integer> lst = new LinkedList<Integer>();
		        for(int i = 0; i < y; i++) {

		            lst.add(Integer.parseInt(str[i]));
		        }
		        
		        cryptoArr.addAll(x, (Collection) lst); //addAll을 활용해서 할 수 있음
		    }
		    
		    // 출력
		    System.out.printf("#%d ", test_case);
		    int idx = 0;
		    for(int i: cryptoArr) {
		        System.out.printf("%d ", i);
		        idx++;
		        if(idx == 10) { break; }
		    }
		    System.out.println();
            
		}
	}
	
}