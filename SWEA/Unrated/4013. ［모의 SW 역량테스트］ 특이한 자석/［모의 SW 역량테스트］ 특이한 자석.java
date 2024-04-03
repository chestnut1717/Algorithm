import java.util.*;
import java.io.*;

public class Solution
{
    static int Test;
    static final int T = 4;
    static int N;
    static List<LinkedList<Integer>> list;
    static int result = 0;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    
	    Test = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= Test; test_case++)
		{
    	    // 그 후 N개의 입력 받음
    	    N = Integer.parseInt(br.readLine());
    	    // 톱니바퀴의 개수만큼 list초기화
    	    list = new ArrayList<>();
    	    for(int i = 0; i <= T; i++) {
    	        list.add(new LinkedList<>());
    	    }
    
    
    	    // Test case의 개수만큼 진행
    	    for(int i = 1; i <= T; i++) {
    	        st = new StringTokenizer(br.readLine());
    	        for(int j = 0; j < 8; j++) {
    	            list.get(i).add(Integer.parseInt(st.nextToken()));
    	        }
    	    }
    	    

    
    	    for(int i = 0; i < N; i++) {
    	        st = new StringTokenizer(br.readLine());
    	        int num = Integer.parseInt(st.nextToken());
    	        int dir = Integer.parseInt(st.nextToken());
    	        
    	        // 번호와 방향을 가지고 재귀 탐색을 함 (default spread = 3)
    	        rotate(num, dir, 3);
    	        
    	    }
    	    
    	    // 회전시킨 이후에 맨 앞의 값이 1인 것을 count
    	    int pivot = 0;
    	    result = 0;
    	    for(int i = 1; i <= T; i++) {
    	        LinkedList<Integer> tmpList = list.get(i);
    	        if(tmpList.getFirst() == 1) {
    	            result += Math.pow(2, pivot);
    	        }
    	        pivot++;
    	    }
    	    
    	    sb.append("#").append(test_case).append(" ").append(result).append("\n");

		}
		
		bw.write(sb.toString());
		bw.close();
	}
	// 방향 탐색
	// spread = 3 : 양쪽 / spread = 1 : 왼쪽 / spread = 2 : 오른쪽
	static void rotate(int num, int dir, int spread) {

	    // 만약 탐색할 때, 양쪽 탐색이거나 왼쪽 탐색 간다면
	    if(spread == 3 || spread == 1) {
	        // 바로 옆에 있는 것이 가능하면
            if(rotatePossible(num, false)) {
                rotate(num+1, -dir, 1);
            }
	    }
	    // 양쪽 탐색이거나 오른쪽 탐색이라면
	    if(spread == 3 || spread == 2) {
            if(rotatePossible(num, true)) {
                rotate(num-1, -dir, 2);
            }
	    }
	    
	    // 시계 방향으로 돌려야 한다면
	    if(dir == 1) {
	        list.get(num).addFirst(list.get(num).removeLast());
	    // 반시계 방향으로 돌려야 한다면
	    } else {
	        list.get(num).addLast(list.get(num).removeFirst());
	    }
	}
	
	
	static boolean rotatePossible(int number, boolean isRight) {
	    if(isRight) {
	        if(number == 1) return false;
	        if(list.get(number).get(6) == list.get(number-1).get(2)) return false;
	    }
	    
	    else {
	        if(number == T) return false;
	        if(list.get(number).get(2) == list.get(number+1).get(6)) return false;
	    }
	    
	    return true;
	}
}