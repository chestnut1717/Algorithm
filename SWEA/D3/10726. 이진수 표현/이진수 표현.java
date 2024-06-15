import java.util.*;
import java.io.*;

public class Solution
{
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    int T = Integer.parseInt(br.readLine());
	    
		for(int test_case = 1; test_case <= T; test_case++) {
		    st = new StringTokenizer(br.readLine());
		    int N = Integer.parseInt(st.nextToken());
		    int M = Integer.parseInt(st.nextToken());
		    
		    int bit = (1 << N) -1;
		    
		    String result = ((M & bit) == bit) ? "ON" : "OFF" ;
		    System.out.printf("#%d %s\n", test_case, result);
		    
		}
	}
}
