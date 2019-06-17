package com.example.patel.joust;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {

    private SoundPool soundPool;
    private int playerMove;
    private int playerJumpUp;
    private int playerJumpDown;
    private int enemyDie;
    private int playerDie;
    private int eggTimeUp;
    private int collectEgg;
    private int bgMusic;

    public Sounds(Context context){

        //Creating sound pool
        soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);

        //adding sounds to sound pool
        playerJumpUp = soundPool.load(context,R.raw.playerup,1);
        playerJumpDown = soundPool.load(context,R.raw.playerdown,1);
        playerDie = soundPool.load(context,R.raw.playerdie,1);
        enemyDie = soundPool.load(context,R.raw.enemydie,1);

        eggTimeUp = soundPool.load(context,R.raw.eggtimeup,1);
        collectEgg = soundPool.load(context,R.raw.eggcollect,1);
        bgMusic = soundPool.load(context,R.raw.gamemusic,1);



    }


    public void getPlayerJumpUp() {
        soundPool.play(playerJumpUp,1.0f,1.0f,1,0,1.0f);

    }

    public void getPlayerJumpDown() {
        soundPool.play(playerJumpDown,1.0f,1.0f,1,0,1.0f);
    }

    public void getEnemyDie() {
        soundPool.play(enemyDie,1.0f,1.0f,1,0,1.0f);
    }

    public void getPlayerDie() {
        soundPool.play(playerDie,1.0f,1.0f,1,0,1.0f);
    }

    public void getEggTimeUp() {
        soundPool.play(eggTimeUp,0.1f,0.1f,1,0,1.0f);
    }

    public void getCollectEgg() {
        soundPool.play(collectEgg,1.0f,1.0f,1,0,1.0f);
    }

    public void getBgMusic() {
        soundPool.play(bgMusic,1.0f,1.0f,1,-1,1.0f);
    }
}