package piyush_makwana.timemangement.TimeTable;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import piyush_makwana.timemangement.R;



/**
 * Created by HP on 26-12-2017.
 */

public class RecyclerAdapterDaysTimeTable extends RecyclerView.Adapter<RecyclerAdapterDaysTimeTable.ViewHolder> {
    private Context context;
    private List<DayItems> dayItems;


    public RecyclerAdapterDaysTimeTable(Context context, List dayItems) {
        this.context = context;
        this.dayItems = dayItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_days,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterDaysTimeTable.ViewHolder holder, int position) {

        DayItems item = dayItems.get(position);
        holder.day.setText(item.getDay());
        holder.freeHourCount.setText(item.getFreeHoursCount());
        holder.activityCount.setText(item.getActivitiesCount());

    }

    @Override
    public int getItemCount() {
        return dayItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView day;
        public TextView freeHourCount;
        public TextView activityCount;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            day = (TextView) itemView.findViewById(R.id.day);
            freeHourCount = (TextView) itemView.findViewById(R.id.freehoursCount);
            activityCount = (TextView) itemView.findViewById(R.id.numberOfActivity);



        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DayItems items = dayItems.get(position);

            Intent intent = new Intent(context,DailyViewActivity.class);
            intent.putExtra("day",items.getDay());
            context.startActivity(intent);

        }
    }
}
