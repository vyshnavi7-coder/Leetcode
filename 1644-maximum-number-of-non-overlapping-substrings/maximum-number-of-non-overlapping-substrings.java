import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }
        
        List<String> res = new ArrayList<>();
        int r = -1;
        
        for (int i = 0; i < n; ++i) {
            if (i != left[s.charAt(i) - 'a']) {
                continue;
            }
            
            int newR = right[s.charAt(i) - 'a'];
            boolean valid = true;
            
            for (int j = i + 1; j <= newR; ++j) {
                if (left[s.charAt(j) - 'a'] < i) {
                    valid = false;
                    break;
                }
                newR = Math.max(newR, right[s.charAt(j) - 'a']);
            }
            
            if (valid) {
                if (i > r) {
                    res.add(s.substring(i, newR + 1));
                } else {
                    res.set(res.size() - 1, s.substring(i, newR + 1));
                }
                r = newR;
            }
        }
        
        return res;
    }
}
