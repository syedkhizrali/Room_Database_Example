package com.roomdatabaseexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.roomdatabaseexample.R;
import com.roomdatabaseexample.room.db.AppExecutors;
import com.roomdatabaseexample.room.db.UserDatabase;
import com.roomdatabaseexample.room.entity.UserDetaill;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_name,tv_address, tv_email,tv_password,tv_contact;
    private Button btn_logout;
    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDatabase = UserDatabase.getInstance(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //method calls
        init();
        setData();
        onClick();
        //method calls
    }

    private void onClick() {
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutUser();
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
            }
        });
    }

    private void init() {
        tv_address = findViewById(R.id.tv_address);
        tv_contact = findViewById(R.id.tv_contact);
        tv_email = findViewById(R.id.tv_email);
        tv_name = findViewById(R.id.tv_name);
        tv_password = findViewById(R.id.tv_password);
        btn_logout = findViewById(R.id.btn_logout);
    }

    private void setData(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<UserDetaill> userDetaills = userDatabase.UserDao().getAll();
                tv_name.setText(userDetaills.get(0).getUsername());
                tv_address.setText(userDetaills.get(0).getAddress());
                tv_contact.setText(userDetaills.get(0).getContact());
                tv_email.setText(userDetaills.get(0).getEmail());
                tv_password.setText(userDetaills.get(0).getPassword());

            }
        });
    }

    private void logOutUser(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDatabase.UserDao().deleteAll();
            }
        });
    }
}