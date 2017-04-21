package com.example.application.drawcircles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sreerag on 23-02-2017.
 */

public class SimpleDrawing extends View {

    private String mode = "insert";
    private int color = Color.BLACK;
    private float screenHeight;
    private float screenWidth;
    private static final String TAG = "DrawingApp";
    private PointF currentCenter;
    private Circle selectedCircle;
    private Circle currentCircle;
    private List<Circle> circleList = new ArrayList<>();
    private Paint circleColor;
    private VelocityTracker velocity = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public SimpleDrawing(Context context) {
        super(context, null);
    }

    public SimpleDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentCenter = new PointF(event.getX(), event.getY());
        if(getMode().equalsIgnoreCase("insert")){
            insert(event);
            invalidate();
        }

        if(getMode().equalsIgnoreCase("delete")) {
            Log.i(TAG, "It is delete");
            delete();
            invalidate();
        }
        if(getMode().equalsIgnoreCase("move")) {
            Log.i(TAG, "It is move");
            move(event);
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG,"test");
        canvas.drawColor(0xfff8efe0);
        for (Circle c : circleList)
            canvas.drawCircle(c.getCenter().x,c.getCenter().y,(float)c.getRadius(),c.getPaint());
        if(getMode().equalsIgnoreCase("move")){
            moveHandler();
            invalidate();
        }
    }

    public void insert(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                circleColor = new Paint();
                circleColor.setStyle(Paint.Style.STROKE);
                circleColor.setColor(getColor());
                circleColor.setStrokeWidth(5f);
                currentCircle = new Circle(currentCenter);
                currentCircle.setRadius(60);
                currentCircle.setPaint(circleColor);
                if(!(xIsOutofBound(currentCircle.getCenter().x,currentCircle.getRadius()) ||
                        yIsOutofBound(currentCircle.getCenter().y,currentCircle.getRadius()))) {
                    circleList.add(currentCircle);
                    selectedCircle = currentCircle;
                    Log.i("log",(String.valueOf(selectedCircle.getCenter().x)));
                }
                else
                Toast.makeText(getContext(), "A circle at selected point will be out of bound",
                        Toast.LENGTH_SHORT).show();

                break;
            case MotionEvent.ACTION_MOVE:
                if(!(xIsOutofBound(selectedCircle.getCenter().x,selectedCircle.getRadius()+2f) ||
                        yIsOutofBound(selectedCircle.getCenter().y,selectedCircle.getRadius()+2f)))
                    selectedCircle.setRadius(selectedCircle.getRadius()+2f);
                else
                    selectedCircle.setRadius(selectedCircle.getRadius()-2f);
                break;
        }
    }

    public void move(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (velocity == null)
                velocity = VelocityTracker.obtain();
                else
                velocity.clear();
                velocity.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,"Move and action move");
                velocity.addMovement(event);
                velocity.computeCurrentVelocity(5);
                for(Circle c : circleList){
                    if(isPointInside(c.getCenter().x,c.getCenter().y,c.getRadius())){
                        c.setToMove(true);
                        c.setVelocityX(velocity.getXVelocity());
                        c.setVelocityY(velocity.getYVelocity());
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                moveHandler();
                invalidate();
                Log.i(TAG,String.valueOf(velocity.getXVelocity()));
                Log.i(TAG,String.valueOf(velocity.getYVelocity()));
                break;
            case MotionEvent.ACTION_CANCEL:
                velocity.recycle();
                velocity=null;
                break;
        }
    }

    public void moveHandler() {
        for (Circle c : circleList) {
            if (c.isToMove() || c.isInMotion()) {
                if (c.isToMove()) {
                    c.setInMotion(true);
                    c.setToMove(false);
                }
                if (xIsOutofBound(c.getCenter().x+c.getVelocityX(),c.getRadius())) {
                    c.setVelocityX(c.getVelocityX() * -0.6);
                    c.getCenter().x += c.getVelocityX();
                } else
                    c.getCenter().x += c.getVelocityX();
                if (yIsOutofBound(c.getCenter().y+c.getVelocityY(),c.getRadius())) {
                    c.setVelocityY(c.getVelocityY() * -0.6);
                    c.getCenter().y += c.getVelocityY();
                } else
                    c.getCenter().y += c.getVelocityY();
            }
        }
    }

    public boolean xIsOutofBound(double x, double radius){
        if(x - radius < 0)
            return true;
        if(x + radius > screenWidth)
            return true;
        return false;
    }

    public boolean yIsOutofBound(double y, double radius){
        if (y - radius < 0)
            return true;
        if(y + radius + 160 > screenHeight)
            return true;
        return false;

    }

    public boolean isPointInside(float circleX, float circleY,double radius){
        float touchX = currentCenter.x, touchY = currentCenter.y;
        float x = Math.abs(touchX-circleX);
        float y = Math.abs(touchY-circleY);
        double distance = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        return distance <= radius;
    }

    public void delete(){
        List<Circle> deleteList = new ArrayList<>();
        for(Circle c : circleList){
            if(isPointInside(c.getCenter().x,c.getCenter().y,c.getRadius())){
                deleteList.add(c);
            }
        }
         for(Circle c : deleteList){
             if(circleList.contains(c))
                 circleList.remove(c);
         }
    }
}