package group38.apoorv;

import java.util.Calendar;

public class Util {

        public long getCurrentTimeInMinutes(){
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int minutes = Calendar.getInstance().get(Calendar.MINUTE);
                return Long.valueOf(hour * 60 + minutes); 

        }

        public long convertTimeToMinutes(long hh, long mm) {
                return Long.valueOf(hh * 60 + mm);
        }


        public String convertMinuteToTime(int minutes){
                int hour = minutes%60;
                int min = minutes/60;
                return hour+":"+min;
        }

}
