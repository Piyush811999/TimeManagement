package piyush_makwana.timemangement.TimeTable;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import piyush_makwana.timemangement.DatabaseRelated.DatabaseHelper;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 26-12-2017.
 */

public class DailyViewActivity extends AppCompatActivity{
    private Bundle extras;
    private TextView day;
    private DatabaseHelper mHelper;
    private RecyclerView mActivityListView;
    private List<ActivityItems> activityItems;
    private RecyclerView.Adapter R_adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyViewActivity.this,PopupAddActivity.class);

                intent.putExtra("day1",extras.getString("day"));
                startActivity(intent);

            }
        });

        day = findViewById(R.id.dayName);

        setupBottomNavigationView();
        setupToolbar();

        extras= getIntent().getExtras();

        if (extras != null){
            day.setText(extras.getString("day"));
        }

        String dayName = extras.getString("day");

        mHelper = new DatabaseHelper(this);
        mActivityListView=findViewById(R.id.recyclerviewDaily);
        mActivityListView.setHasFixedSize(true);
        mActivityListView.setLayoutManager(new LinearLayoutManager(this));

        activityItems = new ArrayList<>();

        updateUI();





    }

    private void setupToolbar(){
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.dayNameBar);
        setSupportActionBar(toolbar1);


//        if (extras != null) {
//            day = findViewById(R.id.dayName);
//            day.setText(extras.getString("day"));
//        }

    }



    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(DailyViewActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


    }

    private void updateUI() {
        mHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(activity.TABLE_NAME,new String[]{activity.activityEntery.COL_activity_TITLE,activity.activityEntery.COL_from_hour,activity.activityEntery.COL_from_min,activity.activityEntery.COL_duration},activity.activityEntery.COL_day_name+" = "+sant(extras.getString("day")),null,null,null,activity.activityEntery.COL_from_hour);
        activityItems.clear();

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(activity.activityEntery.COL_activity_TITLE);
            int index1 = cursor.getColumnIndex(activity.activityEntery.COL_from_hour);
            int index2 = cursor.getColumnIndex(activity.activityEntery.COL_from_min);
            int index3 = cursor.getColumnIndex(activity.activityEntery.COL_duration);
            ActivityItems items = new ActivityItems(cursor.getString(index),cursor.getInt(index1),cursor.getInt(index2),cursor.getInt(index3));
            activityItems.add(items);
        }

        R_adapter = new RecyclerAdapterActivities(this,activityItems);
        mActivityListView.setAdapter(R_adapter);
        cursor.close();
        db.close();
    }

    private String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
    }




}
