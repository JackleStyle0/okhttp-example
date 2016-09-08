package com.rungreangchai.spaky.onebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class UploadActivity extends AppCompatActivity {
    EditText edName, edNumber, edPrice;
    ImageButton imgProduct;
    Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }


    public void bindWidget() {

    }
}
