package com.cad.user.technoshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity {
    Animation animFadein,animFadeOut,animBounce,animSlideUp,animSlideDown,animZoomIn,animZoomOut;
    ImageView logo;
    TextView presents;
    ImageView cadlogo;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeOut=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        animZoomIn=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        animZoomOut=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        animBounce=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        animSlideDown=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        animSlideUp=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        logo=(ImageView)findViewById(R.id.splashscreen);
        cadlogo=(ImageView)findViewById(R.id.cadlogo);
        presents=(TextView)findViewById(R.id.presents);
        cadlogo.setVisibility(View.VISIBLE);
        //presents.setVisibility(View.VISIBLE);
        cadlogo.setAnimation(animFadein);
        presents.setAnimation(animFadein);
        logo.setAnimation(animBounce);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1200);
    }
}