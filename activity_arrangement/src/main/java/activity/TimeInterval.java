package activity;

/**
 * Created by rico on 2016/10/12.
 */
public class TimeInterval {

    int start;
    int end;
    String raw;

    public TimeInterval(String timeInterval) {
        raw = timeInterval;
        String[] times = timeInterval.split("~");
        String startTime = times[0].substring(0, times[0].indexOf(":"));
        String endTime = times[1].substring(0, times[1].indexOf(":"));
        start = Integer.parseInt(startTime);
        end = Integer.parseInt(endTime);
    }

    //返回两个整形区间交集长度
    public int intersection(TimeInterval another) {
        if(start >= another.end || end <= another.start) return 0;

        return Math.min(end, another.end) - Math.max(start, another.start);
    }

    @Override
    public String toString() {
       return raw;
    }
}
