import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));
        if (!geneBank.contains(endGene)) return -1;
        
        char[] choices = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(startGene);
        visited.add(startGene);
        int mutations = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endGene)) return mutations;
                
                char[] currArray = curr.toCharArray();
                for (int j = 0; j < currArray.length; j++) {
                    char oldChar = currArray[j];
                    for (char c : choices) {
                        if (c == oldChar) continue;
                        currArray[j] = c;
                        String nextGene = new String(currArray);
                        
                        if (geneBank.contains(nextGene) && !visited.contains(nextGene)) {
                            visited.add(nextGene);
                            queue.offer(nextGene);
                        }
                    }
                    currArray[j] = oldChar;
                }
            }
            mutations++;
        }
        
        return -1;
    }
}
