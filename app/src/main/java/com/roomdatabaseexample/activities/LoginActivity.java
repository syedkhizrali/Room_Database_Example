package com.roomdatabaseexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roomdatabaseexample.R;
import com.roomdatabaseexample.room.db.AppExecutors;
import com.roomdatabaseexample.room.db.UserDatabase;
import com.roomdatabaseexample.room.entity.UserDetaill;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_password;
    private Button btn_login;
    private UserDatabase userDatabase;
    private String email, password;
    private String USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDatabase = UserDatabase.getInstance(LoginActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //method calls
        init();
        validateData();
        onClick();
        //method calls
    }

    private void onClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }


    private void init() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
    }

    private void validateData(){
       /* *//*email = et_email.getText().toString();
        password = et_password.getText().toString();*//*

        Intent intent = getIntent();
        uid = intent.getIntExtra(USER_ID,0);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<UserDetaill> userData = userDatabase.UserDao().selectOne(uid);
                et_email.setText(userData.get(uid).getEmail());
                et_password.setText(userData.get(uid).getPassword());
            }
        });*/

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<UserDetaill> userDetaills = userDatabase.UserDao().getAll();
                et_email.setText(userDetaills.get(0).getEmail());
                et_password.setText(userDetaills.get(0).getPassword());
            }
        });

    }
}