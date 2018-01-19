package com.swarawan.intent_sample;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PICKFILE = 1;
    private final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextPage();
            }
        });

        findViewById(R.id.btnMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMessaging();
            }
        });

        findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDial();
            }
        });

        findViewById(R.id.btnMedia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMedia();
            }
        });

        findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCamera();
            }
        });
    }

    private void gotoNextPage() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    private void goToDial() {
        Intent intent = new Intent(Intent.ACTION_DIAL); //Implicit
        intent.setData(Uri.parse("tel:" + "081733334444"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToMedia() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); //mime
        intent = Intent.createChooser(intent, "Pick a File"); // <--- directory
        startActivityForResult(intent, REQUEST_PICKFILE);
    }

    private void goToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (null != intent.resolveActivity(getPackageManager())) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void goToMessaging() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + "085729891040"));
        intent.putExtra("sms_body", "it should be written on text body");
        if (null != intent.resolveActivity(getPackageManager())) {
            startActivity(intent);
        }
    }

    private void showToast(final String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICKFILE && resultCode == RESULT_OK) {
            String name = data.getData().getLastPathSegment();
            showToast(name);
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            showToast(data.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
