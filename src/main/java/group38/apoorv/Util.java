package group38.apoorv;

import java.util.Calendar;

public class Util {

        public long getCurrentTimeInMinutes() {
                int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int minutes = Calendar.getInstance().get(Calendar.MINUTE);
                return Long.valueOf(hours * 60 + minutes);
        }

        public long convertTimeToMinutes(long hours, long minutes) {
                return Long.valueOf(hours * 60 + minutes);
        }

        public String convertMinutesToTime(long minutes) {
                long hours = minutes / 60;
                minutes = minutes % 60;
                return (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;
        }

        public boolean isValidTime(long hours, long minutes) {
                if (0 <= hours && hours < 24 && 0 <= minutes && minutes < 60)
                        return true;
                return false;
        }
}
