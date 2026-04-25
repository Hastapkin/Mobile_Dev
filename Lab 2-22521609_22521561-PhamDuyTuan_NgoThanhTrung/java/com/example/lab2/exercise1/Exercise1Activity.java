package com.example.lab2.exercise1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;

import java.util.ArrayList;
import java.util.List;

public class Exercise1Activity extends AppCompatActivity {

    private EditText edtTask;
    private Button btnAdd;
    private RecyclerView recyclerTasks;

    private List<Task> taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        edtTask = findViewById(R.id.edtTask);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerTasks = findViewById(R.id.recyclerTasks);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList);

        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerTasks.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String taskTitle = edtTask.getText().toString().trim();
            if (taskTitle.isEmpty()) {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
                return;
            }
            taskList.add(new Task(taskTitle));
            adapter.notifyItemInserted(taskList.size() - 1);
            edtTask.setText("");
        });
    }
}
