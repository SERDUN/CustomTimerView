package dmitriiserdun.gmail.com.customtimerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by dmitro on 07.12.17.
 */

public class ProgrresView extends FrameLayout {
    private View rootView;
    TimerView timerView;
    TextView textView;

    public ProgrresView(@NonNull Context context) {
        super(context);
    }

    public ProgrresView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rootView = inflate(context, R.layout.timer_layout_view, this);
        textView = rootView.findViewById(R.id.timeTextView);
        timerView = rootView.findViewById(R.id.custom_progressBar);

    }

    public void setProgress(int progress) {
        timerView.setProgress(progress);
    }

    public void setTextView(int textView) {
        this.textView.setText(String.valueOf(textView));
    }
}
