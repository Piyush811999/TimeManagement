package piyush_makwana.timemangement.Goals;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import piyush_makwana.timemangement.R;

/**
 * Created by HP on 29-12-2017.
 */

public class RecyclerAdapterSubTasks extends RecyclerView.Adapter<RecyclerAdapterSubTasks.ViewHolder> {
    private Context context;
    private List<SubListItems> SublistItems;

    public RecyclerAdapterSubTasks(Context context, List<SubListItems> sublistItems) {
        this.context = context;
        SublistItems = sublistItems;
    }

    @Override
    public RecyclerAdapterSubTasks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_subtasks,parent,false);
        return new RecyclerAdapterSubTasks.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterSubTasks.ViewHolder holder, int position) {
        SubListItems items = SublistItems.get(position);
        holder.title.setText(items.getSubtask());

    }

    @Override
    public int getItemCount() {

            return SublistItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_subtask);
        }
    }
}
