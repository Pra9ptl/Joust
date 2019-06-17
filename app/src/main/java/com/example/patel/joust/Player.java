package com.example.patel.joust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {
    private int xPosition;
    private int yPosition;


    // image
    Bitmap image;

    // make the hitbox
    Rect hitboxBottom;
    Rect hitboxTop;


    // vector variables
    double xn = 0;
    double yn = 0;




    public Player(Context context, int x, int y, int imageName) {
        this.xPosition = x;
        this.yPosition = y;

        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.pikachu);

        this.hitboxBottom = new Rect(
                this.xPosition + 80,
                this.yPosition + this.image.getHeight() - 50,
                this.xPosition + this.image.getWidth() - 80,
                this.yPosition + this.image.getHeight()


        );

        this.hitboxTop = new Rect(
                this.xPosition + 80,
                this.yPosition + this.image.getHeight() - 180,
                this.xPosition + this.image.getWidth() - 80,
                this.yPosition + this.image.getHeight() -200
        );

    }


// ---------------------------------
    // sets or gets the xd variable for this sprite
    // ---------------------------------

    public double getXn() {
        return xn;
    }

    public void setXn(double xn) {
        this.xn = xn;
    }

    public double getYn() {
        return yn;
    }

    public void setYn(double yn) {
        this.yn = yn;
    }


    // ---------------------------------
    // gets, sets, or updates the hitbox
    // ---------------------------------

    public Rect getHitboxBottom() {
        return hitboxBottom;
    }

    public void setHitboxBottom(Rect hitbox) {
        this.hitboxBottom = hitbox;
    }

    public void updateHitboxBottom() {
        this.hitboxBottom.left = this.xPosition + 80;
        this.hitboxBottom.top = this.yPosition + this.image.getHeight() - 50;
        this.hitboxBottom.right = this.xPosition + this.image.getWidth() - 80 ;
        this.hitboxBottom.bottom = this.yPosition + this.image.getHeight();
    }

    public Rect getHitboxTop() {
        return hitboxTop;
    }

    public void setHitboxTop(Rect hitboxTop) {
        this.hitboxTop = hitboxTop;
    }

    public void updateHitBoxTop() {
        this.hitboxTop.left = this.xPosition + 80;
        this.hitboxTop.top = this.yPosition + this.image.getHeight() - 180;
        this.hitboxTop.right = this.xPosition + this.image.getWidth() - 80;
        this.hitboxTop.bottom = this.yPosition + this.image.getHeight() - 200;
    }
    // ---------------------------------
    // gets or sets the (x,y) position of the sprite
    // ---------------------------------
    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    // ---------------------------------
    // gets or sets the image sprite
    // ---------------------------------
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}