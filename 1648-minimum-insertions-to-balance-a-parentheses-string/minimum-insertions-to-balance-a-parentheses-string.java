class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
                neededRight += 2;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }
                
                if (neededRight > 0) {
                    neededRight -= 2;
                } else {
                    insertions++;
                }
            }
        }
        
        return insertions + neededRight;
    }
}
