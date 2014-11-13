package com.cosercon.cosercon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by adrian.badarau on 11/12/2014.
 */
public class Camera1 extends Activity implements View.OnClickListener {
    ImageView viewPic;
    Button takePic;
    Button setPic;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        initialize();
        /*Timed Action*/
        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
              /* TakePicture.execute();*/
            }
        },1000, 9000);
        /*Timed Action Stop*/
        InputStream is = getResources().openRawResource(R.drawable.cosercon_logo);
        bmp = BitmapFactory.decodeStream(is);

    }

    private void initialize() {

        viewPic = (ImageView) findViewById(R.id.ivReturnedPic);
        takePic = (Button) findViewById(R.id.bTakePic);
        setPic = (Button) findViewById(R.id.bSetPic);

        takePic.setOnClickListener(this);
        setPic.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bTakePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
            case R.id.bSetPic:

                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            viewPic.setImageBitmap(bmp);
        }
    }

    private class TakePicture extends AsyncTask <byte[],String, String> {

        @Override
        protected String doInBackground(byte[]... params) {
            mytakePicture();
            return null;
        }

        public void mytakePicture(){
            Camera object;
            object = Camera.open();
            Camera.PictureCallback capturedPic = new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    Bitmap pic = BitmapFactory.decodeByteArray(data,0,data.length);
                    if (pic != null){
                        Toast.makeText(getApplicationContext(),"Pic Taken", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Pic not taken", Toast.LENGTH_SHORT).show();
                    }
                    camera.release();
                }
            };
            if (object != null) {
                object.takePicture(null, null, capturedPic);
            }
        }
    }
}
