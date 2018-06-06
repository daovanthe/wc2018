package th.wc2018.activity.fragment;


import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class CommonFragment extends Fragment {

    public static final byte SWIPE_RIGHT = 0x01;
    public static final byte SWIPE_LEFT = 0x02;
    public static final byte SWIPE_DOWN = 0x03;
    public static final byte SWIPE_UP = 0x04;
    protected GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureListener());


    final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            swipe(SWIPE_RIGHT);
                        } else {
                            swipe(SWIPE_LEFT);
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        swipe(SWIPE_DOWN);
                    } else {
                        swipe(SWIPE_UP);
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }


    public interface ISwipeListener {
        void swipeTo(byte direction);
    }

    private ISwipeListener mISwipeListener;

    public void setISwipeListener(ISwipeListener pISwipeListener) {
        this.mISwipeListener = pISwipeListener;
    }

    private void swipe(byte direction) {
        if (mISwipeListener == null) return;
        switch (direction) {
            case SWIPE_RIGHT:
                mISwipeListener.swipeTo(SWIPE_RIGHT);
                break;
            case SWIPE_LEFT:
                mISwipeListener.swipeTo(SWIPE_LEFT);
                break;
            case SWIPE_DOWN:
                mISwipeListener.swipeTo(SWIPE_DOWN);
                break;
            case SWIPE_UP:
                mISwipeListener.swipeTo(SWIPE_UP);
                break;
            default:
                break;
        }

    }

}

