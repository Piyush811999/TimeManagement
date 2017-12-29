package piyush_makwana.timemangement.Goals;

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
 * Created by HP on 29-12-2017.
 */


public class RecyclerAdapterGoals extends RecyclerView.Adapter<RecyclerAdapterGoals.ViewHolder> {
    private Context context;
    private List<ListItems> listItems;

    public RecyclerAdapterGoals(Context context, List<ListItems> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_tasks,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItems items = listItems.get(position);
        holder.title.setText(items.getTask());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.title_task);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ListItems item = listItems.get(position);

            Intent intent = new Intent(context,SubTasksActivity.class);
            intent.putExtra("task",item.getTask());

            context.startActivity(intent);

        }
    }
}
