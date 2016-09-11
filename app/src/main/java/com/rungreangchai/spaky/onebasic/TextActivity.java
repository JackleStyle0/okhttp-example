package com.rungreangchai.spaky.onebasic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String URL_PRODUCR = "http://192.168.1.17/test/product.php";
    private final int RESULT_LOAD_IMAGE = 1;
    private Button btnTest;
    private ImageButton btnImgTest;
    Bitmap upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        bindWidget();
        btnImgTest.setOnClickListener(this);
        TestAsyn task = new TestAsyn();
        task.execute(URL_PRODUCR);

    }

    public void bindWidget() {
        btnImgTest = (ImageButton) findViewById(R.id.btn_timg_test);
        btnTest = (Button) findViewById(R.id.btn_test);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_timg_test:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case R.id.btn_test:
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri path = data.getData();
            try {
                upload = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                btnImgTest.setImageBitmap(upload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String onPostRequest(String urls) {
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE = MediaType.parse("image/jpg");
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", "pic1")
                .addFormDataPart("format", "json")
                .addFormDataPart("filename", "pic.jpg")
                .addFormDataPart("file", "test", RequestBody.create(MEDIA_TYPE, bitmapToFile()))
                .build();
        Request request = new Request.Builder()
                .url(urls)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.d("Exception ", " IO e" + e.toString());
        }
        return null;
    }

    public File bitmapToFile() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic3);
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

    public class TestAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return onPostRequest(urls[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("respones", "show : " + s.toString());
        }
    }
}
