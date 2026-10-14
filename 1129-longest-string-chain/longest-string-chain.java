import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        Map<String, Integer> dp = new HashMap<>();
        int maxChain = 0;
        
        for (String word : words) {
            int currentLength = 1;
            for (int i = 0; i < word.length(); i++) {
                String predecessor = word.substring(0, i) + word.substring(i + 1);
                if (dp.containsKey(predecessor)) {
                    currentLength = Math.max(currentLength, dp.get(predecessor) + 1);
                }
            }
            dp.put(word, currentLength);
            maxChain = Math.max(maxChain, currentLength);
        }
        
        return maxChain;
    }
}
