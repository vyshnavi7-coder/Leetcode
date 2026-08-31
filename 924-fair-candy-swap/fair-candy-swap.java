import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aliceTotal = 0;
        for (int size : aliceSizes) {
            aliceTotal += size;
        }

        int bobTotal = 0;
        Set<Integer> bobSet = new HashSet<>();
        for (int size : bobSizes) {
            bobTotal += size;
            bobSet.add(size);
        }

        int delta = (bobTotal - aliceTotal) / 2;

        for (int x : aliceSizes) {
            int targetY = x + delta;
            if (bobSet.contains(targetY)) {
                return new int[]{x, targetY};
            }
        }

        return new int[0];
    }
}
