package basti.coryphaei.com.customtopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Bowen on 2015/10/15.
 */
public class TopBar extends RelativeLayout{

    private Button leftButton,rightButton;
    private TextView titleTextView;

    private LayoutParams mLeftParams,mRightParams,mTitleParams;

    private TopBarClickListener mTopbarClickListener;

    //属性
    private String leftString,rightString,titleString;
    private Drawable leftBackground,rightBackground,titleBackground;
    private float leftSize,rightSize,titleSize;
    private int leftColor,rightColor,titleColor;

    public TopBar(Context context) {
        this(context,null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化属性
        initAttr(context, attrs);
        //初始化控件
        initView(context);
        initDates();
        //暴露给外部的接口
        initEvent();
    }

    private void initDates() {

        leftButton.setText(leftString);
        leftButton.setTextColor(leftColor);
        leftButton.setBackground(leftBackground);
        leftButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftSize);

        rightButton.setText(rightString);
        rightButton.setTextColor(rightColor);
        rightButton.setBackground(rightBackground);
        rightButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightSize);

        titleTextView.setText(titleString);
        titleTextView.setTextColor(titleColor);
        titleTextView.setBackground(titleBackground);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        titleTextView.setGravity(Gravity.CENTER);

    }

    private void initEvent() {

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopbarClickListener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopbarClickListener.rightClick();
            }
        });

    }

    private void initView(Context context) {

        leftButton = new Button(context);
        rightButton = new Button(context);
        titleTextView = new TextView(context);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleTextView, mTitleParams);

    }

    private void initAttr(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.topbar);

        leftString = ta.getString(R.styleable.topbar_leftButtonText);
        rightString = ta.getString(R.styleable.topbar_rightButtonText);
        titleString = ta.getString(R.styleable.topbar_titleText);

        leftBackground = ta.getDrawable(R.styleable.topbar_leftButtonBackground);
        rightBackground = ta.getDrawable(R.styleable.topbar_rightButtonBackground);
        titleBackground = ta.getDrawable(R.styleable.topbar_titleBackground);

        leftSize = ta.getFloat(R.styleable.topbar_leftButtonTextSize,16);
        rightSize = ta.getFloat(R.styleable.topbar_rightButtonTextSize, 16);
        titleSize = ta.getFloat(R.styleable.topbar_titleTextSize,20);

        leftColor = ta.getColor(R.styleable.topbar_leftButtonTextColor, getResources().getColor(R.color.default_textcolor));
        rightColor = ta.getColor(R.styleable.topbar_leftButtonTextColor, getResources().getColor(R.color.default_textcolor));
        titleColor = ta.getColor(R.styleable.topbar_leftButtonTextColor, getResources().getColor(R.color.default_textcolor));

        ta.recycle();
    }

    public void setTopBarClickListener(TopBarClickListener topBarClickListener){
        mTopbarClickListener = topBarClickListener;
    }

    //设置透明度
    public void setTextAlpha(float alpha){
        leftButton.setAlpha(alpha);
        rightButton.setAlpha(alpha);
        titleTextView.setAlpha(alpha);
    }

    public void setItemVisible(int id,boolean isVisible){
        switch (id){
            case 0:
                if (isVisible)
                    leftButton.setVisibility(View.VISIBLE);
                else
                    leftButton.setVisibility(View.INVISIBLE);
                break;
            case 1:
                if (isVisible)
                    rightButton.setVisibility(View.VISIBLE);
                else
                    rightButton.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void setTypeFace(Typeface tf){
        leftButton.setTypeface(tf);
        rightButton.setTypeface(tf);
        titleTextView.setTypeface(tf);
    }
}
