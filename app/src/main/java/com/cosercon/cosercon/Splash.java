package com.cosercon.cosercon;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by adrian.badarau on 11/11/2014.
 */
public class Splash extends Activity {
    MediaPlayer ourSong;
    protected void onCreate(Bundle CoserconTest){
        super.onCreate(CoserconTest);
        setContentView(R.layout.spash);
        ourSong = MediaPlayer.create(Splash.this, R.raw.button_press);
        ourSong.start();
        Thread timer = new Thread(){
         public void run(){
          try{
              sleep(1000);
          }catch (InterruptedException e){
              e.printStackTrace();
          }finally {
              Intent openHomePage = new Intent("android.intent.action.MENU");
              startActivity(openHomePage);
          }
         }
        };
        timer.start();
    }

    protected void onPause(){
        super.onPause();
        ourSong.release();
        finish();
    }
}
