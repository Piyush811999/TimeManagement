package piyush_makwana.timemangement.Goals;

import android.provider.BaseColumns;

/**
 * Created by HP on 29-12-2017.
 */

public class Task {


    public static final String DB_NAME = "table1.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks1";
        public static final String COL_TASK_TITLE = "Goals";
    }

}
