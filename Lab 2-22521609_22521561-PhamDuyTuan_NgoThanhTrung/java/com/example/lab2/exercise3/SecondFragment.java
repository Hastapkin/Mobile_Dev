package com.example.lab2.exercise3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab2.R;

public class SecondFragment extends Fragment {

    public SecondFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        TextView txtData = view.findViewById(R.id.txtData);

        if (getArguments() != null) {
            String data = getArguments().getString("data");
            if (data != null) {
                txtData.setText(data);
            }
        }

        return view;
    }
}
