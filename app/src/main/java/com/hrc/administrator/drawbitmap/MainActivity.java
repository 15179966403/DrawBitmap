package com.hrc.administrator.drawbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static class MyView extends View{
        private Bitmap bitmap1;
        private Bitmap bitmap2;
        private Bitmap bitmap3;
        private Bitmap bitmap4;
        private Drawable drawable;
        public MyView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);
            InputStream is=context.getResources().openRawResource(R.raw.panda);
            BitmapFactory.Options opts=new BitmapFactory.Options();
            opts.inSampleSize=2;
            bitmap1=BitmapFactory.decodeStream(is,null,opts);
            is=context.getResources().openRawResource(R.raw.tiger);
            bitmap2=BitmapFactory.decodeStream(is);
            int w=bitmap2.getWidth();
            int h=bitmap2.getHeight();
            int[] pixels=new int[w*h];      //存储像素点的数组
            //复制bitmap2的所有像素颜色值
            bitmap2.getPixels(pixels,0,w,0,0,w,h);
            //将bitmap2复制两份(bitmap3和bitmap4)
            bitmap3=Bitmap.createBitmap(pixels,0,w,w,h, Bitmap.Config.ARGB_8888);
            bitmap4=Bitmap.createBitmap(pixels,0,w,w,h, Bitmap.Config.ARGB_4444);
            drawable=context.getResources().getDrawable(R.drawable.button);
            drawable.setBounds(50,350,180,420);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(bitmap1,10,10,null);
            canvas.drawBitmap(bitmap2,10,200,null);
            canvas.drawBitmap(bitmap3,110,200,null);
            canvas.drawBitmap(bitmap4,210,200,null);
            drawable.draw(canvas);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
