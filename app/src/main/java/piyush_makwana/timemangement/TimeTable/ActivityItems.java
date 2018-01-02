package piyush_makwana.timemangement.TimeTable;

/**
 * Created by HP on 30-12-2017.
 */

public class ActivityItems {
    private String activity_name;
    private  String location;
    private int from_hour;
    private int from_min;
    private int to_hour;
    private int to_min;
    private String day;
    private int duration;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ActivityItems(String activity_name, String location, int from_hour, int from_min, int to_hour, int to_min, String day, int duration) {
        this.activity_name = activity_name;
        this.location = location;
        this.from_hour = from_hour;
        this.from_min = from_min;
        this.to_hour = to_hour;
        this.to_min = to_min;
        this.day = day;
        this.duration = duration;
    }

    public ActivityItems(String activity_name, int from_hour, int from_min, int duration) {
        this.activity_name = activity_name;
        this.from_hour = from_hour;
        this.from_min = from_min;
        this.duration = duration;
    }

    public ActivityItems(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFrom_hour() {
        return from_hour;
    }

    public void setFrom_hour(int from_hour) {
        this.from_hour = from_hour;
    }

    public int getFrom_min() {
        return from_min;
    }

    public void setFrom_min(int from_min) {
        this.from_min = from_min;
    }

    public int getTo_hour() {
        return to_hour;
    }

    public void setTo_hour(int to_hour) {
        this.to_hour = to_hour;
    }

    public int getTo_min() {
        return to_min;
    }

    public void setTo_min(int to_min) {
        this.to_min = to_min;
    }
}
