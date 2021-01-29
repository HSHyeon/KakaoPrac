package com.example.practicesns.view.login;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application { //public 안써서 에러났었음
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "ed1b5ce365b6aac2e125fd4f168862fb"); //네이티브 앱 키
    }
}