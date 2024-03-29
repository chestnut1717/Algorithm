	import java.io.*;
import java.util.*;
 
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static ArrayList<Move> nearPerson;
    static ArrayList<Person> arrPerson;
    static int N, M;
    
    public static class Person {
        int x;
        int y;
        int targetX;
        int targetY;
        
        public Person(int x, int y, int targetX, int targetY) {
            this.x = x;
            this.y = y;
            this.targetX = targetX;
            this.targetY = targetY;
        }
    }
    
    public static class Move implements Comparable<Move>{
        int x;
        int y;
        int dis;
        
        public Move (int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
        
        public int compareTo (Move o) {
            if (this.dis == o.dis){
                if (this.x == o.x){
                    return this.y - o.y; 
                } else {
                    return this.x - o.x;
                }
            } else {
                return this.dis - o.dis;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 손님 수
        int K = Integer.parseInt(st.nextToken()); // 초기 연료
        
        map = new int[N][N];
        
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int tX = Integer.parseInt(st.nextToken());
        int tY = Integer.parseInt(st.nextToken());
        
        arrPerson = new ArrayList<>();  // 손님 위치와 목적지
        nearPerson = new ArrayList<>(); // 택시에서 제일 가까운 손님 
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());
            
            arrPerson.add(new Person(x, y, targetX, targetY));
        }
        
        while (true) {
            // 더 이상 손님이 없을 경우
            if (arrPerson.isEmpty()) {
                System.out.println(K);
                return;
            }
            
            nearPerson.clear();
            findPerson(tX-1, tY-1, 0);
            
            // 가까운 손님이 없을 경우
            if (nearPerson.isEmpty()) {
                System.out.println(-1);
                return;
            }
            
            int dist = 0;
            Move now = nearPerson.get(0); // 제일 가까운 손님
            
            K -= now.dis; // 택시에서 손님까지의 거리
            
            for (int i=0; i<arrPerson.size(); i++) {
                if (now.x == arrPerson.get(i).x-1 && now.y == arrPerson.get(i).y-1) {
                    dist = goTaxi(arrPerson.get(i).x-1, arrPerson.get(i).y-1, arrPerson.get(i).targetX-1, arrPerson.get(i).targetY-1);
                    
                    if (dist == -1) {
                        System.out.println(-1);
                        return;
                    }
                    
                    // 택시 위치를 도착지로 초기화 및 배열에서 손님 삭제
                    tX = arrPerson.get(i).targetX;
                    tY = arrPerson.get(i).targetY;
                    arrPerson.remove(i);
                    break;
                }
            }
                        
            K -= dist; // 손님에서 도착지까지의 거리
            
            // 기름이 없을 경우
            if (K < 0) {
                System.out.println(-1);
                return;
            }
            
            K += dist * 2; // 손님에서 도착지까지의 거리
        }
    }
    
    // 가까운 손님 찾기
    public static void findPerson(int x, int y, int dis) {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Move> q = new PriorityQueue<>();
        q.add(new Move(x, y, dis));
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            Move now = q.poll();
            
            for (int i=0; i<arrPerson.size(); i++) {
                if (now.x == arrPerson.get(i).x-1 && now.y == arrPerson.get(i).y-1) {
                    nearPerson.add(new Move(now.x, now.y, now.dis));
                }
            }
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1 || map[nx][ny] == 1 || visited[nx][ny]) continue;
                if(nearPerson.size() == 0) {
                    q.add(new Move(nx, ny, now.dis+1));
                    visited[nx][ny] = true;
                }

            }
        }
    }
    
    // 손님을 태우고 도착지까지 이동
    public static int goTaxi(int x, int y, int targetX, int targetY) {
        boolean[][] visited = new boolean[N][N];
        Queue<Move> q = new LinkedList<>();
        q.add(new Move(x, y, 0));
        
        while (!q.isEmpty()) {
            Move now = q.poll();
            
            if (now.x == targetX && now.y == targetY) {
                return now.dis;
            }
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1 || map[nx][ny] == 1 || visited[nx][ny]) continue;
                
                q.add(new Move(nx, ny, now.dis+1));
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
}