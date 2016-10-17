import java.io.*;
import java.util.*;

import activity.*;

public class Solution {

    public static String generateSummary(String input) {

        List<Activity> activities = parseFrom(new ByteArrayInputStream(input.getBytes()));

        Summary summary = new Summary();

        for (Activity activity : activities) {
            summary.addFinacialStatus(activity.getFinancialStatus());
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


}

