package com.example.application.drawcircles;

import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by sreerag on 23-02-2017.
 */

public class Circle {

    private PointF center;
    private boolean toMove = false;
    private boolean inMotion = false;
    private double velocityX;
    private double velocityY;
    private double radius;

    public boolean isInMotion() {
        return inMotion;
    }

    public void setInMotion(boolean inMotion) {
        this.inMotion = inMotion;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isToMove() {
        return toMove;
    }

    public void setToMove(boolean toMove) {
        this.toMove = toMove;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private Paint paint;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(PointF startPoint){
        center = startPoint;
    }


    public PointF getCenter() {
        return center;
    }
}