package text.bwei.com.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by dell on 2018/1/30.
 */

public class Basellview extends View {
    //画笔
    private Paint mPaint;
    //圆的半径
    private float mRadius = 50f;
    //圆的圆心的x坐标
    private float pointX = mRadius;
    //圆的圆心的Y坐标
    private float pointY = mRadius;

    //控制是否可以移动的变量 true的时候可以移动
    private boolean moveable;

    public Basellview(Context context) {
        super(context);
    }

    //自定义veiw在布局中使用，必须实现的一个构造器
    public Basellview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //构造一个paint
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //设置一个抗锯齿
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据圆心的坐标来绘制圆的位置的，而圆心的坐标，我们触摸屏幕的时候被我们修改了
        canvas.drawCircle(pointX, pointY, mRadius, mPaint);
//        canvas.drawRect(60, 60, 80, 80, mPaint);// 正方形
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
                }
                break;
            //抬起的事件
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}