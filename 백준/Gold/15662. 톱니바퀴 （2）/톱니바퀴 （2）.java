import java.io.*;
import java.util.*;

public class Main
{
    static int T;
    static int N;
    static List<LinkedList<Integer>> list = new ArrayList<>();
    static int result = 0;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    // 톱니바퀴의 개수 입력!!!
	    T = Integer.parseInt(br.readLine());
	    
	    // 톱니바퀴의 개수만큼 list초기화
	    for(int i = 0; i <= T; i++) {
	        list.add(new LinkedList<>());
	    }
	    
	    // Test case의 개수만큼 진행
	    for(int i = 1; i <= T; i++) {
	        char[] charArr = br.readLine().toCharArray();
	        for(int j = 0; j < 8; j++) {
	            list.get(i).add(charArr[j] - '0');
	        }
	    }
	    // 그 후 N개의 입력 받음
	    N = Integer.parseInt(br.readLine());

	    for(int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        int num = Integer.parseInt(st.nextToken());
	        int dir = Integer.parseInt(st.nextToken());
	        
	        // 번호와 방향을 가지고 재귀 탐색을 함 (default spread = 3)
	        rotate(num, dir, 3);
	        
	    }
	    
	    // 회전시킨 이후에 맨 앞의 값이 1인 것을 count
	    for(int i = 1; i <= T; i++) {
	        LinkedList<Integer> tmpList = list.get(i);
	        if(tmpList.getFirst() == 1) {
	            result++;
	        }
	    }
	    
	    System.out.println(result);
	    
	    
	}
	
	// 방향 탐색
	// spread = 3 : 양쪽 / spread = 1 : 왼쪽, spread = 2 : 오른쪽
	static void rotate(int num, int dir, int spread) {
		if(dir == 1)
			dir = -1;
		else
			dir = 1;
	    // 만약 탐색할 때, 양쪽 탐색이거나 왼쪽 탐색 간다면
	    if(spread == 3 || spread == 1) {
	        // 바로 옆에 있는 것이 가능하면
	        if(rotatePossible(num, false)) {
	            rotate(num-1, dir, 1);
	        }
	    }
	    // 양쪽 탐색이거나 오른쪽 탐색이라면
	    if(spread == 3 || spread == 2) {
	        if(rotatePossible(num, true)) {
	            rotate(num+1, dir, 2);
	        }
	    }
	    
	    // 시계 방향으로 돌려야 한다면
	    if(dir == -1) {
	        list.get(num).addFirst(list.get(num).removeLast());
	    // 반시계 방향으로 돌려야 한다면
	    } else {
	        list.get(num).addLast(list.get(num).removeFirst());
	    }
	}
	
	static boolean rotatePossible(int number, boolean isRight) {
	    // 왼쪽탐색
		if(isRight) {
		    // 만약 톱니바퀴 끝에 도달했으면
			if(number == T)
				return false;
            
            // 톱니바퀴의 2번째랑 톱니바퀴의 6번째랑 일치한다면 => 이동 X
			if(list.get(number).get(2) == list.get(number+1).get(6))
				return false;
		}
		// 왼쪽 탐색
		else {
		    // 만약 처음에 도착했으면
			if(number == 1)
				return false;
            
            // 만약 톱니바퀴의 2번째랑 6번째랑 일치 => 이동 X
			if(list.get(number).get(6) == list.get(number-1).get(2))
				return false;
		}

		return true;
	    
	}
}