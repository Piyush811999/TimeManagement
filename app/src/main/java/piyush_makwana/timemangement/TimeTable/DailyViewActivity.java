package piyush_makwana.timemangement.TimeTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.Utils.BottomNavigationViewHelper;

/**
 * Created by HP on 26-12-2017.
 */

public class DailyViewActivity extends AppCompatActivity{
    private Bundle extras;
    private TextView day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        day = findViewById(R.id.dayName);


        setupBottomNavigationView();
        setupToolbar();

        extras= getIntent().getExtras();

        if (extras != null){
            day.setText(extras.getString("day"));
        }
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


}
