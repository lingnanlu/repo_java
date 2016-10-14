package activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity {

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

    public boolean isWeekend() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        if(day_of_week == Calendar.SATURDAY || day_of_week == Calendar.SUNDAY) {
            return true;
        }

        return false;
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

}
