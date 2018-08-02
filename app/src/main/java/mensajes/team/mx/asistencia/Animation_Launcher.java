package mensajes.team.mx.asistencia;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.redbooth.WelcomeCoordinatorLayout;
import com.redbooth.WelcomePageBehavior;

public class Animation_Launcher extends WelcomePageBehavior {

    public static final long DURATION = 10000L;
    private ObjectAnimator objectAnimatorY;
    private ObjectAnimator objectAnimatorX;

    @Override
    protected void onCreate(WelcomeCoordinatorLayout coordinator) {
        objectAnimatorY = ObjectAnimator.ofFloat(getTargetView(), View.TRANSLATION_Y, 0, -getTargetView().getTop() - getTargetView().getHeight());
        objectAnimatorY.setDuration(DURATION);
        objectAnimatorY.setInterpolator(new LinearInterpolator());
        objectAnimatorX = ObjectAnimator.ofFloat(getTargetView(), View.TRANSLATION_X, 0, coordinatorLayout.getWidth());
        objectAnimatorX.setDuration(DURATION);
        objectAnimatorX.setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void onPlaytimeChange(WelcomeCoordinatorLayout coordinator, float newPlaytime, float newScrollPosition) {
        if (newPlaytime < 1) {
            long playTime = (long) (newPlaytime * DURATION);
            objectAnimatorY.setCurrentPlayTime(playTime);
            objectAnimatorX.setCurrentPlayTime(playTime);
        } else {
            objectAnimatorY.setCurrentPlayTime(1);
            objectAnimatorX.setCurrentPlayTime(1);
        }
    }
}
