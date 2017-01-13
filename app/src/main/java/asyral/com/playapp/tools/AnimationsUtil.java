package asyral.com.playapp.tools;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by guerra on 11/01/17.
 */

public class AnimationsUtil {

    public static final long ANIMATION_DELAY_SHORT = 20;
    public static final long ANIMATION_DELAY_MEDIUM = 40;
    public static final long ANIMATION_DELAY_LONG = 60;
    public static final long ANIMATION_SHORT = 300;
    public static final long ANIMATION_MEDIUM = 300;
    public static final long ANIMATION_LONG = 900;


    public static ValueAnimator animationColor(final View view, int colorFrom, int colorTo) {
        return animationColor(view, colorFrom, colorTo, ANIMATION_DELAY_SHORT, ANIMATION_MEDIUM);
    }

    public static ValueAnimator animationColor(final View view, int colorFrom, int colorTo, long duration, long delay) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setStartDelay(delay);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((Integer) animator.getAnimatedValue());
            }
        });
        return colorAnimation;
    }

    public static int getHeightScreen(Context context) {
        Integer height = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        return height;
    }

    public static int getwidthScreen(Context context) {
        int width = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        return width;
    }

}