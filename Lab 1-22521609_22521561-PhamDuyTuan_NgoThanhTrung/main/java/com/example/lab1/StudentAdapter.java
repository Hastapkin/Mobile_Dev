package com.example.lab1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private final List<Student> students;
    private final Context context;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student s = students.get(position);
        holder.tvName.setText(s.getName());
        holder.tvAverage.setText(String.format("Avg: %.2f", s.getAverage()));
        holder.tvGrade.setText(s.getGrade());
        holder.tvGrade.setTextColor(getGradeColor(s.getGrade()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("student", s);
            context.startActivity(intent);
        });
    }

    private int getGradeColor(String grade) {
        switch (grade) {
            case "Excellent": return Color.parseColor("#2E7D32");
            case "Good":      return Color.parseColor("#1565C0");
            case "Average":   return Color.parseColor("#E65100");
            default:          return Color.RED;
        }
    }

    @Override
    public int getItemCount() { return students.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAverage, tvGrade;

        ViewHolder(View v) {
            super(v);
            tvName    = v.findViewById(R.id.tvName);
            tvAverage = v.findViewById(R.id.tvAverage);
            tvGrade   = v.findViewById(R.id.tvGrade);
        }
    }
}
