package src.main.java.group38.apoorv;

import java.util.Date;

public class Util {

        public long getCurrentTimeInMilli(){
                Date date = new Date();
                return date.getTime();

        }

        public long convertDateToMilli(Date date){
                return date.getTime();
        }

        public Date convertMilliToDate(Long milli){

                return null;
        }
        
}
