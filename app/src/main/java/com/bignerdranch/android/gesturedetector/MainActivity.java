package com.bignerdranch.android.gesturedetector;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GestureDetector mGestureDetector;
ImageView mImageView;
    class mylistiner extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if((e1.getX()-e2.getX())>50)
            {
                finish();
            }else
            {
                Intent intent = new Intent(); //调用照相机
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }
            return super.onFling(e1, e2, velocityX, velocityY);

        }

        @Override
            public boolean onDoubleTap(MotionEvent e) {
            Uri uri = Uri.parse("smsto:13800000000");
            Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
            intent.putExtra("sms_body", "The SMS text");
            startActivity(intent);

            return super.onDoubleTap(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this,new mylistiner());
        mImageView = (ImageView) findViewById(R.id.GestureDetector_imageView);
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }
}
