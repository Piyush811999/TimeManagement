package piyush_makwana.timemangement.Goals;

import android.provider.BaseColumns;

/**
 * Created by HP on 29-12-2017.
 */

public class SubTask {

    public static final String DB_NAME = "table2.db";
    public static final int DB_VERSION = 1;

    public class SubTaskEntry implements BaseColumns {


        public static final String TABLE = "Subtasks1";
        public static final String COL_TASK_TITLE = "Task";
        public static final String COL_GOAL_TITLE = "Goals";
    }
}
