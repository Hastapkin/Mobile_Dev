package com.example.lab1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private final List<TodoItem> items;

    public TodoAdapter(List<TodoItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem item = items.get(position);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(item.isCompleted());
        holder.tvTitle.setText(item.getTitle());
        applyStyle(holder, item.isCompleted());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setCompleted(isChecked);
            applyStyle(holder, isChecked);
        });

        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_ID) {
                items.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    private void applyStyle(ViewHolder holder, boolean completed) {
        if (completed) {
            holder.tvTitle.setTextColor(Color.GRAY);
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9"));
        } else {
            holder.tvTitle.setTextColor(Color.BLACK);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tvTitle;
        Button btnDelete;

        ViewHolder(View v) {
            super(v);
            checkBox = v.findViewById(R.id.checkBox);
            tvTitle = v.findViewById(R.id.tvTitle);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
