package com.example.patel.joust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Enemy {
    private int xPosition;
    private int yPosition;


    // image
    Bitmap image;

    // make the hitbox
    Rect bodyHitbox;
    Rect headHitbox;


    // vector variables
    double xn = 0;
    double yn = 0;




    public Enemy(Context context, int x, int y) {
        this.xPosition = x;
        this.yPosition = y;

        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);

        this.headHitbox = new Rect(
                this.xPosition + 50,
                this.yPosition,
                this.xPosition + this.image.getWidth() - 50,
                this.yPosition + 50
        );

        this.bodyHitbox = new Rect(
                this.xPosition,
                this.yPosition + 55,
                this.xPosition + this.image.getWidth(),
                this.yPosition + this.image.getHeight()
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

    public Rect getHeadHitbox() {
        return headHitbox;
    }

    public void setHeadHitbox(Rect hitbox) {
        this.headHitbox = hitbox;
    }

    public Rect getBodyHitbox() {
        return bodyHitbox;
    }

    public void setBodyHitbox(Rect bodyHitbox) {
        this.bodyHitbox = bodyHitbox;
    }

    public void updateHeadHitbox() {
        this.headHitbox.left = this.xPosition + 50;
        this.headHitbox.top = this.yPosition;
        this.headHitbox.right = this.xPosition + this.image.getWidth() - 50;
        this.headHitbox.bottom = this.yPosition + 50;
    }

    public void updateBodyHitbox() {
        this.bodyHitbox.left = this.xPosition;
        this.bodyHitbox.top = this.yPosition + 55;
        this.bodyHitbox.right = this.xPosition + this.image.getWidth();
        this.bodyHitbox.bottom = this.yPosition + this.image.getHeight();
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