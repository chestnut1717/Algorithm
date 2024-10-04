import java.util.*;
import java.io.*;
public class Main
{
    static int N, K;
    static char[] arr;
    static boolean[] visited;
    static int answer = 0;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new char[N]; // 햄버거 위치 담을 arr 초기화
		visited = new boolean[N];
		
		
		
		// 햄버거 사람 입력
		arr= br.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
		    if(arr[i] == 'P') {
		        for(int j = -K; j <= K; j++) {
		            if(i + j >= N) break;
		            if(i + j < 0) continue;
		            
		            if(arr[i+j] == 'H' && visited[i+j] == false) {
		                visited[i+j] = true;
		                answer++;
		                break;
		            }
		        }
		    }
		}
		
		System.out.println(answer);
		
        
		
	}
}