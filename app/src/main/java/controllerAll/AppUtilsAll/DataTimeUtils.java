package controllerAll.AppUtilsAll;

public class DataTimeUtils {
    public String GET_UTC_CONVERT_TIME(long time) {
        int SECOND = 1000;
        int MINUTE = 60 * SECOND;
        int HOUR = 60 * MINUTE;
        int DAY = 24 * HOUR;
        int MONTH = 30;
        int YEAR = 365;

//        Log.e("date_day", "- " + DAY);

        if (time < 1000000000000L) {
            time *= 1000;
//            Log.e("date_time_post", "- " + time);
        }

        long now = System.currentTimeMillis();
//        Log.e("date_now", "- " + now);
        if (time > now || time <= 0) {
            return null;
        }

        final long diff = now - time;
//        Log.e("date_diff", "- " + diff);
        if (diff < MINUTE) {
            return "just now";
        } else if (diff < 2 * MINUTE) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE) {
            return diff / MINUTE + " minutes ago";
        } else if (diff < 90 * MINUTE) {
            return "an hour ago";
        } else if (diff < 24 * HOUR) {
            if(diff / HOUR == 1){
                return diff / HOUR + " hour ago";
            }else {
                return diff / HOUR + " hours ago";
            }
        } else if (diff < 48 * HOUR) {
            return "yesterday";
        } else {
            int value = (int) (diff / DAY);
            if (value < 30) {
                return value + " days ago";
            } else if ((value > 30 || value == 30) && value < 60) {
                int month_value = (int) (value / MONTH);
                return month_value + " month ago";
            } else if ((value > 60 || value == 60) && value <365) {
                int months_value = (int) (value / MONTH);
                return months_value + " months ago";
            }else {
                int year_value = (int) (value / YEAR);
                return year_value + " year ago";
            }
        }
    }
}
