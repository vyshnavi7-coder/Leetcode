class Solution {
    public int numRescueBoats(int[] people, int limit) {
        java.util.Arrays.sort(people);
        int boatCount = 0;
        int left = 0;
        int right = people.length - 1;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boatCount++;
        }
        
        return boatCount;
    }
}
