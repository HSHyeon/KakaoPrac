package com.example.practicesns.view;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicesns.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> implements OnBoardItemClickListener {
    ArrayList<Board> items= new ArrayList<>();

    OnBoardItemClickListener listener;


    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View itemView= inflater.inflate(R.layout.board_item2, viewGroup,false);

        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        Board item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(Board item){
        items.add(item);
    }
    public void setItems(ArrayList<Board> items){
        this.items=items;
    }
    public Board getItem(int position){
        return items.get(position);
    }
    public void setOnItemClickListener(OnBoardItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener!=null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layout;
        ImageView imv;
        TextView tv_title;
        TextView tv_date;
        TextView tv_host;

        public ViewHolder(View itemView, final OnBoardItemClickListener listener){
            super(itemView);

            layout=itemView.findViewById(R.id.background);
            imv=itemView.findViewById(R.id.iv_profile_image);
            tv_title=itemView.findViewById(R.id.tv_title);
         //   tv_date=itemView.findViewById(R.id.tv_status);
         //   tv_host=itemView.findViewById(R.id.tv_host);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(listener!=null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }

                }
            });

        }
        public void setItem(Board item){
            String title=item.getTitle();
            String imgURL=item.getImgURL();
            String host=item.getHost();
            imv.setImageResource(R.drawable.broke_image);

            if(imgURL!=null && !imgURL.equals("")){
             //   imv.setImageURI(Uri.parse("file://"+imgURL));
            }else{
             //   imv.setVisibility(View.GONE);

            }
            tv_title.setText(title);
            //tv_host.setText(host);
            //imv.setImageURI(Uri.parse(item.getImgURL()));
        }

    }
}
