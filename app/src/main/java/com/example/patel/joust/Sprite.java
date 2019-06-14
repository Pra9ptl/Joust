package com.example.patel.joust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Sprite {
    private int xPosition;
    private int yPosition;


    // image
    Bitmap image;

    // make the hitbox
    Rect hitbox;


    // vector variables
    double xn = 0;
    double yn = 0;




    public Sprite(Context context, int x, int y, int imageName) {
        this.xPosition = x;
        this.yPosition = y;

        this.image = BitmapFactory.decodeResource(context.getResources(), imageName);

        if (imageName == R.drawable.cat) {
            this.hitbox = new Rect(
                    this.xPosition + 50,
                    this.yPosition,
                    (this.xPosition + this.image.getWidth()) - 50,
                    this.yPosition + 50
            );
        } else if (imageName == R.drawable.pikachu) {
            this.hitbox = new Rect(
                    this.xPosition + 50,
                    this.yPosition + this.image.getHeight() - 50,
                    (this.xPosition + this.image.getWidth()) - 50,
                    this.yPosition + this.image.getHeight()
            );
        }

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

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }

    public void updateHitbox(int imageName) {
        if (imageName == R.drawable.cat) {
            this.hitbox = new Rect(
                    this.xPosition + 50,
                    this.yPosition,
                    (this.xPosition + this.image.getWidth()) - 50,
                    this.yPosition + 50
            );
        } else if (imageName == R.drawable.pikachu) {
            this.hitbox = new Rect(
                    this.xPosition +50,
                    this.yPosition + this.image.getHeight() - 50,
                    (this.xPosition + this.image.getWidth()) -50,
                    this.yPosition + this.image.getHeight()
            );
        }
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
