package com.example.lab2.exercise1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTask;
        CheckBox checkTask;
        Button btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTask = itemView.findViewById(R.id.txtTask);
            checkTask = itemView.findViewById(R.id.checkTask);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.txtTask.setText(task.getTitle());

        // Remove listener before setting state to avoid triggering it
        holder.checkTask.setOnCheckedChangeListener(null);
        holder.checkTask.setChecked(task.isCompleted());

        updateUI(holder, task);

        holder.checkTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            updateUI(holder, task);
        });

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_ID) {
                taskList.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    private void updateUI(TaskViewHolder holder, Task task) {
        if (task.isCompleted()) {
            holder.txtTask.setTextColor(Color.GRAY);
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9")); // light green
        } else {
            holder.txtTask.setTextColor(Color.BLACK);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
