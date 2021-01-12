package com.example.practicesns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    private View login, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        logout=findViewById(R.id.logout);

        Function2<OAuthToken, Throwable, Unit> callback= new Function2<OAuthToken, Throwable, Unit>(){
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken!=null){ //토근이 전달되면 로그인 성공
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LoginActivity.this, NavigationBar.class);
                    startActivity(intent);
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

    }
}