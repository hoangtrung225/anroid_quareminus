package com.example.chaosbringer.squareminus;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by chaosbringer on 12/5/16.
 */

public class imageTag {

    private int imageIndex;

    private int positionX;
    private int positionY;

    private Bitmap image;
    public imageTag(int i, int x, int y){
        imageIndex = i;

        positionX = x;
        positionY = y;
    }

    public void setImageIndex(int id){
        imageIndex = id;
    }

    public void setX(int x){
        positionX = x;
    }

    public void setY(int y){
        positionY = y;
    }

    public void setImage(Bitmap i){
        image = i;
    }

    public int getImageIndex(){
        return imageIndex;
    }
    public int getX(){
        return positionX;
    }
    public int getY(){
        return positionY;
    }

    public Bitmap getImage(){
        return image;
    }
}
