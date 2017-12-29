package piyush_makwana.timemangement.Goals;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 25-12-2017.
 */

public class GoalsActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private RecyclerView mTaskListView;
    private RecyclerView.Adapter R_adapter;
    private List<ListItems> listItems;
    private ImageButton deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tasks);
        setupBottomNavigationView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.topgoals);
        setSupportActionBar(toolbar);

        CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.testProgress);

        mHelper = new DatabaseHelper(this);
        mTaskListView= findViewById(R.id.list_todo);
        mTaskListView.setHasFixedSize(true);
        mTaskListView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<ListItems>();

        updateUI();


    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(Task.TaskEntry.TABLE,new String[] {Task.TaskEntry.COL_TASK_TITLE},null,null,null,null,null);
        listItems.clear();

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(Task.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(index));
            ListItems item = new ListItems(cursor.getString(index));
            listItems.add(item);
        }


        R_adapter = new RecyclerAdapterGoals(this, listItems);
        mTaskListView.setAdapter(R_adapter);
        cursor.close();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_sub_add :
                final EditText taskText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New Task")
                        .setMessage("Add a new task")
                        .setView(taskText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(Task.TaskEntry.COL_TASK_TITLE,task);
                                db.insert(Task.TaskEntry.TABLE,null,values);                //<
                                updateUI();
                                db.close();

                            }
                        })
                        .setNegativeButton("Cancle",null)
                        .create();
                dialog.show();
                return true;
            //  case R.id.action_delete :

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(GoalsActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menue_timetable,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
