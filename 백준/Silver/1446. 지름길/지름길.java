import java.util.*;
import java.io.*;

public class Main
{
    static int N, D;
    static int[] start;
    static int[] end;
    static int[] dist;
    static int[] memo;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		
		start = new int[N];
		end = new int[N];
		dist = new int[N];
		memo = new int[D+1];
		// 우선 모든 거리를 초기화한다.(memo[50] = 50만큼 걸어갔을 때 운전해야 하는 거리)
		for(int i = 0; i <= D; i++) {
		    memo[i] = i;
		}
		
		// 입력을 받는다.
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    start[i] = Integer.parseInt(st.nextToken());
		    end[i] = Integer.parseInt(st.nextToken());
		    dist[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이제 start, end, dist를 통해 매번 memo의 최솟값을 탐색한다.
		for(int i = 1; i <= D; i++) {
		    
		    memo[i] = Math.min(memo[i], memo[i-1]+1);
		    // 이제 배열에서 저장된 값을 하나씩 꺼내준다.
		    for(int j = 0; j < N; j++) {
		        // 만약 종점이 현재 탐색하는 값(i)와 같다면
		        if(end[j] == i) {
		            memo[i] = Math.min(memo[i], memo[start[j]] + dist[j]);
		        }
		    }
		}

		System.out.println(memo[D]);
		
	}
}
