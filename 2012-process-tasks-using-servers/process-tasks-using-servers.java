import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length;
        int n = servers.length;
        
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }
        
        int[] result = new int[m];
        int time = 0;
        
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);
            
            if (available.isEmpty() && busy.peek()[0] > time) {
                time = busy.peek()[0];
            }
            
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.offer(new int[]{servers[server[1]], server[1]});
            }
            
            int[] bestServer = available.poll();
            result[i] = bestServer[1];
            busy.offer(new int[]{time + tasks[i], bestServer[1]});
        }
        
        return result;
    }
}
