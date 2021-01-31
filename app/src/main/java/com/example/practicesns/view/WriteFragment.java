package com.example.practicesns.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicesns.R;

import static android.app.Activity.RESULT_OK;

public class WriteFragment extends Fragment {
    private static final String TAG="WriteFragment";
    OnTabItemSelectedListener listener;
    ImageView imv;
    private final int GET_GALLERY_IMAGE = 200;

    public WriteFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_write, container, false);
        initUI(view);



      return view;
    }
    public void initUI(ViewGroup view){
        imv=view.findViewById(R.id.imageView);
        imv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        Button savebtn=view.findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){listener.onTabSelected(0);
            }}
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            Log.e(TAG,selectedImageUri.toString());
            imv.setImageURI(selectedImageUri);

        }

    }
    public void setDateString(String dateString){

    }

}
