package com.example.home_car.DataGraph;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.home_car.R;

public class
WaterView extends View {
    private Paint paint;
    private int batteryLevel = 50; // 默认电池电量为100%

    public WaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.blue));
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

        canvas.drawRect(0, height - batteryHeight, width, height, paint);
    }
}
