package com.example.pawel.simpleoffice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Pawel on 23.10.2017.
 */

public class DrawView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    public Button CleanScreenButton;
    public ViewGroup.LayoutParams params;


    public DrawView(Context context) {
        super(context);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(150f);

        CleanScreenButton = new Button(context);
        CleanScreenButton.setText("Clean Screen");
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        CleanScreenButton.setLayoutParams(params);
        CleanScreenButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                path.reset();
                postInvalidate();

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}

