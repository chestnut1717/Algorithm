import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Collections;

public class Main {
    static final byte[] dy = {0, 1, 0, -1};
    static final byte[] dx = {1, 0, -1, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        byte N = Byte.parseByte(br.readLine());

        boolean[][] map = new boolean[N][N];
        for(byte i = 0; i < N; i++) {
            String row = br.readLine();
            for(byte j = 0; j < N; j++) {
                map[i][j] = (row.charAt(j) == '0' ? false : true);
            }
        }

        boolean[][] visited = new boolean[N][N];
        List<Short> components = new ArrayList<>();
        for(byte i = 0; i < N; i++) {
            for(byte j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j]) {
                    Queue<byte[]> q = new ArrayDeque<>(); 
                    visited[i][j] = true;
                    q.offer(new byte[] {i, j});
                    short cnt = 1;

                    while(!q.isEmpty()) {
                        byte[] coor = q.poll();
                        for(byte k = 0; k < 4; k++) {
                            byte ny = (byte) (coor[0] + dy[k]);
                            byte nx = (byte) (coor[1] + dx[k]);
                            if(0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx] && map[ny][nx]) {
                                visited[ny][nx] = true;
                                cnt++;
                                q.offer(new byte[] {ny, nx});
                            }
                        }
                    }
                    components.add(cnt);
                }
            }
        }

        Collections.sort(components);

        sb.append(components.size()).append('\n');
        for(short size : components) {
            sb.append(size).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    }
}
