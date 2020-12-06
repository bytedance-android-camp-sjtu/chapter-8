package com.bytedance.camera.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SimpleVideoRecordActivity extends AppCompatActivity {

    private Button mRecordButton;
    private VideoView mVideoView;
    static final int REQUEST_VIDEO_CAPTURE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_video_record);
        mRecordButton = findViewById(R.id.record);
        mVideoView = findViewById(R.id.video_view);

        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
               startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoURI =  data.getData();
            mVideoView.setVideoURI(videoURI);
            mVideoView.start();
        }
    }
}
