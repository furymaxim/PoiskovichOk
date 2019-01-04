package com.example.user.poiskovichok;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;


import com.deange.ropeprogressview.RopeProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    private RopeProgressBar mRopeProgressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        mRopeProgressBar = findRopeProgressView(getWindow().getDecorView()); // equals findViewById


        new Thread(){

            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 4;

                    handler.post(new Runnable() {
                        public void run() {
                            mRopeProgressBar.animateProgress(progressStatus);
                        }

                    });


                }

                try{
                    Thread.sleep(2450);


                }catch (InterruptedException e){
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    public void run () {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

            }





        }.start();



    }





    private RopeProgressBar findRopeProgressView(final View view) {
        if (view instanceof RopeProgressBar) {
            return (RopeProgressBar) view;

        } else if (view instanceof ViewGroup) {
            ViewGroup p = ((ViewGroup) view);
            View child;
            for (int i = 0; i < p.getChildCount(); i++) {
                if ((child = findRopeProgressView(p.getChildAt(i))) != null) {
                    return (RopeProgressBar) child;
                }
            }
        }

        return null;
    }




}

