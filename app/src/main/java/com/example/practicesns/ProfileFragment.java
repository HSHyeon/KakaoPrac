package com.example.practicesns;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class ProfileFragment extends Fragment {
    private static final String TAG="MainActivity";

    private View view;
    private Button logout;
    private TextView nickName;
    private ImageView profileImg;
    public ProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);
        logout = view.findViewById(R.id.logout);
        nickName = view.findViewById(R.id.nickname);
        profileImg = view.findViewById(R.id.profile);
        updateKakaoLoginUi();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });

            }
        });return view;
    }

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
    }
}
