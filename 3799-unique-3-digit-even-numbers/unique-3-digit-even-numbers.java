import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;
        
        for (int i = 0; i < n; ++i) {
            if (digits[i] % 2 != 0) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (digits[k] == 0 || k == i || k == j) {
                        continue;
                    }
                    uniqueNumbers.add(digits[k] * 100 + digits[j] * 10 + digits[i]);
                }
            }
        }
        return uniqueNumbers.size();
    }
}
