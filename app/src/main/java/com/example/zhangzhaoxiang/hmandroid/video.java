package com.example.zhangzhaoxiang.hmandroid;





import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class video extends AppCompatActivity {
    private VideoView videoView;
    private Button btn_start,btn_end;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        ImageButton btn_ass = (ImageButton) findViewById(R.id.btn_ass);
        btn_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(video.this,ass.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"点击imagebutton事件",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        videoView= (VideoView) findViewById(R.id.videoView);
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_end= (Button) findViewById(R.id.btn_end);



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
            }
        });
    }


    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}

