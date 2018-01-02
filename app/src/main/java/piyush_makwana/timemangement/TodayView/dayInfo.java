package piyush_makwana.timemangement.TodayView;

import android.provider.BaseColumns;

/**
 * Created by HP on 02-01-2018.
 */

public class dayInfo {
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME ="dayInfo_table";

    //columns

    public class dayInfoEntery implements BaseColumns {

        public static final String COL_date = "Date";
        public static final String COL_productiveTime = "Productive_time";
        public static final String COL_socialTime = "Social_time";
        public static final String COL_freetime = "Free_time";
        public static final String COL_Notes = "Notes";

           }


}
