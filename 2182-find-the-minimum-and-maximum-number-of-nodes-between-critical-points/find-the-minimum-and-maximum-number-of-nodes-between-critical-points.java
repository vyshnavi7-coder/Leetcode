class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            ListNode nextNode = curr.next;

            if ((curr.val > prev.val && curr.val > nextNode.val) || 
                (curr.val < prev.val && curr.val < nextNode.val)) {
                
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - lastCriticalIndex);
                }
                lastCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        if (firstCriticalIndex == lastCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}
