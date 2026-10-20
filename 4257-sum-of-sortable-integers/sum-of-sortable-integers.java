class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (check(nums, k, n)) {
                    sum += k;
                }
            }
        }
        return sum;
    }

    private boolean check(int[] nums, int k, int n) {
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                currentMin = Math.min(currentMin, nums[j]);
                currentMax = Math.max(currentMax, nums[j]);
            }
            if (currentMin < prevMax) {
                return false;
            }
            int countDrops = 0;
            for (int j = i; j < i + k; j++) {
                int nextIdx = i + (j - i + 1) % k;
                if (nums[nextIdx] < nums[j]) {
                    countDrops++;
                }
            }
            if (countDrops > 1) {
                return false;
            }
            prevMax = currentMax;
        }
        return true;
    }
}
