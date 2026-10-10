import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long totalK = (long) k1 + k2;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
        }

        if (totalDiff <= totalK) {
            return 0;
        }

        Arrays.sort(diff);

        long sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            long count = n - i;
            long currentDiff = diff[i];
            long nextDiff = (i > 0) ? diff[i - 1] : 0;
            long availableReduce = (currentDiff - nextDiff) * count;

            if (totalK >= availableReduce) {
                totalK -= availableReduce;
            } else {
                long decreasePerElement = totalK / count;
                long remainder = totalK % count;
                long targetValue = currentDiff - decreasePerElement;

                long ans = 0;
                for (int j = 0; j < i; j++) {
                    ans += (long) diff[j] * diff[j];
                }
                ans += remainder * (targetValue - 1) * (targetValue - 1);
                ans += (count - remainder) * targetValue * targetValue;
                return ans;
            }
        }

        return 0;
    }
}
