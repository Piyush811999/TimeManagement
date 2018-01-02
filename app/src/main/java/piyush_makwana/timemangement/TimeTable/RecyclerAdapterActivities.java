package piyush_makwana.timemangement.TimeTable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import piyush_makwana.timemangement.R;

/**
 * Created by HP on 01-01-2018.
 */

public class RecyclerAdapterActivities extends RecyclerView.Adapter<RecyclerAdapterActivities.ViewHolder> {
    private Context context;
    private List<ActivityItems> activityItems;

    public RecyclerAdapterActivities(Context context, List<ActivityItems> activityItems) {
        this.context = context;
        this.activityItems = activityItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_activities,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterActivities.ViewHolder holder, int position) {
        ActivityItems items = activityItems.get(position);


        holder.activity.setText(items.getActivity_name());
        holder.fromTime.setText(timeToString(items.getFrom_hour(),items.getFrom_min()));
        holder.duration.setText(items.getDuration() + " min");



    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activity;
        public TextView fromTime;
        public TextView duration;

        public ViewHolder(View itemView) {
            super(itemView);

            activity = itemView.findViewById(R.id.activity);
            fromTime = itemView.findViewById(R.id.timeFromView);
            duration = itemView.findViewById(R.id.durationView);


        }
    }

    public String timeToString (int hours , int minutes ){


        if (hours == 0) {
            return (12 + " : " + minutes + " AM");
        } else if (hours < 12) {
            return (hours + " : " + minutes + " AM");
        } else if (hours == 12) {
            return (hours + " : " + minutes + " PM");
        } else {
            return (hours - 12 + " : " + minutes + " PM");
        }


    }


}

