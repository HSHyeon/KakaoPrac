package com.example.practicesns.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.practicesns.R;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    private Button logout;
    private TextView nickName;
    private ImageView profileImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_profile);


        logout=findViewById(R.id.logout);
        nickName=findViewById(R.id.nickname);
        profileImg=findViewById(R.id.profile);
    //    updateKakaoLoginUi();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                       // updateKakaoLoginUi();
                        finish();
                        return null;
                    }
                });
            }
        });
}
/*
    private void updateKakaoLoginUi(){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null){
                    Log.i(TAG,"invoke: id="+user.getId());
                    Log.i(TAG,"invoke: email="+user.getKakaoAccount().getEmail());
                    Log.i(TAG,"invoke: nickname="+user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG,"invoke: gender="+user.getKakaoAccount().getGender());
                    Log.i(TAG,"invoke: age="+user.getKakaoAccount().getAgeRange());

                    Glide.with(profileImg).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImg);
                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
                    logout.setVisibility(View.VISIBLE);

                }else{
                    logout.setVisibility(View.GONE);
                }
                if(throwable!=null){
                    Log.w(TAG,"invoke:"+throwable.getLocalizedMessage());
                }
                return null;
            }
        });
    }*/

}