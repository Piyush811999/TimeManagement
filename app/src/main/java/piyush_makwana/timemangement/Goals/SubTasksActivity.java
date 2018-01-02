package piyush_makwana.timemangement.Goals;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.TimeTable.DailyViewActivity;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 29-12-2017.
 */

public class SubTasksActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private static String GoalName;
    private RecyclerView.Adapter SR_adapter;
    private Bundle extras;
    private  RecyclerView mSubTaskListView;
    private List<SubListItems> sublistItems;
    private TextView goal;
    private CheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_tasks);
        setupBottomNavigationView();

        mHelper = new DatabaseHelper(this);
        extras = getIntent().getExtras();
        if (extras != null){
            GoalName = extras.getString("task");
        }

        goal = findViewById(R.id.goalName);






        extras= getIntent().getExtras();

        if (extras != null){
            goal.setText(extras.getString("task"));
        }


    mSubTaskListView = findViewById(R.id.Sub_list_todo);
        mSubTaskListView.setHasFixedSize(true);
        mSubTaskListView.setLayoutManager(new LinearLayoutManager(this));

        sublistItems = new ArrayList<SubListItems>();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddSubTasks);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText subtaskText = new EditText(SubTasksActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(SubTasksActivity.this)
                        .setTitle("Add Sub-Task")
                        .setMessage("Add a new sub-task")
                        .setView(subtaskText)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String subtask = String.valueOf(subtaskText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(SubTask.SubTaskEntry.COL_TASK_TITLE,subtask);
                                values.put(SubTask.SubTaskEntry.COL_GOAL_TITLE,GoalName);
                                db.insert(SubTask.SubTaskEntry.TABLE,null,values);               //<
                                updateUI();
                                db.close();

                            }
                        })
                        .setNegativeButton("CANCEL",null)
                        .create();
                dialog.show();

            }
        });

        updateUI();


    }

    private void updateUI() {
        ArrayList<String> subtaskList = new ArrayList<>();
        mHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor =db.query(SubTask.SubTaskEntry.TABLE,new String[] {SubTask.SubTaskEntry.COL_TASK_TITLE},SubTask.SubTaskEntry.COL_GOAL_TITLE+" = "+sant(GoalName),null,null,null,null);

        sublistItems.clear();


        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(SubTask.SubTaskEntry.COL_TASK_TITLE);
            subtaskList.add(cursor.getString(index));
            SubListItems item = new SubListItems(cursor.getString(index));
            sublistItems.add(item);
        }

        SR_adapter = new RecyclerAdapterSubTasks(this,sublistItems);
        mSubTaskListView.setAdapter(SR_adapter);
        cursor.close();
    }

    private String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(SubTasksActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);


    }

}
