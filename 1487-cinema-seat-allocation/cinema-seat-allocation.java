class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        for (int[] reserved : reservedSeats) {
            int row = reserved[0];
            int seat = reserved[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        int ans = (n - rowToSeats.size()) * 2;
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        for (int seats : rowToSeats.values()) {
            boolean placedFirst = false;
            if ((seats & masks[0]) == 0) {
                ans++;
                placedFirst = true;
            }
            if ((seats & masks[1]) == 0) {
                ans++;
                placedFirst = true;
            }
            if (!placedFirst && (seats & masks[2]) == 0) {
                ans++;
            }
        }
        return ans;
    }
}
