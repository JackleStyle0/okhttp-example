package com.rungreangchai.spaky.onebasic;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rungreangchai.spaky.onebasic.network.Service;

public class RegisterActivity extends AppCompatActivity implements Service.UserRegisterCallback {

    private EditText edUsername, edPassword, edFirstname, edLastname;
    private Button btnRegister;
    private LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String firstName = edFirstname.getText().toString();
                String lastName = edLastname.getText().toString();
                Service.regisUser(username, password, firstName, lastName, RegisterActivity.this);
            }
        });

    }

    public void bindWidget() {
        edFirstname = (EditText) findViewById(R.id.firstname);
        edLastname = (EditText) findViewById(R.id.lastname);
        edUsername = (EditText) findViewById(R.id.username);
        edPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        rootView = (LinearLayout) findViewById(R.id.root_view);
    }

    @Override
    public void onUserRegisSuccess(Boolean success) {
        if (success) {
            Snackbar.make(rootView, "Register success.", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(rootView, "Register fail.", Snackbar.LENGTH_SHORT).show();
        }
    }
}
