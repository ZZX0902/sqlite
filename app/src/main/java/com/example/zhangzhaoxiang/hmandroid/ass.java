package com.example.zhangzhaoxiang.hmandroid;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class ass extends Activity implements
        SeekBar.OnSeekBarChangeListener {
    private AssetManager assetManager;
    private MediaPlayer player = null;
    private int minWidth = 80;
    private ImageView imageView;
    private TextView textview1, textview2;
    Matrix matrix=new Matrix();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass);
        player = new MediaPlayer();
        assetManager = getAssets();
        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd(" Something Just Like This.mp3");
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getStartOffset());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView = (ImageView) findViewById(R.id.imageview3);
        SeekBar seekbar1 = (SeekBar) findViewById(R.id.sbSize);
        SeekBar seekbar2 = (SeekBar) findViewById(R.id.sbRotate);
        textview1 = (TextView) findViewById(R.id.tv1);
        textview2 = (TextView) findViewById(R.id.tv2);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        seekbar1.setMax(dm.widthPixels - minWidth);
        seekbar1.setOnSeekBarChangeListener(this);
        seekbar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        if (seekBar.getId() == R.id.sbSize) {
            //设置图片的大小
            int newWidth=progress+minWidth;
            int newHeight=(int)(newWidth*3/4);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth, newHeight));
            textview1.setText("图像宽度："+newWidth+"图像高度："+newHeight);
        } else if (seekBar.getId() == R.id.sbRotate){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.pp);
            matrix.setRotate(progress,30,60);
            bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            imageView.setImageBitmap(bitmap);
            textview2.setText(progress+"°");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
