import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCounts = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int currLoad = load[i];

            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                int[] freed = busy.poll();
                available.add(freed[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer targetServer = available.ceiling(i % k);
            if (targetServer == null) {
                targetServer = available.first();
            }

            available.remove(targetServer);
            busy.offer(new int[]{currTime + currLoad, targetServer});
            
            requestCounts[targetServer]++;
            maxRequests = Math.max(maxRequests, requestCounts[targetServer]);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCounts[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
