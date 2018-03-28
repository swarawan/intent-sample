package com.swarawan.intent_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by rioswarawan on 1/19/18.
 */

public class ResultActivity extends AppCompatActivity {

    public static final String KEY_ANDROID = "bundle-android";
    public static final String KEY_NAME = "extra-name";

    DataModel data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        handleCaraPertama();
//        handleCaraKedua();
//        handleCaraKetiga();
    }

    private void handleCaraPertama() {
        data = getIntent().getExtras().getParcelable("parcel");
        setData(data.name, data.email);
    }

    private void handleCaraKedua() {
        Bundle bundle = getIntent().getExtras();
        String android = bundle.getString(KEY_ANDROID, "Android second");
        Integer harga = bundle.getInt("bundle-harga", 0);
        Boolean dijual = bundle.getBoolean("bundle-dijual", false);
    }

    private void handleCaraKetiga() {
        String name = getIntent().getStringExtra(KEY_NAME);
        String email = getIntent().getStringExtra("extra-email");
        setData(name, email);
    }

    private void setData(String name, String email) {
        ((TextView) findViewById(R.id.tvName)).setText(name);
        ((TextView) findViewById(R.id.tvEmail)).setText(email);
    }

    public void kirim() {
        if (null != data) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("parcel-lagi", data);
        }
    }
}
