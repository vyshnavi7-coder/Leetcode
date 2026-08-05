class Solution {
    public int findMinMoves(int[] machines) {
        int totalDresses = 0;
        for (int clothes : machines) {
            totalDresses += clothes;
        }

        if (totalDresses % machines.length != 0) {
            return -1;
        }

        int target = totalDresses / machines.length;
        int maxMoves = 0;
        int currentBalance = 0;

        for (int clothes : machines) {
            int balanceChange = clothes - target;
            currentBalance += balanceChange;
            maxMoves = Math.max(maxMoves, Math.max(Math.abs(currentBalance), balanceChange));
        }

        return maxMoves;
    }
}
