class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age : ages) {
            count[age]++;
        }

        int totalRequests = 0;
        for (int ageA = 1; ageA <= 120; ageA++) {
            if (count[ageA] == 0) continue;
            
            for (int ageB = 1; ageB <= 120; ageB++) {
                if (count[ageB] == 0) continue;
                
                if (ageB <= 0.5 * ageA + 7) continue;
                if (ageB > ageA) continue;
                
                if (ageA == ageB) {
                    totalRequests += count[ageA] * (count[ageA] - 1);
                } else {
                    totalRequests += count[ageA] * count[ageB];
                }
            }
        }

        return totalRequests;
    }
}
