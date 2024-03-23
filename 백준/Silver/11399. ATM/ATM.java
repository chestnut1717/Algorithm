import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] time;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    time = new int[N];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i = 0; i <N; i++) {
	        time[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    // 정렬
	    Arrays.sort(time);
	    for(int i = 0; i < N-1; i++) {
	        time[i+1] += time[i];
	    }
	    // 정렬 후 값 더함
	    int result = 0;
	    for(int t: time) {
	        result += t;
	    }
	    
	    System.out.println(result);
	    
	    
	}
}
