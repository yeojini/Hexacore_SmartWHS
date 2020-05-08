package logistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pentacore.tabletserver.R;

import java.util.LinkedList;
import java.util.Queue;

import msg.Task;

public class TaskQueueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LinkedList taskQueue;
    public TaskQueueAdapter(Queue taskQueue){
        this.taskQueue = (LinkedList)taskQueue;
        this.taskQueue.addAll(taskQueue);
    }
    public void updateTaskQueue(Queue taskQueue) {
        this.taskQueue = (LinkedList)taskQueue;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskQueue_item;


        public ViewHolder(View view) {
            super(view);
            taskQueue_item = view.findViewById(R.id.taskQueue_item);

        }

        public void setItem(Task task) {
            StringBuilder sb = new StringBuilder();
            if(task.getIo()==0) sb.append("[입고] ");
            else if(task.getIo()==1) sb.append("[출고] ");
            sb.append(task.getName())
                .append(",  수량: ")
                .append(task.getQty())
                .append("개,  위치: ")
                .append(task.getLocX())
                .append(", ")
                .append(task.getLocY())
                .append("");
            taskQueue_item.setText(sb.toString());
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.task_queue_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(taskQueue.get(position)==null) return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setItem( (Task)(taskQueue.get(position)));
    }

    @Override
    public int getItemCount() {
        return taskQueue.size();
    }
}
