package text.bwei.com.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by dell on 2018/1/30.
 */

public class SquareRelativeLayout extends android.support.v7.widget.AppCompatImageView {

    private float pointX=50f;
    private float pointY=50f;
    private boolean moveable;
    private float mRadius = 50f;

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        pointX = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec((int)pointX, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指触摸的x坐标
        float touchX;
        //手指触摸的y坐标
        float touchY;
        //event.getAction()判断事件的类型
        switch (event.getAction()) {
            //按下的事件
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                if (touchX > pointX - mRadius && touchX < pointX + mRadius && touchY > pointY - mRadius && touchY < pointY + mRadius) {
                    moveable = true;
                    Toast.makeText(getContext(), "我按下了", Toast.LENGTH_LONG).show();

                } else {
                    moveable = false;
                }
                break;
            //移动的事件
            case MotionEvent.ACTION_MOVE:
                if (moveable) {
                    //重新设置一下圆心的位置， 把我们圆心的位置（pointX,pointY)设置成
                    // 当前触摸的位置（event.getX()，event.getY()）
                    pointX = event.getX();
                    pointY = event.getY();

                    //去重新绘制， 会重新走onDraw()方法
                    invalidate();
                    postInvalidate();
                }
                break;
//                抬起的事件
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}