import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer("0000");
        visited.add("0000");
        int turns = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) return turns;
                
                for (int j = 0; j < 4; j++) {
                    for (int dir = -1; dir <= 1; dir += 2) {
                        char[] chars = curr.toCharArray();
                        int digit = (chars[j] - '0' + dir + 10) % 10;
                        chars[j] = (char) (digit + '0');
                        String next = new String(chars);
                        
                        if (!dead.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            turns++;
        }
        
        return -1;
    }
}
