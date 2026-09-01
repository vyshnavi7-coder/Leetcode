class Solution {
    public boolean canTransform(String start, String result) {
        if (!start.replace("X", "").equals(result.replace("X", ""))) {
            return false;
        }
        
        int p1 = 0;
        int p2 = 0;
        int n = start.length();
        
        while (p1 < n && p2 < n) {
            while (p1 < n && start.charAt(p1) == 'X') {
                p1++;
            }
            while (p2 < n && result.charAt(p2) == 'X') {
                p2++;
            }
            
            if (p1 == n || p2 == n) {
                return p1 == n && p2 == n;
            }
            
            if (start.charAt(p1) == 'L' && p1 < p2) {
                return false;
            }
            if (start.charAt(p1) == 'R' && p1 > p2) {
                return false;
            }
            
            p1++;
            p2++;
        }
        
        return true;
    }
}
