package com.example.y.textview_demo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartBtn;
    private Button mPauseBtn;
    private Button mRestartBtn;
    private MarqueeTextView mTextView;
    private TextView mSpannableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text);
        mStartBtn = findViewById(R.id.start_btn);
        mPauseBtn = findViewById(R.id.pause_btn);
        mRestartBtn = findViewById(R.id.restart_btn);
        mSpannableTextView = findViewById(R.id.spannable_text);

        SpannableString span = new SpannableString("红色可点击斜体删除线下划线");
        //设置字体颜色
        span.setSpan(new ForegroundColorSpan(Color.RED),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置可点击
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "被点了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //默认的字体颜色时红色  在这里设置成黑色
                ds.setColor(Color.BLACK);
                //默认的有下划线  在这里去掉
                ds.setUnderlineText(false);
            }
        },2,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置斜体
        span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),5,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置删除线
        span.setSpan(new StrikethroughSpan(),7,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置下划线
        span.setSpan(new UnderlineSpan(),10,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //加入这句代码  点击事件才能生效
        mSpannableTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mSpannableTextView.setText(span);
        //text view设置高亮颜色为透明 不设置的话 点击后会有浅绿色的背景  贼难看
        mSpannableTextView.setHighlightColor(Color.TRANSPARENT);
        mStartBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
        mRestartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                mTextView.startScroll();
                break;
            case R.id.pause_btn:
                mTextView.stopScroll();
                break;
            case R.id.restart_btn:
                mTextView.startFor();
                break;
        }
    }
}
