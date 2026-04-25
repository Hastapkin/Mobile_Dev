package com.example.lab2.exercise3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab2.R;

public class FirstFragment extends Fragment {

    private Button btnSend;
    private TextView txtMessage;

    public interface OnMessageSendListener {
        void onMessageSend(String message);
    }

    private OnMessageSendListener listener;

    public FirstFragment() {}

    public static FirstFragment newInstance(String message) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("message_key", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMessageSendListener) {
            listener = (OnMessageSendListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        txtMessage = view.findViewById(R.id.txtMessage);
        btnSend = view.findViewById(R.id.btnSendData);

        if (getArguments() != null) {
            String message = getArguments().getString("message_key");
            txtMessage.setText(message);
        }

        btnSend.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMessageSend("Message from FirstFragment");
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
