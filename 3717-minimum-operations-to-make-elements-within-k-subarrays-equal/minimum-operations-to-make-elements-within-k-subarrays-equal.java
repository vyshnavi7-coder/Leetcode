import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    private TreeMap<Integer, Integer> lower = new TreeMap<>();
    private TreeMap<Integer, Integer> upper = new TreeMap<>();
    private long lowerSum = 0;
    private long upperSum = 0;
    private int lowerSize = 0;
    private int upperSize = 0;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];

        // Clear global state for fresh test cases
        lower.clear();
        upper.clear();
        lowerSum = upperSum = 0;
        lowerSize = upperSize = 0;

        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) {
                remove(nums[i - x]);
            }
            if (i >= x - 1) {
                int median = lower.lastKey();
                long ops = ((long) median * lowerSize - lowerSum) + (upperSum - (long) median * upperSize);
                minOps[i - x + 1] = ops;
            }
        }

        long inf = (long) 1e16;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], inf);
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i][j] == inf) continue;

                // Option 1: Skip the current element
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);

                // Option 2: Take the window of size x starting at index i
                if (j + 1 <= k && i + x <= n) {
                    dp[i + x][j + 1] = Math.min(dp[i + x][j + 1], dp[i][j] + minOps[i]);
                }
            }
        }

        return dp[n][k] >= inf ? -1 : dp[n][k];
    }

    private void add(int val) {
        if (lower.isEmpty() || val <= lower.lastKey()) {
            lower.put(val, lower.getOrDefault(val, 0) + 1);
            lowerSum += val;
            lowerSize++;
        } else {
            upper.put(val, upper.getOrDefault(val, 0) + 1);
            upperSum += val;
            upperSize++;
        }
        balance();
    }

    private void remove(int val) {
        if (lower.containsKey(val) && val <= lower.lastKey()) {
            int cnt = lower.get(val);
            if (cnt == 1) lower.remove(val);
            else lower.put(val, cnt - 1);
            lowerSum -= val;
            lowerSize--;
        } else {
            int cnt = upper.get(val);
            if (cnt == 1) upper.remove(val);
            else upper.put(val, cnt - 1);
            upperSum -= val;
            upperSize--;
        }
        balance();
    }

    private void balance() {
        if (lowerSize < upperSize) {
            int val = upper.firstKey();
            int cnt = upper.get(val);
            if (cnt == 1) upper.remove(val);
            else upper.put(val, cnt - 1);
            upperSum -= val;
            upperSize--;

            lower.put(val, lower.getOrDefault(val, 0) + 1);
            lowerSum += val;
            lowerSize++;
        } else if (lowerSize - upperSize > 1) {
            int val = lower.lastKey();
            int cnt = lower.get(val);
            if (cnt == 1) lower.remove(val);
            else lower.put(val, cnt - 1);
            lowerSum -= val;
            lowerSize--;

            upper.put(val, upper.getOrDefault(val, 0) + 1);
            upperSum += val;
            upperSize++;
        }
    }
}
