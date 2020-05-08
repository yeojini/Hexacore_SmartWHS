package logistics;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pentacore.tabletserver.R;

import java.util.LinkedList;
import java.util.Queue;

public class ConsoleQueueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LinkedList consoleQueue;
    public ConsoleQueueAdapter(Queue consoleQueue){
        this.consoleQueue = (LinkedList)consoleQueue;
        this.consoleQueue.addAll(consoleQueue);
    }
    public void updateConsoleQueue(Queue consoleQueue) {
        this.consoleQueue = (LinkedList)consoleQueue;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView consoleQueue_logMessage;

        public ViewHolder(View view) {
            super(view);
            consoleQueue_logMessage = view.findViewById(R.id.consoleQueue_logMessage);
        }

        public void setItem(String logMessage) {
            consoleQueue_logMessage.setText(logMessage);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.console_queue_item, viewGroup, false);

        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(consoleQueue.get(position)==null) return;
        ConsoleQueueAdapter.ViewHolder viewHolder = (ConsoleQueueAdapter.ViewHolder) holder;
        viewHolder.setItem((String)(consoleQueue.get(position)));
    }

    @Override
    public int getItemCount() {
        return consoleQueue.size();
    }

}
