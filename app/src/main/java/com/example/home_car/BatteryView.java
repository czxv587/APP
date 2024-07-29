package com.example.home_car;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BatteryView extends View {
    private Paint paint;
    private int batteryLevel = 100; // 默认电池电量为100%
    private float cornerRadius = 20.0f;// 圆角半径

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.green1));
    }

    public void setBatteryLevel(int level) {
        batteryLevel = level;
        invalidate(); // 重新绘制View
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int batteryHeight = (height * batteryLevel) / 100;
        RectF rect = new RectF(0, height - batteryHeight, width, height);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
    }
}
