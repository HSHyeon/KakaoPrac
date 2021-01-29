package com.example.practicesns.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicesns.R;

public class BoardFragment extends Fragment {

    private View view;
    private RecyclerView recycle;

    public BoardFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        view = inflater.inflate(R.layout.frag_write, container, false);
        recycle = view.findViewById(R.id.chatlist);

        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });return view;
    }

}
