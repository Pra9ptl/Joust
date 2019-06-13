package com.example.patel.joust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable, GestureDetector.OnGestureListener {

    // -----------------------------------
    // ## ANDROID DEBUG VARIABLES
    // -----------------------------------

    // Android debug variables
    final static String TAG = "JOUST";
    GestureDetector gestureDetector;
    // -----------------------------------
    // ## SCREEN & DRAWING SETUP VARIABLES
    // -----------------------------------

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;
    int level1;
    int level2;
    int level3;
    int level4;
    Sprite demo;


    private GestureDetectorCompat mDetector;


    // -----------------------------------
    // ## GAME SPECIFIC VARIABLES
    // -----------------------------------

    //Creating an array to store all enemies
    ArrayList<Sprite> enemies = new ArrayList<Sprite>();
    int[] speed = new int[8];
    int speed_count = 0;

    // ----------------------------
    // ## SPRITES
    // ----------------------------
    Sprite e1;


    // ----------------------------
    // ## GAME STATS - number of lives, score, etc
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        gestureDetector = new GestureDetector(getContext(), this);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        this.level2 = ((screenHeight / 2) - 100);
        this.level1 = ((screenHeight / 4) - 100);
        this.level3 = (((screenHeight / 2) + (screenHeight / 4)) - 100);
        this.level4 = screenHeight - 200;

        this.printScreenInfo();

        // @TODO: Add your sprites to this section
        // This is optional. Use it to:
        //  - setup or configure your sprites
        //  - set the initial position of your sprites

        //cat sprite to get the width and height properties
        demo = new Sprite(getContext(), 100, 200, R.drawable.cat);

        // @TODO: Any other game setup stuff goes here


    }

    // ------------------------------
    // HELPER FUNCTIONS
    // ------------------------------

    // Enemy will be created each time this function is called (xs and ys will be passed randomly
    public void makeEnemy(int xs, int ys) {
        e1 = new Sprite(getContext(), xs, ys, R.drawable.cat);
        enemies.add(e1);
    }

    public int randonLevel() {
        Random r = new Random();
        int level = r.nextInt(4);
        return (level + 1);

    }

    // This funciton prints the screen height & width to the screen.
    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }


    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {
        // @TODO: Update the position of the sprites
        if (enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                Sprite t = enemies.get(i);
                t.setxPosition(t.getxPosition() + speed[i]);

                //Making enemies appear from other side of screen
                if(t.getxPosition()> this.screenWidth)
                {
                    t.setxPosition( - (t.image.getWidth()));
                }

            }
        }
        // @TODO: Collision detection code

    }

    Paint p;
    long currentTime = 0;
    long previousTime = 0;

    // 2. Tell Android to DRAW the sprites at their positions
    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------------------------------------------
            //@TODO: Draw the sprites (rectangle, circle, etc)
            //----------------------------------------------------
            // Put all your drawing code in this section

            // configure the drawing tools
            p = new Paint();
            //setting background images of canvas
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
            b = Bitmap.createScaledBitmap(b, screenWidth, screenHeight, false);
            p.setColor(Color.RED);
            canvas.drawBitmap(b, 0, 0, p);

            //building levels
            //level image variable
            Bitmap level = BitmapFactory.decodeResource(getResources(), R.drawable.brick_block);
            level = Bitmap.createScaledBitmap(level, screenWidth, 100, false);
            p.setColor(Color.GREEN);
            //adding level to canvas
            canvas.drawBitmap(level, 0, level1, p);
            canvas.drawBitmap(level, 0, level2, p);
            canvas.drawBitmap(level, 0, level3, p);
            canvas.drawBitmap(level, 0, level4, p);

            // ------------------------------
            // CREATING ENEMIES
            // ------------------------------

            //getting level
            int get_level = randonLevel();

            //keeping track of time

            // get current time
            currentTime = System.currentTimeMillis();

            if ((currentTime - previousTime) > 2000) {

                //setting random position for the enemies after every 2 seconds (max enemies limit = 8)

                if (enemies.size() < 8) {

                    if (get_level == 1) {
                        makeEnemy((int) ((Math.random() * (((this.screenWidth - this.demo.image.getWidth()) - 0) + 1)) + 0),
                                this.level1 - this.demo.image.getHeight());
                        //setting speed
                        speed[speed_count] = (int) ((Math.random() * (((20 - 5) + 1)) + 5));
                    } else if (get_level == 2) {
                        makeEnemy((int) ((Math.random() * (((this.screenWidth - this.demo.image.getWidth()) - 0) + 1)) + 0),
                                this.level2 - this.demo.image.getHeight());
                        speed[speed_count] = (int) ((Math.random() * (((20 - 5) + 1)) + 5));
                    } else if (get_level == 3) {
                        makeEnemy((int) ((Math.random() * (((this.screenWidth - this.demo.image.getWidth()) - 0) + 1)) + 0),
                                this.level3 - this.demo.image.getHeight());
                        speed[speed_count] = (int) ((Math.random() * (((20 - 5) + 1)) + 5));
                    } else if (get_level == 4) {
                        makeEnemy((int) ((Math.random() * (((this.screenWidth - this.demo.image.getWidth()) - 0) + 1)) + 0),
                                this.level4 - this.demo.image.getHeight());
                        speed[speed_count] = (int) ((Math.random() * (((20 - 5) + 1)) + 5));
                    }
                    speed_count++;
                }
                previousTime = currentTime;
            }
            //drawing all the enemies from array
            if (enemies.size() > 0) {
                for (int i = 0; i < enemies.size(); i++) {
                    Sprite t = enemies.get(i);
                    p.setColor(Color.WHITE);
                    canvas.drawBitmap(t.getImage(), t.getxPosition(), t.getyPosition(), p);
                }
            }

            //@TODO: Draw game statistics (lives, score, etc)

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    // Sets the frame rate of the game
    public void setFPS() {
        try {
            gameThread.sleep(50);
        } catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {
        Log.d("Fling Tap", "You Tapped");
        if (motionEvent1.getY() - motionEvent2.getY() > 50) {

            Log.d("Swipe", "Swipe Up");
            Toast.makeText(getContext(), " Swipe Up ", Toast.LENGTH_LONG).show();

            return true;
        }

        if (motionEvent2.getY() - motionEvent1.getY() > 50) {

            Log.d("Swipe", "Swipe Down");
            Toast.makeText(getContext(), " Swipe Down ", Toast.LENGTH_LONG).show();

            return true;
        }

        if (motionEvent1.getX() - motionEvent2.getX() > 50) {

            Log.d("Swipe", "Swipe Left");
            Toast.makeText(getContext(), " Swipe Left ", Toast.LENGTH_LONG).show();

            return true;
        }

        if (motionEvent2.getX() - motionEvent1.getX() > 50) {

            Log.d("Swipe", "Swipe Right");
            Toast.makeText(getContext(), " Swipe Right ", Toast.LENGTH_LONG).show();

            return true;
        } else {

            return true;
        }
    }


    @Override
    public void onLongPress(MotionEvent arg0) {
        Log.d("Long Tap", "You Tapped");
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        Log.d("Scroll Tap", "You Tapped");
        // TODO Auto-generated method stub

        return true;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        Log.d("Single Tap", "You Tapped");
        return true;
    }

}