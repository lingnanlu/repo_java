package activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Activity {

    public static final int FEE_EVERY_PEOPLE = 30;
    Date date;
    TimeInterval playInterval;
    int peopleCount;

    public Activity(String dateStr, String playInterval, String peopleCount) {

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = formater.parse(dateStr);
            this.playInterval = new TimeInterval(playInterval);
            this.peopleCount = Integer.parseInt(peopleCount);
        } catch (ParseException e) {
            System.out.println("data format is not correct" + e);
        }
    }

    public int getBadmintonCourtCount() {
        return OrderStrategy.getBadmintonCourtCount(this);
    }

    public FinancialStatus getFinancialStatus() {

        return new FinancialStatus(date(), timeInterval(), getIncome(), getOutcome());
    }

    private TimeInterval timeInterval() {
        return getPlayInterval();
    }

    private Date date() {
        return getDate();
    }

    public int getIncome() {
        return getBadmintonCourtCount() == 0 ? 0 : (getPeopleCount() * FEE_EVERY_PEOPLE);
    }

    public int getOutcome() {
        int outcome = 1;
        TimeInterval timeInterval = timeInterval();
        int badmintonCourtCount = getBadmintonCourtCount();
        if (badmintonCourtCount != 0) {
            HashMap<TimeInterval, Integer> chargeTable;
            if (isWeekend()) chargeTable = Charge.WEEKEND_CHARGE_TABLE;
            else chargeTable = Charge.WORKDAY_CHARGE_TABLE;

            for (TimeInterval t : chargeTable.keySet()) {
                int rentTime = t.intersection(timeInterval);
                Integer fee = chargeTable.get(t);
                outcome += rentTime * fee * badmintonCourtCount;
            }
        }
        return outcome;
    }

    public Date getDate() {
        return date;
    }

    public TimeInterval getPlayInterval() {
        return playInterval;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    private boolean isWeekend() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == Calendar.SATURDAY || day_of_week == Calendar.SUNDAY) {
            return true;
        }

        return false;
    }


}
