package dmitriiserdun.gmail.com.customtimerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dmitro on 07.12.17.
 */

public class TimerView extends View {
    private static final float MARGIN = 10;
    private Paint mPaint;

    final RectF oval = new RectF();
    final RectF pointOval = new RectF();


    private float strokeWidth = 4;
    private float progress = 25;
    private int min = 0;
    private int max = 100;
    private int color = Color.DKGRAY;

    private Paint backgroundPaint;
    private Paint foregroundPaint;

    int height;
    int width;

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleProgressBar,
                0, 0);
        try {
            strokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_progressBarThickness, strokeWidth);
            progress = typedArray.getFloat(R.styleable.CircleProgressBar_progress, progress);
            color = typedArray.getInt(R.styleable.CircleProgressBar_progressbarColor, color);
            min = typedArray.getInt(R.styleable.CircleProgressBar_min, min);
            max = typedArray.getInt(R.styleable.CircleProgressBar_max, max);
        } finally {
            typedArray.recycle();
        }

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(adjustAlpha(color, 0.3f));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(color);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        foregroundPaint.setAntiAlias(true);
        foregroundPaint.setDither(true);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeJoin(Paint.Join.ROUND);
        foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
        foregroundPaint.setStrokeWidth(12);


        float angle = 360 * progress / max;
        int grad = (int) angle;
        canvas.drawArc(oval, 270 + grad, 360 - grad, false, foregroundPaint);
        canvas.drawOval(pointOval, foregroundPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        oval.set((0 + strokeWidth / 2)+MARGIN, (0 + strokeWidth / 2)+MARGIN, (min - strokeWidth / 2)-MARGIN, (min - strokeWidth / 2)-MARGIN);


        float x=width/2-10;
        float y=3;
        float radius=0.5f;

      //  pointOval.set(60+MARGIN, 3+y+MARGIN, 62+MARGIN, 5+y+MARGIN);
        pointOval.set(x+MARGIN, y+MARGIN, x+radius+MARGIN, y+radius+MARGIN);



    }


    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

}
