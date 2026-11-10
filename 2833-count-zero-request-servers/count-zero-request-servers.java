import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int q = queries.length;
        int[][] sortedQueries = new int[q][2];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] answer = new int[q];
        Map<Integer, Integer> activeServers = new HashMap<>();
        
        int left = 0;
        int right = 0;
        int m = logs.length;
        
        for (int i = 0; i < q; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            int windowStart = queryTime - x;
            int windowEnd = queryTime;
            
            while (right < m && logs[right][1] <= windowEnd) {
                int server = logs[right][0];
                activeServers.put(server, activeServers.getOrDefault(server, 0) + 1);
                right++;
            }
            
            while (left < m && logs[left][1] < windowStart) {
                int server = logs[left][0];
                int count = activeServers.get(server);
                if (count == 1) {
                    activeServers.remove(server);
                } else {
                    activeServers.put(server, count - 1);
                }
                left++;
            }
            
            answer[originalIndex] = n - activeServers.size();
        }
        
        return answer;
    }
}
