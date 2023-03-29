package com.SAURABH.pizza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread= new Thread(){
            public  void  run(){
                try{
                    sleep(3500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent= new Intent(SplashActivity.this,registerActivity.class);
                    startActivity(intent);
                }
            }
        };thread.start();

    }
}