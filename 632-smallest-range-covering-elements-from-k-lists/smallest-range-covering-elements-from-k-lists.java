class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int max = Integer.MIN_VALUE;
        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }
        
        while (minHeap.size() == nums.size()) {
            int[] curr = minHeap.poll();
            int minVal = curr[0];
            int row = curr[1];
            int col = curr[2];
            
            if (max - minVal < rangeEnd - rangeStart) {
                rangeStart = minVal;
                rangeEnd = max;
            }
            
            if (col + 1 < nums.get(row).size()) {
                int nextVal = nums.get(row).get(col + 1);
                minHeap.offer(new int[]{nextVal, row, col + 1});
                max = Math.max(max, nextVal);
            } else {
                break;
            }
        }
        
        return new int[]{rangeStart, rangeEnd};
    }
}