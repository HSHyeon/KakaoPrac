package com.example.practicesns.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.practicesns.R;
import com.example.practicesns.view.NavigationBar;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="LoginActivity";
    private static String Nick=null;
    private static String ProImg=null;
    private View login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);

        autoLogin();

        Function2<OAuthToken, Throwable, Unit> callback= new Function2<OAuthToken, Throwable, Unit>(){
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken!=null){ //토근이 전달되면 로그인 성공
                    updateKakaoLoginUi();
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                     //   finish();
                }
                if(throwable!=null){ //오류가 있다면
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
               // updateKakaoLoginUi();
                return null;
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    LoginClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback); //카카오앱으로 로그인
                }else{
                    LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback ); //카카오 웹에서 계정으로 로그인
                }
            }
        });

        /*logout.setOnClickListener(new View.OnClickListener() {
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
        });

        updateKakaoLoginUi();
    }*/

    }    private void updateKakaoLoginUi(){
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null){
                    Log.i(TAG,"invoke: id="+user.getId());
                    Log.i(TAG,"invoke: email="+user.getKakaoAccount().getEmail());
                    Log.i(TAG,"invoke: nickname="+user.getKakaoAccount().getProfile().getNickname());

                    Nick=user.getKakaoAccount().getProfile().getNickname();
                    ProImg=user.getKakaoAccount().getProfile().getProfileImageUrl();

                    Intent intent= new Intent(LoginActivity.this, NavigationBar.class);
                    Log.i(TAG,"invoke: id="+Nick);
                    Log.i(TAG,"invoke: img="+ProImg);
                    intent.putExtra("proimg",ProImg);
                    intent.putExtra("nick",Nick);

                    startActivity(intent);
                }else{

                }
                if(throwable!=null){
                  /*  ActivityCompat.finishAffinity(ProfileFragment);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().remove(ProfileFragment.this).commit();
                    fragmentManager.popBackStack();
                    profileImg.setImageBitmap(null);
                    nickName.setText(null);
                    Log.w(TAG,"invoke:"+throwable.getLocalizedMessage());*/
                }
                return null;
            }
        });
    }
    private void autoLogin(){
        if(Nick!=null){
            Intent intent= new Intent(LoginActivity.this, NavigationBar.class);

            intent.putExtra("proimg",ProImg);
            intent.putExtra("nick",Nick);

            startActivity(intent);
        }
    }
}