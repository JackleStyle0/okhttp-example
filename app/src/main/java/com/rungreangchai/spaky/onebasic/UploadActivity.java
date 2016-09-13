package com.rungreangchai.spaky.onebasic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.LinearLayout;

import com.rungreangchai.spaky.onebasic.network.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class UploadActivity extends AppCompatActivity implements View.OnClickListener, Service.InsertProductCallback {
    private final int RESULT_LOAD_IMAGE = 1;

    private LinearLayout viewGroup;
    private EditText edName, edNumber, edPrice;
    private boolean success;
    private ImageButton imgProduct;
    private Button btnUpload;
    private Bitmap upload;
    private Uri filePath;

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
        viewGroup = (LinearLayout) findViewById(R.id.view_group);

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

                Service.productUpload(number, name, price, bitmapToFile(), this);
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


    public File bitmapToFile() {
        Bitmap bitmap = ((BitmapDrawable) imgProduct.getDrawable()).getBitmap();
        File file = new File(getCacheDir(), "image.png");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file;
    }

    @Override
    public void onInsertProductSuccess(Boolean success) {
        this.success = success;
        if (success.equals("true")) {
            Snackbar.make(viewGroup, "Upload Success", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(viewGroup, "Upload Fail", Snackbar.LENGTH_SHORT).show();
        }
    }
}
