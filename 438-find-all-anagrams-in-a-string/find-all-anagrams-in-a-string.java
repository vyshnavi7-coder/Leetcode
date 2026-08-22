import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution { 
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCounts = new int[26];
        int[] sCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCounts[p.charAt(i) - 'a']++;
            sCounts[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(pCounts, sCounts)) {
            result.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            sCounts[s.charAt(i) - 'a']++;
            sCounts[s.charAt(i - p.length()) - 'a']--;

            if (Arrays.equals(pCounts, sCounts)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    } 
}
