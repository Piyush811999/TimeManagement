package piyush_makwana.timemangement.Utils;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piyush_makwana.timemangement.Analysis.AnalysisActivity;
import piyush_makwana.timemangement.Goals.GoalsActivity;
import piyush_makwana.timemangement.DailyLog.LogActivity;
import piyush_makwana.timemangement.TimeTable.MainActivity;
import piyush_makwana.timemangement.R;
import piyush_makwana.timemangement.TodayView.TodayActivity;


/**
 * Created by HP on 25-12-2017.
 */

public class BottomNavigationViewHelper {

    public static void setBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
       // bottomNavigationViewEx.enableAnimation(false);
        //bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        //bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_timeTable:
                        Intent intent = new Intent(context,MainActivity.class);
                        context.startActivity(intent);
                        return true;

                    case R.id.navigation_dailyLog:
                        Intent intent1 = new Intent(context,LogActivity.class);
                        context.startActivity(intent1);
                        return true;

                    case R.id.navigation_goals:
                        Intent intent2 = new Intent(context,GoalsActivity.class);
                        context.startActivity(intent2);
                        return true;

                    case R.id.navigation_weeklyAnalysis:
                        Intent intent3 = new Intent(context,AnalysisActivity.class);
                        context.startActivity(intent3);
                        return true;

                    case R.id.navigation_today:
                        Intent intent4 = new Intent(context,TodayActivity.class);
                        context.startActivity(intent4);
                        return true;


                }
                return false;
            }
        });
    }
}