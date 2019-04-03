package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Ball {
    float x=20 , y=20, rad = 50;
    private float vX = 0;
    private float vY = 0;
    private float velocity = 5;
    int rand=(int)(Math.random()*5);

float corX = x, corY = y;
    Paint p = new Paint();

    void setVec(MotionEvent event){
         corX = event.getX();
         corY = event.getY();
        float vx = corX-x;
        float vy =corY-y;
        float len = (float)Math.sqrt(vx*vx+vy*vy);
        vX = vx/len;
        vY = vy/len;
    }

    void moveto(){
       /* if(corY>=y-rad&&corY<=y+rad){
            if( corX>=x-rad&&corX<=x+rad){

            }else {  moveX(); }
        } else { moveY(); }   */
       if(!(x+rad>=corX&&y+rad>=corY&&x-rad<=corX&&y-rad<=corY)) {
           moveX();
           moveY();
       }

    }
    void moveX(){
        x += vX*velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void moveY(){
        y += vY*velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x,y,rad,paint);
    }
}