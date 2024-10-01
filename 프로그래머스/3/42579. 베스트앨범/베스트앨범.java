import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        
        Map<String, PriorityQueue<Music>> map = new HashMap<>();
        Map<String, Integer> keyMap = new HashMap<>(); // 장르별로 정렬하기 위한 map
        List<Integer> answer = new ArrayList<>();
        
        
        // 이제 음악의 개수만큼 반복문을 돌리면서
        int N = genres.length;
        for(int i = 0; i < N; i++) {
            // 만약 map에 키가 등록되지 않았다면
            String genre = genres[i];
            if(!map.containsKey(genre)) {
                // 우선순위큐 하나 만들어서 넣음
                map.put(genre, new PriorityQueue<Music>());
                keyMap.put(genre, 0);
                
            }
            
            // 그리고 map의 우선순위에 값을 넣음
            PriorityQueue<Music> pq = map.get(genre);
            pq.offer(new Music(plays[i], i)); // 플레이수와 고유번호가 담긴 Music instance를 넣음
            keyMap.put(genre, keyMap.get(genre)+plays[i]);
            
            
        }
        // 그리고 key값으로 정렬해주기 위함
        List<String> keySet = new ArrayList<>(map.keySet());
        
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return keyMap.get(o2) - keyMap.get(o1);
            }
        });

        // 이제 keySet에서 하나씩 뽑는다.
        for(String key: keySet) {
            PriorityQueue<Music> pq = map.get(key);
            // 이제 이 pq가 비지 않고, 사이즈가 2일 때까지 계속 뽑는다.
            int cnt = 0;
            while(!pq.isEmpty() && cnt < 2) {
                Music music = pq.poll();
                answer.add(music.num);
                cnt++;
            }
        }
        int[] arr = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
    static class Music implements Comparable<Music>{
        int play, num;
        public Music(int play, int num) {
            this.play = play;
            this.num = num;
        }
        
        @Override
        public int compareTo(Music o) {
            if(this.play == o.play) {
                // 고유번호 숫자가 작은 수가 우선
                return this.num - o.num;
            }
            // play가 많이 재생된 숫자
            return o.play - this.play;
        }
        // 이제 Key의 
        @Override
        public String toString() {
            return String.format("[num: %d / play: %d]", this.num, this.play);
        }
    }
}