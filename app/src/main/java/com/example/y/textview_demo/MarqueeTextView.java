package com.example.y.textview_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView implements Runnable{
    private int currentScrollX;
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isMeasure) {
            getTextWidht();
            isMeasure = true;
        }
    }

    private void getTextWidht() {
        Paint paint = new Paint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
    }

    @Override
    public void run() {
        currentScrollX += 2;
        scrollTo(currentScrollX,0);
        if (isStop)
            return;
        if (getScrollX() <= -(this.getWidth())) {
            scrollTo(textWidth,0);
            currentScrollX = textWidth;
        }
        postDelayed(this,5);
    }

    public void startScroll(){
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    public void stopScroll(){
        isStop = true;
    }

    public void startFor(){
        currentScrollX = 0;
        startScroll();
    }
}
