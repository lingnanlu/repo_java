package activity;

/**
 * Created by rico on 2016/10/14.
 */
public class OrderStrategy {

    public static int getBadmintonCourtCount(Activity activity) {
        int people = activity.getPeopleCount();
        int badmintonCourtCount = 0;

        int T = people / 6;
        int X = people % 6;

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

}
