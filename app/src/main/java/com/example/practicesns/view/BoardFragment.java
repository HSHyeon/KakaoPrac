package com.example.practicesns.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.practicesns.R;

public class BoardFragment extends Fragment {

    private RecyclerView recycle;
    BoardAdapter adapter;
    Context context;
    OnTabItemSelectedListener listener;

    public BoardFragment(){

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context=context;

        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(context!=null){
            context=null;
            listener=null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.frag_board, container, false);
        initUI(view);
        return view;
    }
    private void initUI(ViewGroup view){

        recycle=view.findViewById((R.id.chatlist));
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycle.setLayoutManager(layoutManager);

        adapter= new BoardAdapter();
        adapter.addItem(new Board(1,"","게시판 테스트","text1","현"));
        adapter.addItem(new Board(2,"","게시판 테스트","text2","현"));
        adapter.addItem(new Board(3,"","게시판 테스트","text3","현"));
        adapter.addItem(new Board(4,"","게시판 테스트","text4","현"));

        recycle.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnBoardItemClickListener() {
            @Override
            public void onItemClick(BoardAdapter.ViewHolder holder, View view, int position) {
                Board item=adapter.getItem(position);
                Toast.makeText(getContext(),"선택됨"+item.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
