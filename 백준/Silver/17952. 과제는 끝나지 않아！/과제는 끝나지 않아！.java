
import java.io.*;
import java.util.*;

public class Main {
	
	static int N; // 몇 분인지 주어짐
	static PriorityQueue<Task> q = new PriorityQueue<>(); // (조건2) 하던 업무는 나중에 처리하기
	static ArrayList<Task> lst = new ArrayList<>();
	static int total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		// 과제 정보 주어지기
		// i = 현재 몇분인지
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0) {
				// 큐가 비워졌을 때 예외처리
				if (q.isEmpty()) continue;
				
				// 큐에 남아 있는 일 처리
				Task task = q.poll();
				task.takeTime--;
				
				// 만약 일을 다 끝냈으면, score 더해주기
				if (task.takeTime == 0) total += task.score;
				// 그렇지 않다면, 다시 일 넣어주기
				else q.offer(task);
					
			}
			else if (num == 1) {
				int score = Integer.parseInt(st.nextToken()); // 만점
				int takeTime = Integer.parseInt(st.nextToken()); // 과제 해결 시간
				takeTime--; // (조건1) 업무가 주어지면 바로 수행
				
				// 만약 바로 처리할 수 있는 일이라면 => 큐에 넣지 않고 즉시종료
				if (takeTime == 0) {
					total +=score;
					continue;
				}
				
				Task newTask = new Task(score, i, takeTime); // 입력으로 들어온 TASK
				// 큐에 넣으면서 최근에 들어온 것으로 자동정렬
				q.offer(newTask);

			}
			
		}
		
		// 총 점수 출력
		System.out.println(total);
		

	}
	


}

class Task implements Comparable<Task> {
	int score, time, takeTime;
	
	public Task(int score, int time, int takeTime) {
		this.score = score;
		this.time = time; // 추가된 시간을 나타냄
		this.takeTime = takeTime;
	}

	@Override
	public int compareTo(Task o) {		
		return o.time - this.time;
	}

}
