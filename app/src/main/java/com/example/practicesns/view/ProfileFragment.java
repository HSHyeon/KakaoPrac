package com.example.practicesns.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.practicesns.R;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

import static android.content.Context.MODE_PRIVATE;
import static com.example.practicesns.view.NavigationBar.Nick;

public class ProfileFragment extends Fragment {
    private static final String TAG="ProfileFragment";

    private Button logout;
    private TextView nickName;
    private ImageView profileImg;

    ViewGroup viewGroup;


    public ProfileFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_profile,container,false);
        logout = viewGroup.findViewById(R.id.logout);
        nickName = viewGroup.findViewById(R.id.nickname);
        profileImg = viewGroup.findViewById(R.id.profile);

        Bundle bundle = getArguments(); //번들 안의 텍스트 불러오기
        String Nick = bundle.getString("nick");
        String ProImg = bundle.getString("proimg");
        //updateKakaoLoginUi();
        Log.i(TAG,"invoke: id="+Nick);

        Glide.with(profileImg).load(ProImg).circleCrop().into(profileImg);
        nickName.setText(Nick);
/*

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        nickName.setText(NavigationBar.Nick);
                        //updateKakaoLoginUi();
                        return null;
                    }
                });
                //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.

            }
        });*/  return viewGroup;
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
                    //
                    //
                }else{
                    logout.setVisibility(View.GONE);
                }
                if(throwable!=null){
                     ActivityCompat.finishAffinity(ProfileFragment);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().remove(ProfileFragment.this).commit();
                    fragmentManager.popBackStack();
                    profileImg.setImageBitmap(null);
                    nickName.setText(null);
                    Log.w(TAG,"invoke:"+throwable.getLocalizedMessage());
                }
                return null;
            }
        });
    }*/
}
