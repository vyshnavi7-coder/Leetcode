class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] time : times) {
            adj.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});
        Map<Integer, Integer> dist = new HashMap<>();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int node = curr[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (adj.containsKey(node)) {
                for (int[] edge : adj.get(node)) {
                    int nextNode = edge[0];
                    int weight = edge[1];
                    if (!dist.containsKey(nextNode)) {
                        pq.offer(new int[]{d + weight, nextNode});
                    }
                }
            }
        }
        if (dist.size() != n) return -1;
        int maxDelay = 0;
        for (int d : dist.values()) {
            maxDelay = Math.max(maxDelay, d);
        }
        return maxDelay;
    }
}
