class Solution {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = convertToDays(arriveAlice);
        int leaveA = convertToDays(leaveAlice);
        int arriveB = convertToDays(arriveBob);
        int leaveB = convertToDays(leaveBob);

        int start = Math.max(arriveA, arriveB);
        int end = Math.min(leaveA, leaveB);

        return Math.max(0, end - start + 1);
    }

    private int convertToDays(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int days = 0;
        for (int i = 0; i < month - 1; i++) {
            days += daysInMonth[i];
        }
        return days + day;
    }
}
