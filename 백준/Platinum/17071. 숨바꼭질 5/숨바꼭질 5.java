import java.io.*;
import java.util.*;

public class Main
{
    static int N, K;
    static int[][] arr = new int[2][500001];
    static Queue<Integer> q = new LinkedList<>();
    static int result;
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        if (N == K) {
            System.out.println(0);
            return;
        }
        // 아직 방문 안한 친구들은 다 -1로 두기
        for (int i = 0; i < 2; i++) {
            Arrays.fill(arr[i], -1);
        }
        arr[0][N] = 0; // 수빈 처음 위치
        q.offer(N);
        result = bfs();
        System.out.println(result);
	}
	
	static int bfs() {
	    int time = 1;
	    int bro = K;
	    while(!q.isEmpty()) {
	        bro += time; // 먼저 동생이 움직인다
	        
	        // 이미 끝난 경우
	        if(bro > 500000) return -1;
	        
	        if(arr[time%2][bro] != -1) {
	            return time;
	        }
	        
	        int size = q.size();
	        for(int i = 0; i < size; i++) {
	            int now = q.poll();
	            
	            int[] moves = {now-1, now+1, now*2};
	            for(int next: moves) {
	                if(next < 0 || next > 500000) continue;
	                // 우선 아직 방문 안한것들 방문처리 함
	                if(arr[time % 2][next] == -1) {
	                    arr[time % 2][next] = arr[(time - 1) % 2][now] + 1;
	                    if(next == bro) return time;
	                    q.offer(next);
	                }    
	            }
	        }
	        
	        time++;
	    }
	    return -1;
	}
}