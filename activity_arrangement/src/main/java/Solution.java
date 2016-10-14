import java.io.*;
import java.util.*;

import activity.*;

public class Solution {

    public static HashMap<TimeInterval, Integer> WORKDAY_CHARGE_TABLE = new HashMap<>();
    public static HashMap<TimeInterval, Integer> WEEKEND_CHARGE_TABLE = new HashMap<>();

    static {
        WORKDAY_CHARGE_TABLE.put(new TimeInterval("9:00~12:00"), 30);
        WORKDAY_CHARGE_TABLE.put(new TimeInterval("12:00~18:00"), 50);
        WORKDAY_CHARGE_TABLE.put(new TimeInterval("18:00~20:00"), 80);
        WORKDAY_CHARGE_TABLE.put(new TimeInterval("20:00~22:00"), 60);

        WEEKEND_CHARGE_TABLE.put(new TimeInterval("9:00~12:00"), 40);
        WEEKEND_CHARGE_TABLE.put(new TimeInterval("12:00~18:00"), 50);
        WEEKEND_CHARGE_TABLE.put(new TimeInterval("18:00~22:00"), 60);
    }

    public static String generateSummary(String input) {

        List<Activity> activities = parseFrom(new ByteArrayInputStream(input.getBytes()));

        Summary summary = new Summary();

        for (Activity activity : activities) {
            summary.addFinacialStatus(calFinacialStatus(activity));
        }

        return summary.toString();
    }

    public static void main(String[] args) {

        //test1 范例
        String input1 =
                "2016-06-02 20:00~22:00 7\n" +
                        "2016-06-03 09:00~12:00 14\n" +
                        "2016-06-04 14:00~17:00 22\n" +
                        "2016-06-05 19:00~22:00 3\n" +
                        "2016-06-06 12:00~15:00 15\n" +
                        "2016-06-07 15:00~17:00 12\n" +
                        "2016-06-08 10:00~13:00 19\n" +
                        "2016-06-09 16:00~18:00 16\n" +
                        "2016-06-10 20:00~22:00 5\n" +
                        "2016-06-11 13:00~15:00 11";

        System.out.println(generateSummary(input1));

       /*
        [Summary]

        2016-06-02 20:00~22:00 +210 -240 -30
        2016-06-03 09:00~12:00 +420 -180 +240
        2016-06-04 14:00~17:00 +660 -600 +60
        2016-06-05 19:00~22:00 +0 -0 0
        2016-06-06 12:00~15:00 +450 -300 +150
        2016-06-07 15:00~17:00 +360 -200 +160
        2016-06-08 10:00~13:00 +570 -330 +240
        2016-06-09 16:00~18:00 +480 -300 +180
        2016-06-10 20:00~22:00 +150 -120 +30
        2016-06-11 13:00~15:00 +330 -200 +130

        Total Income: 3630
        Total Payment: 2470
        Profit: 1160
        */

        //test2 活动时间跨多个收费时段
        String input2 = "2016-06-02 11:00~14:00 7";
        System.out.println(generateSummary(input2));

       /*
       [Summary]

        2016-06-02 11:00~14:00 +210 -260 -50

        Total Income: 210
        Total Payment: 260
        Profit: -50
        */
    }

    /**
     * @param T 立即确定的场地数
     * @param X 多出来的人数
     * @return 需要定的场地数
     */
    private static int orderStrategy(int T, int X) {

        int badmintonCourtCount = 0;

        if (T == 0 && X < 4) {
            badmintonCourtCount = 0;
        } else if (T == 0 && X >= 4) {
            badmintonCourtCount = 1;
        } else if (T == 1) {
            badmintonCourtCount = 2;
        } else if ((T == 2 || T == 3) && X >= 4) {
            badmintonCourtCount = T + 1;
        } else if ((T == 2 || T == 3) && X < 4) {
            badmintonCourtCount = T;
        } else if (T > 3) {
            badmintonCourtCount = T;
        }

        return badmintonCourtCount;
    }

    private static List<Activity> parseFrom(InputStream source) {

        List<Activity> activities = new ArrayList<>();

        Scanner scanner = new Scanner(source);

        while (scanner.hasNext()) {
            String raw = scanner.nextLine();
            String[] segments = raw.split(" ");
            activities.add(new Activity(segments[0], segments[1], segments[2]));
        }

        return activities;
    }

    /**
     * 计算每次活动的费用情况， 保存在一个FinacialStatus中
     */
    private static FinancialStatus calFinacialStatus(Activity activity) {

        Date date = activity.getDate();
        TimeInterval timeInterval = activity.getPlayInterval();
        int people = activity.getPeopleCount();
        int badmintonCourtCount = orderStrategy(people / 6, people % 6);

        int income = 0, outcome = 0;
        if (badmintonCourtCount != 0) {

            income = people * 30;

            HashMap<TimeInterval, Integer> chargeTable = null;
            if (activity.isWeekend()) chargeTable = WEEKEND_CHARGE_TABLE;
            else chargeTable = WORKDAY_CHARGE_TABLE;

            for (TimeInterval t : chargeTable.keySet()) {
                int rentTime = t.intersection(timeInterval);
                Integer fee = chargeTable.get(t);
                outcome += rentTime * fee * badmintonCourtCount;
            }
        }

        return new FinancialStatus(date, timeInterval, income, outcome);
    }
}

