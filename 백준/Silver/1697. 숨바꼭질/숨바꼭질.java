import java.io.*;
import java.util.*;

public class Main
{
    // arr = 출발지에서 num으로 가기 위한 최소거리 + 1(시작 = 1부터 시작)
    static int[] arr = new int[100008];
    static int start, end;
    static int prev, next;
    static Deque<Integer> dq = new ArrayDeque<>();
    
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		// 우선 출발지점을 memory에 넣어주기 + 초기화
		dq.push(start);
		arr[start] = 1;
		
		// 시작, 끝값이 같을 경우
		if(start == end) {
		   System.out.println(0);
		   return;
		}
		
		while(!dq.isEmpty()) {
		    
		    prev = dq.poll();
		    int[] moves = {prev - 1, prev + 1, prev * 2};
		    for(int next: moves) {
		        if (next < 0 || next > 100000) continue;
		        if (arr[next] != 0) continue;
		        
		        
		        if(end == next) {
		            System.out.println(arr[prev]);
		            return;
		        } else {
		            arr[next] = arr[prev] + 1;
		        }
		        
		        dq.add(next);
		    }
		    
		}
		
		
	}

}