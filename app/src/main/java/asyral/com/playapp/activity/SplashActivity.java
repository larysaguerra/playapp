package asyral.com.playapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.mikepenz.iconics.view.IconicsImageView;


import java.io.IOException;

import asyral.com.playapp.R;
import asyral.com.playapp.tools.ActivityUtil;
import asyral.com.playapp.tools.AnimationsUtil;

/**
 * Created by guerra on 10/01/17.
 * Splash App
 */

public class SplashActivity extends ActivityUtil {

    private RelativeLayout background;
    private IconicsImageView icon;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getResources().getBoolean(R.bool.is_tablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        findViews();
        setActions();
    }

    private void findViews() {
        background = (RelativeLayout) findViewById(R.id.background_splash);
        icon = (IconicsImageView) findViewById(R.id.icon_splash);
        text = (TextView) findViewById(R.id.text_name);
    }

    private void setActions() {
        int colorFrom = ContextCompat.getColor(this, R.color.colorAccent);
        int colorTo = ContextCompat.getColor(this, R.color.colorPrimary);
        int colorToWhite = ContextCompat.getColor(this, R.color.colorWhite);

        ViewAnimator
                .animate(background).backgroundColor(colorFrom, colorTo)
                .duration(AnimationsUtil.ANIMATION_MEDIUM)
                //.andAnimate(text).textColor(colorFrom, colorToWhite)
                //.duration(AnimationsUtil.ANIMATION_MEDIUM)
                .andAnimate(icon).bounceIn().interpolator(new BounceInterpolator())
                .duration(AnimationsUtil.ANIMATION_LONG).accelerate().startDelay(AnimationsUtil.ANIMATION_DELAY_SHORT)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        MediaPlayer myMediaPlayer = MediaPlayer.create(SplashActivity.this, R.raw.ball_bounce_popup);
                        if (myMediaPlayer != null) {
                            myMediaPlayer.start();
                        } else {
                            myMediaPlayer.reset();
                            try {
                                myMediaPlayer.prepare();
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            myMediaPlayer.start();
                        }
                    }
                })
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);
                        /*ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this,
                                Pair.create((View) text, getString(R.string.transition_name)));
                        ActivityCompat.startActivity(SplashActivity.this, intent, options.toBundle());*/
                        startActivity(intent);
                        finish();
                    }
                })
                .start();
    }
}
