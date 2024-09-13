import java.util.*;
import java.io.*;

public class Main
{
    static int N, K;
    static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][K+1];
		
		for(int i = 0; i <= N; i++) {
		    arr[i][0] = 0;
		}
		
		for(int j = 0; j <= K; j++) {
		    arr[0][j] = 1;
		}
		
		for(int i = 1; i<= N; i++) {
		    for(int j = 1; j <= K; j++) {
		        arr[i][j] = (arr[i][j-1] + arr[i-1][j]) %1000000000;
		    }
		}
		
		System.out.println(arr[N][K]);
	}
}