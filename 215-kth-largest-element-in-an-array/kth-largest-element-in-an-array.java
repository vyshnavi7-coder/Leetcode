class Solution {
    public int findKthLargest(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        int[] count = new int[max - min + 1];
        for (int num : nums) {
            count[num - min]++;
        }

        int remains = k;
        for (int i = count.length - 1; i >= 0; i--) {
            remains -= count[i];
            if (remains <= 0) {
                return i + min;
            }
        }

        return -1;
    }
}
