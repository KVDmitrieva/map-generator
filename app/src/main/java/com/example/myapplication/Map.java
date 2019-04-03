package com.example.myapplication;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;

public class Map {

    int width, height;
    int data[];
    List<Room> map_room = new ArrayList();
    Map(int width, int height){
        this.width = width;
        this.height = height;
        /*data = new int[width*height];
        for(int i =0; i<width; i++){
            data[i]=0;
        }*/

    }

    void generate(int roomCount){
        map_room.clear();
        for(int i = 0; i<roomCount; i++){
            for(int j = 0; j<10; j++){
                int w = 100 + (int)(Math.random()*width)/3;
                int h = 100 + (int)(Math.random()*height)/3;
                Room room = new Room((int)(Math.random()*Math.random()*width),
                        (int)(Math.random()*Math.random()*height),w,h);
                boolean nextTo = false;
                for(Room r : map_room){
                    if(!room.intersect(r)) {
                        nextTo = true;
                        break;
                    }
                }
                if(!nextTo) {map_room.add(room); break;}
                /*for(Room r: map_room){
                    for(int x = 0; x<r.width; x++){
                        for(int y = 0; y<r.height; y++){
                            data[(r.x + x) + (r.y + y) * width]=1;
                        }
                    }
                }
                */
            }
        }
    }
    void drawMap(Canvas canvas){
        Paint p =new Paint();
        p.setColor(Color.GRAY);
        for(Room r:map_room){
            canvas.drawRect(r.x, r.y, r.x+r.width, r.height+r.y, p);
        }
    }
}
