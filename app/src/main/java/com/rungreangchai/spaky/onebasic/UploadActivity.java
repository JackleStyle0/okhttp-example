package com.rungreangchai.spaky.onebasic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class UploadActivity extends AppCompatActivity implements View.OnClickListener {
    private final int RESULT_LOAD_IMAGE = 1;
    private final String TYPE_PNG = ".png";
    private final String TYPE_JPEG = ".jpg";

    private EditText edName, edNumber, edPrice;
    private ImageButton imgProduct;
    private Button btnUpload;
    private Bitmap upload;
    private Uri filePath;
    private OutputStream file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        bindWidget();
        imgProduct.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
    }


    public void bindWidget() {
        edName = (EditText) findViewById(R.id.ed_name);
        edNumber = (EditText) findViewById(R.id.ed_number);
        edPrice = (EditText) findViewById(R.id.ed_price);
        btnUpload = (Button) findViewById(R.id.btn_upload);
        imgProduct = (ImageButton) findViewById(R.id.btn_select_image);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_select_image:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case R.id.btn_upload:

                String name = edName.getText().toString();
                String price = edPrice.getText().toString();
                String number = edNumber.getText().toString();
                bitmaptoFile(name, TYPE_PNG);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                upload = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                imgProduct.setImageBitmap(upload);
            } catch (IOException e) {
                Log.d("Upload Activity", " IOException " + e.toString());
            }
        }
    }


    public String bitmaptoFile(String name, String fileType) {
        File fileDir = getApplicationContext().getFilesDir();
        File imgFile = new File(fileDir, name + "" + fileType);

        OutputStream os = null;
        try {
            os = new FileOutputStream(imgFile);
            if (fileDir.equals(".png")) {
                upload.compress(Bitmap.CompressFormat.PNG, 100, os);
            } else {
                upload.compress(Bitmap.CompressFormat.JPEG, 100, os);
            }

            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("os", "is" + imgFile.toString());

        return os.toString();
    }
}
