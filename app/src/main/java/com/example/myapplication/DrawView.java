package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    final SurfaceHolder surfaceHolder = getHolder();
    private volatile boolean running = true;

    DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    int width = displaymetrics.widthPixels;
    int height = displaymetrics.heightPixels;



    Map map = new Map(width, height);
    Ball ball =  new Ball();
    int a =0;
    Paint p = new Paint();
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            canvas.drawColor(Color.BLACK);
                            if(a==0){
                                map.generate(10);
                                a++;


                            }
                            p.setColor(Color.YELLOW);
                            map.drawMap(canvas);
                           // ball.moveto();
                            //ball.onDraw(canvas);
                        } finally {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    //DrawView j; MainActivity m;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      // moveRoom((int)(ball.x-event.getX()), (int)(ball.y-event.getY()));
        ball.setVec(event);
        return super.onTouchEvent(event);
    }
    void moveRoom(int moveX, int moveY){
        for(Room r:map.map_room){
            r.x+=moveX;
            r.y += moveY;
        }
    }

}