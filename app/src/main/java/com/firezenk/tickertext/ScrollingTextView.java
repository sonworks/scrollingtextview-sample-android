package com.firezenk.tickertext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import android.widget.TextView;

public class ScrollingTextView extends TextView implements Runnable {
	private static final float DEFAULT_SPEED = 15.0f;

    private Scroller scroller;
    private float speed = DEFAULT_SPEED;
    private boolean continuousScrolling = true;
    private byte[] b;
    
	public ScrollingTextView(Context context) {
		super(context);
		setup(context);
	}
    
	public ScrollingTextView(Context context, byte[] b) {
		super(context);
		setup(context);
		this.b = b;
	}
	
	public ScrollingTextView(Context context, AttributeSet attributes) {
        super(context, attributes);
        setup(context);
    }

    private void setup(Context context) {
        scroller = new Scroller(context, new LinearInterpolator());
        setScroller(scroller);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    	super.onLayout(changed, left, top, right, bottom);
    	if (scroller.isFinished()) {
    		scrollH();
        }
    }
    
    private void scrollV() {
        int viewHeight = getHeight();
        int visibleHeight = viewHeight - getPaddingBottom() - getPaddingTop();
        int lineHeight = getLineHeight();

        int offset = -1 * visibleHeight;
        int distance = visibleHeight + getLineCount() * lineHeight;
        int duration = (int) (distance * speed);

        scroller.startScroll(0, offset, 0, distance, duration);

        if (continuousScrolling) {
            post(this);
        }
    }
    
    private void scrollH() {
    	int viewWidth = getWidth();
    	int visibleWidth = viewWidth - getPaddingRight() - getPaddingLeft();
    	int lineWidth =  getText().toString().getBytes().length * 10;//length()*14;//

        int offset = -1 * visibleWidth;
        int distance = visibleWidth + getLineCount() * lineWidth;
        int duration = (int) (distance * speed);
    	
        scroller.startScroll(offset, 0, distance, 0, duration);

        if (continuousScrolling) {
            post(this);
        }
    }

	@Override
	public void run() {
		if (scroller.isFinished()) {
			scrollH();
        } else {
            post(this);
        }
	}
	
	public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setContinuousScrolling(boolean continuousScrolling) {
        this.continuousScrolling = continuousScrolling;
    }

    public boolean isContinuousScrolling() {
        return continuousScrolling;
    }
}
