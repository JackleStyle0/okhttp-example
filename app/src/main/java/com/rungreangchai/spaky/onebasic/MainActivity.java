package com.rungreangchai.spaky.onebasic;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rungreangchai.spaky.onebasic.network.Service;
import com.rungreangchai.spaky.onebasic.network.model.UserProfile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Service.UserResultLoginCallback {
    private Button btnResponse;
    private UserProfile userProfiles;
    private TextView txtShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidget();
        btnResponse.setOnClickListener(this);

    }

    public void bindWidget() {
        btnResponse = (Button) findViewById(R.id.btn_response);
        txtShow = (TextView) findViewById(R.id.txt_show);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_response:
                callService();
                break;
            default:
                break;
        }
    }

    public void setUserProfile(UserProfile userProfiles) {
        txtShow.setText("first nam : " + userProfiles.getFirstName() + "\nLast name : " + userProfiles.getLastName());
    }

    public void callService() {
        Service.getUserLogin(this);
    }


    @Override
    public void onUserLoginSuccess(UserProfile userProfileList) {
        this.userProfiles = userProfileList;
        setUserProfile(userProfileList);
    }
}
