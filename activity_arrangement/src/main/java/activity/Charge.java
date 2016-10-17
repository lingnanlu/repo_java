package activity;

import java.util.HashMap;

/**
 * Created by rico on 2016/10/14.
 *
 * 收费标准不应该由Activity负责， 当收费标准更改时， 修改该类即可
 */
public class Charge {

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
}
